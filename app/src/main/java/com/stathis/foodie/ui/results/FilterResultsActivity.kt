package com.stathis.foodie.ui.results

import androidx.lifecycle.ViewModelProvider
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import kotlinx.android.synthetic.main.fragment_filters.*

class FilterResultsActivity : AbstractActivity(R.layout.activity_filter_results) {

    private lateinit var viewModel: FilterResultsViewModel
    private lateinit var kcalMinValue: String
    private lateinit var kcalMaxValue: String
    private lateinit var mealType: String
    private lateinit var dietType: String

    override fun init() {
        viewModel = ViewModelProvider(this).get(FilterResultsViewModel::class.java)
    }

    override fun running() {
        kcalMinValue = intent.getStringExtra("KCAL_MIN_VALUE") ?: ""
        kcalMaxValue = intent.getStringExtra("KCAL_MAX_VALUE") ?: ""
        mealType = intent.getStringExtra("MEAL_TYPE") ?: ""
        dietType = intent.getStringExtra("DIET_TYPE") ?: ""
    }

    override fun stopped() {
        //
    }
}