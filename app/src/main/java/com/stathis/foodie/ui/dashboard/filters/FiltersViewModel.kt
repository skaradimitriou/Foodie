package com.stathis.foodie.ui.dashboard.filters

import androidx.lifecycle.ViewModel

class FiltersViewModel : ViewModel() {

    fun checkIfValid(
        calorieMin: String,
        calorieMax: String,
        mealType: String,
        dietType: String
    ): Any {
        return calorieMin.isNotEmpty() && calorieMax.isNotEmpty() && mealType.isNotEmpty() && dietType.isNotEmpty()
    }
}