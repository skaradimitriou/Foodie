package com.stathis.foodie.ui.dashboard

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity

class DashboardActivity : AbstractActivity(R.layout.activity_dashboard) {

    override fun init() {
        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation_menu)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    override fun running() {}

    override fun stopped() {}
}