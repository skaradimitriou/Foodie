package com.stathis.foodie.ui.dashboard.profile

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractFragment


class ProfileFragment : AbstractFragment(R.layout.fragment_profile) {

    private lateinit var viewModel: ProfileViewModel

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun running() {
        /*
        As a User, I want to :

        a) be able to view my profile info
        b) be able to view my favorites briefly
        c) be able to click my favorites and navigate to them

         */
    }

    override fun stop() {
        //
    }
}