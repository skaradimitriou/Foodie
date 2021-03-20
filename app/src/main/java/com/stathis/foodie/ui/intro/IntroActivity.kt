package com.stathis.foodie.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseUser
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import com.stathis.foodie.ui.dashboard.DashboardActivity
import com.stathis.foodie.ui.login.LoginActivity
import com.stathis.foodie.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AbstractActivity(R.layout.activity_intro) {

    private lateinit var viewModel: IntroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IntroViewModel::class.java)
    }

    override fun init() {
        /*
        As a User, I want to :

        a) be able to navigate to login/ register screens
        c) be able to auto-login if I am already logged in

     */
    }

    override fun onStart() {
        super.onStart()
        val currentUser = viewModel.auth.currentUser
        updateUI(currentUser)
    }

    override fun running() {
        //Glide.with(this).load(R.drawable.cooking_background).into(intro_background_img)

        intro_login_btn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        intro_register_btn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun stopped() {}

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }
}