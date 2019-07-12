package com.sotti.watch.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

internal class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        findNavController(R.id.nav_host_fragment).let { navController ->
            setupActionBarWithNavController(
                navController = navController,
                configuration = AppBarConfiguration(
                    setOf(
                        R.id.nav_destination_explore,
                        R.id.nav_destination_about
                    )
                )
            )
            findViewById<BottomNavigationView>(R.id.bottom_nav_view).apply {
                setupWithNavController(navController)
                setOnNavigationItemReselectedListener { }
            }
        }
    }
}