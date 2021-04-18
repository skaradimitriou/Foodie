package com.stathis.foodie.ui.editProfile

import android.content.Intent
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import com.stathis.foodie.ui.intro.IntroActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.view_authorize_user_credentials.view.*

class EditProfileActivity : AbstractActivity(R.layout.activity_edit_profile) {

    private lateinit var viewModel: EditProfileViewModel
    private lateinit var oldUserEmail: String

    override fun init() {
        viewModel = ViewModelProvider(this).get(EditProfileViewModel::class.java)

        /*
        *
        * As a user, I want to be able to edit my profile data
        *
        * Functionalities to be implemented:
        * a. Upload photo
        * b. Change e-mail
        * c. Change username
        * d. View my profile data vertically
        *
         */
    }

    override fun running() {
        viewModel.getUserData()

        edit_username_btn.setOnClickListener {
            viewModel.makeUsernameEditable()
        }

        edit_email_btn.setOnClickListener {
            viewModel.makeEmailEditable()
        }

        edit_phone_btn.setOnClickListener {
            viewModel.makePhoneEditable()
        }

        edit_profile_save_btn.setOnClickListener{
            //save user input from all fields
            if (oldUserEmail != edit_profile_email.text.toString()) {
                val newEmail = edit_profile_email.text.toString()
                showLoginDialogue(newEmail)
            }

            viewModel.saveUsernameToDb(edit_profile_username.text.toString())
            viewModel.savePhoneToDb(edit_profile_phone.text.toString())

        }

        edit_profile_logout_btn.setOnClickListener {
            askForLogout()
        }

        observeData()
    }

    override fun stopped() {
        viewModel.isUsernameEditable.removeObservers(this)
        viewModel.isEmailEditable.removeObservers(this)
        viewModel.isPhoneEditable.removeObservers(this)
        viewModel.userEmail.removeObservers(this)
        viewModel.username.removeObservers(this)
        viewModel.phoneNumber.removeObservers(this)
    }

    private fun observeData() {
        viewModel.isUsernameEditable.observe(this, Observer {
            when (it) {
                true -> edit_profile_username.isEnabled = true
                false -> edit_profile_username.isEnabled = false
            }
        })

        viewModel.isEmailEditable.observe(this, Observer {
            when (it) {
                true -> edit_profile_email.isEnabled = true
                false -> edit_profile_email.isEnabled = false
            }
        })

        viewModel.isPhoneEditable.observe(this, Observer {
            when (it) {
                true -> edit_profile_phone.isEnabled = true
                false -> edit_profile_phone.isEnabled = false
            }
        })

        viewModel.userEmail.observe(this, Observer {
            oldUserEmail = it
            it?.let { edit_profile_email.setText(it) }
        })

        viewModel.username.observe(this, Observer {
            it?.let { edit_profile_username.setText(it) }
        })

        viewModel.phoneNumber.observe(this, Observer {
            it?.let { edit_profile_phone.setText(it) }
        })
    }

    fun showLoginDialogue(newEmail: String) {
        val dialog =
            LayoutInflater.from(this).inflate(R.layout.view_authorize_user_credentials, null)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialog).show()

        dialog.edit_profile_save_data_btn.setOnClickListener {
            val userEmail = dialog.user_email.text.toString()
            val userPassword = dialog.user_password_field.text.toString()
            viewModel.saveEmailToDb(userEmail, userPassword, newEmail)
            dialogBuilder.dismiss()
        }
    }

    private fun askForLogout() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Do you want to log out?")

        builder.setPositiveButton("YES") { dialog, which ->
            viewModel.logoutUser()
            startActivity(Intent(this, IntroActivity::class.java))
        }

        builder.setNegativeButton("CANCEL") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
}