package com.stathis.foodie.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stathis.foodie.models.RecipeMain
import kotlinx.android.synthetic.main.activity_details.*

class DetailsViewModel : ViewModel() {

    private val repo = DetailsRepository()
    val isFavoriteRecipe = repo.isFavoriteRecipe
    val cookTime = MutableLiveData<String>()

    fun getCookTime(totalTime: Double) {
        val hours: Int = totalTime.toInt() / 60
        val minutes: Int = totalTime.toInt() % 60

        if (hours < 1 && minutes > 1) {
            cookTime.value = "Cook time: $minutes minutes"
        } else if (hours > 1 && minutes < 1) {
            cookTime.value = "Cook time: $hours"
        } else if (hours < 1 && minutes < 1) {
            cookTime.value = "Cook time: N/A"
        } else {
            cookTime.value = "Cook time: $hours hours and $minutes minutes"
        }
    }

    fun isFavoriteRecipe(recipe: RecipeMain) {
        repo.isFavoriteRecipe(recipe)
    }

    fun addRecipeToFavorites(recipe: RecipeMain) {
        repo.addRecipeToFavorites(recipe)
    }

    fun removeRecipeFromFavorites(recipe: RecipeMain) {
        repo.removeRecipeFromFavorites(recipe)
    }
}