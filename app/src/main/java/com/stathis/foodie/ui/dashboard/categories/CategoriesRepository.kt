package com.stathis.foodie.ui.dashboard.categories

import androidx.lifecycle.MutableLiveData
import com.stathis.foodie.R
import com.stathis.foodie.models.SuggestionItem

class CategoriesRepository {

    val categories = MutableLiveData<List<SuggestionItem>>()

    fun getCategories() {
        categories.value = listOf(
            SuggestionItem("Breakfast", R.drawable.breakfast),
            SuggestionItem("Lunch", R.drawable.lunch_icon),
            SuggestionItem("Soups", R.drawable.soup_icon),
            SuggestionItem("Salads", R.drawable.salad_icon),
            SuggestionItem("Desserts", R.drawable.desert_icon),
            SuggestionItem("Fish", R.drawable.fish_icon),
            SuggestionItem("Meat", R.drawable.meat_icon),
            SuggestionItem("Pasta", R.drawable.pasta_icon)
        )
    }
}