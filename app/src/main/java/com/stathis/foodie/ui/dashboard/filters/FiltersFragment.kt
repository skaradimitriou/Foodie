package com.stathis.foodie.ui.dashboard.filters

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractFragment
import com.stathis.foodie.ui.results.FilterResultsActivity
import kotlinx.android.synthetic.main.fragment_filters.*

class FiltersFragment : AbstractFragment(R.layout.fragment_filters) {

    private lateinit var viewModel: FiltersViewModel

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(FiltersViewModel::class.java)
    }

    override fun running() {
        /*
        As a user I want to be able to select filters by mealType, etc
        or dishType
         */

        calories_slider.setValues(300.0f, 2700.0f)

        val mealTypeOptions = resources.getStringArray(R.array.meal_type_options)
        val mealTypeAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, mealTypeOptions)
        meal_type_autocomplete.setAdapter(mealTypeAdapter)

        val dietTypeOptions = resources.getStringArray(R.array.diet_type_options)
        val dietTypeAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, dietTypeOptions)
        diet_autocomplete.setAdapter(dietTypeAdapter)

        find_recipes_floating_btn.setOnClickListener {
            startActivity(Intent(requireContext(), FilterResultsActivity::class.java).also{
                it.putExtra("KCAL_MIN_VALUE",calories_slider.values[0].toString())
                it.putExtra("KCAL_MAX_VALUE",calories_slider.values[1].toString())
                it.putExtra("MEAL_TYPE",meal_type_autocomplete.text.toString())
                it.putExtra("DIET_TYPE",diet_autocomplete.text.toString())
            })
        }
    }

    override fun stop() {}
}