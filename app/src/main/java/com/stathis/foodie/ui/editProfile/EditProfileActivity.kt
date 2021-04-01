package com.stathis.foodie.ui.editProfile

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AbstractActivity(R.layout.activity_edit_profile) {

    private lateinit var viewModel: EditProfileViewModel

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
            viewModel.saveUserData(edit_profile_username.toString(),edit_profile_email.toString())
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
            it?.let { profile_email.text = it }
            it?.let { edit_profile_email.setText(it) }
        })

        viewModel.username.observe(this, Observer {
            it?.let { profile_username.text = it }
            it?.let { edit_profile_username.setText(it) }
        })
    }

    override fun stopped() {
        //
    }
}