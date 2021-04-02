package com.stathis.foodie.ui.editProfile

import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.edit_profile_save_data_btn
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

        edit_profile_btn.setOnClickListener {
            viewModel.makeFieldsEditable()
        }

        edit_profile_save_data_btn.setOnClickListener {
            if (oldUserEmail != edit_profile_email.text.toString()) {
                val newEmail = edit_profile_email.text.toString()
                showLoginDialogue(newEmail)
            }

            viewModel.saveUsernameToDb(edit_profile_username.text.toString())
        }

        viewModel.isEditable.observe(this, Observer {
            when (it) {
                true -> {
                    edit_profile_username.isEnabled = true
                    edit_profile_email.isEnabled = true
                }

                false -> {
                    edit_profile_username.isEnabled = false
                    edit_profile_email.isEnabled = false
                }
            }
        })

        viewModel.userEmail.observe(this, Observer {
            oldUserEmail = it
            it?.let { profile_email.text = it }
            it?.let { edit_profile_email.setText(it) }
        })

        viewModel.username.observe(this, Observer {
            it?.let { profile_username.text = it }
            it?.let { edit_profile_username.setText(it) }
        })
    }

    override fun stopped() {
        viewModel.isEditable.removeObservers(this)
        viewModel.userEmail.removeObservers(this)
        viewModel.username.removeObservers(this)
    }

    fun showLoginDialogue(newEmail: String) {
        val dialog = LayoutInflater.from(this).inflate(R.layout.view_authorize_user_credentials, null)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialog).show()

        dialog.edit_profile_save_data_btn.setOnClickListener {
            val userEmail = dialog.user_email.text.toString()
            val userPassword = dialog.user_password_field.text.toString()
            viewModel.saveEmailToDb(userEmail, userPassword, newEmail)
            dialogBuilder.dismiss()
        }
    }
}