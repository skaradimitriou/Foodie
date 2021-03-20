package com.stathis.foodie.ui.dashboard.profile

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractFragment
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.fragment_profile.*


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

        val photo =
            "https://img.freepik.com/free-photo/handsome-young-businessman-shirt-eyeglasses_85574-6228.jpg?size=626&ext=jpg"
        Glide.with(this).load(photo).placeholder(R.color.orange).into(profile_photo)
        profile_username.text = getString(R.string.profile_dummy_username)
        profile_email.text = getString(R.string.profile_dummy_email)

        profile_favorites_recycler.adapter = viewModel.adapter

        viewModel.getFavoriteData("chicken", object : RecipeClickListener {
            override fun onRecipeClick(recipe: RecipeMain) {
                startActivity(
                    Intent(requireContext(), DetailsActivity::class.java)
                        .putExtra("RECIPE", recipe)
                )
            }
        })

        viewModel.observe(this)
    }

    override fun stop() {
        viewModel.removeObservers(this)
    }
}