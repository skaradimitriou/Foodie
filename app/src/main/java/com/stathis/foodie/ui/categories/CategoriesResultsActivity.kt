package com.stathis.foodie.ui.categories

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
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
        viewModel.clearCounters()

        categoryName = intent.getStringExtra("CATEGORY") ?: ""
        category_result_subtxt.text = categoryName

        getData()
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

        viewModel.isLoading.observe(this, Observer {
            when(it){
                true -> categories_results_loading.visibility = View.VISIBLE
                false -> categories_results_loading.visibility = View.GONE
            }
        })
    }

    override fun stopped() {
        viewModel.removeObserve(this)
    }

    private fun getData() {
        viewModel.getResults(categoryName.toLowerCase())
        observeDataPaging()
    }

    fun observeDataPaging() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            category_result_recycler.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (!category_result_recycler.canScrollVertically(1)) {
                    viewModel.loadMoreRecipes(categoryName.toLowerCase())
                }
            }
        }
    }
}