package com.stathis.foodie.ui.categories

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_categories_results.*

class CategoriesResultsActivity : AbstractActivity(R.layout.activity_categories_results) {

    private lateinit var viewModel: CategoriesResultsViewModel
    private lateinit var categoryName: String

    override fun init() {
        viewModel = ViewModelProvider(this).get(CategoriesResultsViewModel::class.java)
    }

    override fun running() {
        categoryName = intent.getStringExtra("CATEGORY") ?: ""
        category_result_subtxt.text = categoryName

        viewModel.getResults(categoryName.toLowerCase())
        category_result_recycler.adapter = viewModel.adapter

        swipe_refresh_layout.setOnRefreshListener {
            viewModel.getResults(categoryName.toLowerCase())
            swipe_refresh_layout.isRefreshing = false
        }

        viewModel.observeData(this, object : RecipeClickListener {
            override fun onRecipeClick(recipe: RecipeMain) {
                startActivity(
                    Intent(this@CategoriesResultsActivity, DetailsActivity::class.java)
                        .putExtra("RECIPE", recipe)
                )
            }
        })
    }

    override fun stopped() {
        viewModel.removeObserve(this)
    }
}