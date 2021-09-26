package com.cloud273.localization

import android.app.Activity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

private fun localized(key: String): String {
    val code = CLLocalization.language
    return if (code.isNotEmpty()) {
        "${key}_${code}"
    } else {
        key
    }
}

val String.localized: String
    get() = CLLocalization.getValue(this)

fun Activity.startMonitorLanguageChanged() {
    CLLocalization.startMonitorActivity(this)
}

fun Activity.stopMonitorLanguageChanged () {
    CLLocalization.stopMonitorActivity(this)
}

var Activity.lTitle: String?
    get() = null
    set(value) {
        title = value?.localized
    }

var Fragment.lTitle: String?
    get() = null
    set(value) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = value?.localized
    }

var TextView.lText: String?
    get() = null
    set(value) {
        this.text = value?.localized
    }

var EditText.lHint: String?
    get() = null
    set(value) {
        this.hint = value?.localized
    }

var Button.lText: String?
    get() = null
    set(value) {
        this.text = value?.localized
    }

var CheckBox.lText: String?
    get() = null
    set(value) {
        this.text = value?.localized
    }

var RadioButton.lText: String?
    get() = null
    set(value) {
        this.text = value?.localized
    }

var ImageView.lImage: String?
    get() = null
    set(value) {
        value?.also {
            val drawableResourceId = this.resources.getIdentifier(localized(value), "drawable", CLLocalization.instance!!.application.packageName)
            this.setImageResource(drawableResourceId)
        }

    }