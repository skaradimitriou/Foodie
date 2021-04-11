package com.stathis.foodie.ui.login

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import com.stathis.foodie.ui.dashboard.DashboardActivity
import com.stathis.foodie.ui.forgotPassword.ForgotPasswordActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AbstractActivity(R.layout.activity_login) {

    private lateinit var viewModel: LoginViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun running() {
        login_btn.setOnClickListener {
            viewModel.validateUserInput(login_email_input, login_password_input)
        }

        forgot_pass_btn.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        observeViewModel()
    }

    override fun stopped() {
        viewModel.loginSuccessful.removeObservers(this)
        viewModel.loginFailed.removeObservers(this)
    }

    private fun observeViewModel() {
        viewModel.loginSuccessful.observe(this, Observer {
            updateUI(it)
        })

        viewModel.loginFailed.observe(this, Observer {
            when (it) {
                true -> Toast.makeText(this, "Please enter valid credentials", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    private fun updateUI(user: FirebaseUser?) {
        user?.let { startActivity(Intent(this, DashboardActivity::class.java)) }
    }
}