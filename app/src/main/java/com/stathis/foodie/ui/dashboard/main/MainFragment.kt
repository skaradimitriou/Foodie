package com.stathis.foodie.ui.dashboard.main

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractFragment
import com.stathis.foodie.listeners.MainFragmentListener
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : AbstractFragment(R.layout.fragment_main) {

    private lateinit var viewModel: MainFragmentViewModel

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
    }

    override fun running() {
        main_screen_recycler.adapter = viewModel.adapter

        viewModel.getDataFromRepository(object : MainFragmentListener {
            override fun onRecipeClick(recipe: RecipeMain) {
                startActivity(Intent(requireContext(), DetailsActivity::class.java))
            }
        })

        viewModel.observeData(this)
    }

    override fun stop() {
        viewModel.removeObserver(this)
    }
}