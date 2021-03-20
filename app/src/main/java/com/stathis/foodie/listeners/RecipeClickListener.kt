package com.stathis.foodie.listeners

import com.stathis.foodie.models.RecipeMain

interface RecipeClickListener {

    fun onRecipeClick(recipe: RecipeMain)
}