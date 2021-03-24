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
            SuggestionItem("Soup", R.drawable.soup_icon),
            SuggestionItem("Salad", R.drawable.salad_icon),
            SuggestionItem("Dessert", R.drawable.desert_icon),
            SuggestionItem("Indian", R.drawable.indian_icon),
            SuggestionItem("Chinese", R.drawable.chinese_icon),
            SuggestionItem("Italian", R.drawable.pasta_icon)
        )
    }
}