package com.cloud273.localization.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.cloud273.localization.*

class MainActivity : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
        stopMonitorLanguageChanged()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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