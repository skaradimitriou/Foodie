package com.stathis.foodie.ui.dashboard.categories

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractFragment
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.SuggestionItemClickListener
import com.stathis.foodie.models.SuggestionItem
import com.stathis.foodie.ui.categories.CategoriesResultsActivity
import kotlinx.android.synthetic.main.fragment_categories.*


class CategoriesFragment : AbstractFragment(R.layout.fragment_categories) {

    private lateinit var viewModel: CategoriesViewModel

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
    }

    override fun running() {
        viewModel.initCategories()

        categories_screen_recycler.adapter = viewModel.adapter

        viewModel.observeData(this, object : SuggestionItemClickListener {
            override fun onCategoryClick(category: SuggestionItem) {
               startActivity(Intent(context,CategoriesResultsActivity::class.java)
                   .putExtra("CATEGORY",category.categoryName))
            }
        })
    }

    override fun stop() {
        viewModel.removeObserver(this)
    }
}