package com.stathis.foodie.ui.dashboard.filters

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
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

            when (viewModel.checkIfValid(
                calories_slider.values[0].toString(),
                calories_slider.values[1].toString(),
                meal_type_autocomplete.text.toString(),
                diet_autocomplete.text.toString()
            )) {
                true -> {
                    startActivity(Intent(requireContext(), FilterResultsActivity::class.java).also {
                        it.putExtra("KCAL_MIN_VALUE", calories_slider.values[0].toDouble())
                        it.putExtra("KCAL_MAX_VALUE", calories_slider.values[1].toDouble())
                        it.putExtra("MEAL_TYPE", meal_type_autocomplete.text.toString())
                        it.putExtra("DIET_TYPE", diet_autocomplete.text.toString())
                    })
                }
                false -> Toast.makeText(context, resources.getText(R.string.toast_fill_all_fields), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun stop() {}
}