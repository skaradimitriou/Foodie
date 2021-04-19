package com.stathis.foodie.ui.dashboard.categories

import androidx.lifecycle.MutableLiveData
import com.stathis.foodie.R
import com.stathis.foodie.models.SuggestionItem
import com.stathis.foodie.models.SuggestionModel

class CategoriesRepository {

    val categories = MutableLiveData<List<SuggestionModel>>()

    fun getCategories() {
        categories.value = listOf(
            SuggestionModel(
                "Meal Types", listOf(
                    SuggestionItem("Breakfast", R.drawable.breakfast),
                    SuggestionItem("Lunch", R.drawable.lunch_icon),
                    SuggestionItem("Dinner", R.drawable.breakfast),
                    SuggestionItem("Snack", R.drawable.snack_icon),
                    SuggestionItem("Tea Time", R.drawable.tea_icon)
                )
            ),
            SuggestionModel(
                "Dish Types", listOf(
                    SuggestionItem("Soup", R.drawable.soup_icon),
                    SuggestionItem("Salad", R.drawable.salad_icon),
                    SuggestionItem("Dessert", R.drawable.desert_icon),
                    SuggestionItem("Cereals", R.drawable.cereal),
                    SuggestionItem("Pancake", R.drawable.pancake),
                    SuggestionItem("Starter", R.drawable.starter_food),
                    SuggestionItem("Omelet", R.drawable.omelette)
                )
            ),
            SuggestionModel(
                "Cuisine Types", listOf(
                    SuggestionItem("Indian", R.drawable.indian_icon),
                    SuggestionItem("Chinese", R.drawable.chinese_icon),
                    SuggestionItem("Italian", R.drawable.pasta_icon),
                    SuggestionItem("Mexican", R.drawable.mexican_icon),
                    SuggestionItem("Japanese", R.drawable.sushi_icon)
                )
            )
        )
    }
}