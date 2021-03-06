package com.stathis.foodie.ui.results

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_categories_results.*
import kotlinx.android.synthetic.main.activity_filter_results.*
import kotlinx.android.synthetic.main.activity_filter_results.swipe_refresh_layout
import kotlinx.android.synthetic.main.fragment_filters.*

class FilterResultsActivity : AbstractActivity(R.layout.activity_filter_results) {

    private lateinit var viewModel: FilterResultsViewModel
    private var kcalMinValue = 0.0
    private var kcalMaxValue = 0.0
    private lateinit var mealType: String
    private lateinit var dietType: String

    override fun init() {
        viewModel = ViewModelProvider(this).get(FilterResultsViewModel::class.java)
    }

    override fun running() {
        kcalMinValue = intent.getDoubleExtra("KCAL_MIN_VALUE", 0.0)
        kcalMaxValue = intent.getDoubleExtra("KCAL_MAX_VALUE", 0.0)
        mealType = intent.getStringExtra("MEAL_TYPE") ?: ""
        dietType = intent.getStringExtra("DIET_TYPE") ?: ""

        calories_min_max_subheader.text =
            "Calorie range : ${kcalMinValue.toInt()} up to ${kcalMaxValue.toInt()} kcal"
        meal_type_desc.text = "Meal Type : $mealType"
        diet_type_desc.text = "Diet Type : ${dietType.capitalize()}"

        results_screen_recycler.adapter = viewModel.adapter

        getData()

        swipe_refresh_layout.setOnRefreshListener {
            getData()
        }

        viewModel.observeData(this)

        viewModel.errorCase.observe(this, Observer {
            when(it){
                true -> {
                    Snackbar.make(findViewById(android.R.id.content), application.resources.getText(R.string.snackbar_no_results), Snackbar.LENGTH_LONG)
                        .setAction("Go back") {
                            onBackPressed()
                        }
                        .show()
                }

                false -> Unit
            }
        })
    }

    override fun stopped() {
        viewModel.removeObservers(this)
    }

    private fun getData() {
        viewModel.clearCounters()

        viewModel.getRecipeData(
            kcalMinValue.toInt(),
            kcalMaxValue.toInt(),
            mealType,
            dietType,
            object : RecipeClickListener {
                override fun onRecipeClick(recipe: RecipeMain) {
                    startActivity(
                        Intent(this@FilterResultsActivity, DetailsActivity::class.java)
                            .putExtra("RECIPE", recipe)
                    )
                }
            })

        swipe_refresh_layout.isRefreshing = false

        observeDataPaging()
    }

    fun observeDataPaging() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            results_screen_recycler.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (!results_screen_recycler.canScrollVertically(1)) {
                    viewModel.getRecipeData(
                        kcalMinValue.toInt(),
                        kcalMaxValue.toInt(),
                        mealType,
                        dietType,
                        object : RecipeClickListener {
                            override fun onRecipeClick(recipe: RecipeMain) {
                                startActivity(
                                    Intent(this@FilterResultsActivity, DetailsActivity::class.java)
                                        .putExtra("RECIPE", recipe)
                                )
                            }
                        })
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}