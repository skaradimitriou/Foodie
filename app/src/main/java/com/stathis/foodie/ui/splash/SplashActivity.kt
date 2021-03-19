package com.stathis.foodie.ui.splash

import android.content.Intent
import android.os.Handler
import android.util.Log
import com.stathis.foodie.APP_ID
import com.stathis.foodie.APP_KEY
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import com.stathis.foodie.models.ResponseModel
import com.stathis.foodie.network.ApiClient
import com.stathis.foodie.ui.dashboard.DashboardActivity
import com.stathis.foodie.ui.intro.IntroActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AbstractActivity(R.layout.activity_splash) {

    override fun init() {
        Handler().postDelayed({
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }, 3000)
    }

    override fun running() {}

    override fun stopped() {}
}