package com.stathis.foodie.ui.register

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import com.stathis.foodie.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AbstractActivity(R.layout.activity_register) {

    private lateinit var viewModel: RegisterViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        /*
        As a User, I want to :

        a) be able to create a new account to the app

        */
    }

    override fun running() {
        register_btn.setOnClickListener {
            viewModel.validateUserInput(
                register_username_input,
                register_email_input,
                register_pass_input,
                register_conf_pass_input
            )
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.registerSuccessful.observe(this, Observer {
            when (it) {
                true -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                }
                false -> {
                    Toast.makeText(this, "Please enter valid credentials", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun stopped() {}
}