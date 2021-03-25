package com.stathis.foodie.ui.dashboard.main

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractFragment
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : AbstractFragment(R.layout.fragment_main) {

    private lateinit var viewModel: MainFragmentViewModel

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
    }

    override fun running() {
        /*
        Things to do before I consider this screen done:

        - Be sure that every element from Zeplin is transfered in the screen

         */

        main_screen_recycler.adapter = viewModel.adapter

        viewModel.getUsername()

        viewModel.getTimeOfDay(object : RecipeClickListener {
            override fun onRecipeClick(recipe: RecipeMain) {
                startActivity(
                    Intent(requireContext(), DetailsActivity::class.java)
                        .putExtra("RECIPE", recipe)
                )
            }
        })

        viewModel.observeData(this)

        viewModel.userGreetingMessage.observe(this, Observer {
            main_screen_header.text = it ?: ""
        })

        viewModel.username.observe(this, Observer {
            home_header_username.text = it.capitalize() ?: ""
        })
    }

    override fun stop() {
        viewModel.removeObserver(this)
    }
}