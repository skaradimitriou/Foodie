package com.stathis.foodie.ui.dashboard.main

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractFragment
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.listeners.SuggestionItemClickListener
import com.stathis.foodie.models.HomeCategoryItem
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.models.SuggestionItem
import com.stathis.foodie.ui.categories.CategoriesResultsActivity
import com.stathis.foodie.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : AbstractFragment(R.layout.fragment_main) {

    private lateinit var viewModel: MainFragmentViewModel

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
    }

    override fun running() {
        /*
        - Implement Shimmer lazy loading effect
         */

        categories_recycler.adapter = viewModel.categoryAdapter

        main_screen_viewpager.adapter = viewModel.adapter
        main_screen_viewpager.offscreenPageLimit = 3

        getData()

        swipe_refresh_layout.setOnRefreshListener {
            getData()
        }

        observeData()
    }

    private fun getData() {
        viewModel.getUsername()
        viewModel.getHomeCategories()
        viewModel.createPageTransformer()

        viewModel.getTimeOfDay(object : RecipeClickListener {
            override fun onRecipeClick(recipe: RecipeMain) {
                startActivity(
                    Intent(requireContext(), DetailsActivity::class.java)
                        .putExtra("RECIPE", recipe)
                )
            }
        })

        swipe_refresh_layout.isRefreshing = false
    }

    override fun stop() {
        viewModel.removeObserver(this)
        viewModel.userGreetingMessage.removeObservers(this)
        viewModel.username.removeObservers(this)
        viewModel.pageTransformer.removeObservers(this)
    }

    fun observeData(){
        viewModel.observeData(this, object : SuggestionItemClickListener {
            override fun onCategoryClick(category: SuggestionItem) {
                //Not a category
            }

            override fun onHomeCategoryClick(category: HomeCategoryItem) {
                startActivity(
                    Intent(context, CategoriesResultsActivity::class.java)
                        .putExtra("CATEGORY", category.categoryName)
                )
            }
        })

        viewModel.userGreetingMessage.observe(this, Observer {
            main_screen_header.text = it ?: ""
        })

        viewModel.username.observe(this, Observer {
            home_header_username.text = it.capitalize()
        })

        viewModel.pageTransformer.observe(this, Observer {
            main_screen_viewpager.setPageTransformer(it)
        })
    }
}