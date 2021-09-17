package com.cloud273.localization.example

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.cloud273.localization.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
        stopMonitorLanguageChanged()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CLLocalization.initialize(listOf("en", "vi"))
        startMonitorLanguageChanged()
        setContentView(R.layout.activity_main)
        val navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment).navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment).navController.navigateUp() || super.onSupportNavigateUp()
    }

}