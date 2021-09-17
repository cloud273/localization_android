package com.cloud273.localization

import android.app.Activity
import android.app.Application
import android.content.*
import android.content.res.Resources
import androidx.core.os.ConfigurationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.RuntimeException
import android.content.Intent

class CLLocalization {

    companion object {

        private var instance: CLLocalization? = null

        const val languageChangedNotification = "com.cloud273.localization.languageChangedNotification"

        val supportedLanguages: List<String>
            get() {
                return instance?.codes ?: listOf()
            }
        
        val language: String
            get() {
                return instance?.code ?: ""
            }

        fun initialize(supportedLanguages: List<String>) {
            if (instance == null) {
                instance = CLLocalization()
                instance!!.initialize(supportedLanguages)
            }
        }

        fun setLanguage(language: String?) {
            if (instance != null) {
                instance!!.setLanguage(language)
            } else {
                throw RuntimeException("-----------------UNINITIALIZED-------------")
            }
        }

        internal fun startMonitorActivity(activity: Activity) {
            instance?.startMonitorActivity(activity)
        }

        internal fun stopMonitorActivity(activity: Activity) {
            instance?.stopMonitorActivity(activity)
        }

        internal fun getValue(key: String): String {
            return instance?.data?.get(key) ?: key
        }

        internal fun reloadPreferLanguageIfNeed() {
            instance?.reloadPreferLanguageIfNeed()
        }

    }

    private val application: Application = CLApp.instance

    private val store: SharedPreferences = application.getSharedPreferences(BuildConfig.LIBRARY_PACKAGE_NAME + ".store", Context.MODE_PRIVATE)

    private val key = "code"

    private var code: String? = null

    private val list = mutableListOf<Activity>();

    private lateinit var data: Map<String, String>

    private lateinit var codes: List<String>


    private fun initialize(supportedLanguages: List<String>) {
        supportedLanguages.ifEmpty {
            throw RuntimeException("-----------------INITIALIZED FAIL: WRONG INPUT-------------")
        }
        codes = supportedLanguages
        setLanguage(store.getString(key, null))
    }

    private fun setLanguage(language: String?) {
        if (codes.isNullOrEmpty()) {
            throw RuntimeException("-----------------UNINITIALIZED-------------")
        }
        language?.also {
            if (!codes.contains(language)) {
                throw RuntimeException("-----------------LANGUAGE ISN'T IN LIST-------------")
            }
        }
        store.edit().putString(key, language).apply()
        val c = language ?: preferredLanguage
        if (c != code) {
            code = c
            data = getData()
            list.forEach {
                it.recreate()
            }
            LocalBroadcastManager.getInstance(application.applicationContext).sendBroadcast(Intent(
                languageChangedNotification
            ))
        }
    }

    private fun startMonitorActivity(activity: Activity) {
        list.add(activity)
    }

    private fun stopMonitorActivity(activity: Activity) {
        list.remove(activity)
    }

    private fun reloadPreferLanguageIfNeed() {
        if (store.getString(key, null) == null) {
            setLanguage(null)
        }
    }

    private fun <T>loadJson(filename: String): T? {
        val resources = application.resources
        val id = resources.getIdentifier(filename, "raw", application.packageName)
        val jsonString: String = resources.openRawResource(id).bufferedReader().use { it.readText() }
        return GsonBuilder().create().fromJson(jsonString, object: TypeToken<T>() {}.type)
    }

    private fun getData(): Map<String, String> {
        val filename = "localization_${code!!}"
        val result: Map<String, String>? = loadJson(filename)
        if (result != null) {
            return result
        } else {
            throw RuntimeException("-----------------LANGUAGE FILE: $filename IS INVALID-------------")
        }
    }

    private val preferredLanguages: List<String>
        get() {
            val languages = mutableListOf<String>()
            val locals = ConfigurationCompat.getLocales(Resources.getSystem().configuration)
            for (i in 0 until locals.size()) {
                languages.add(locals.get(i).language)
            }
            return languages
        }

    private val preferredLanguage: String
        get() {
            val supportedLanguages = codes!!
            val languages = preferredLanguages
            val preferredLanguages = mutableListOf<String>()
            for (item in languages) {
                val components = item.split("-")
                if (components.size > 1) {
                    val lastComponent = components.last()
                    preferredLanguages.add(
                        item.substring(
                            0,
                            item.length - lastComponent.length - 1
                        )
                    )
                } else {
                    preferredLanguages.add(item)
                }
            }
            return preferredLanguages.firstOrNull {
                supportedLanguages.contains(it)
            } ?: supportedLanguages.first()
        }

}

class LocaleChangedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.action!!.compareTo(Intent.ACTION_LOCALE_CHANGED) == 0) {
            CLLocalization.reloadPreferLanguageIfNeed()
        }
    }
}