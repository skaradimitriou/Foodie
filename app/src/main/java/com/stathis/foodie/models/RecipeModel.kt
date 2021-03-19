package com.stathis.foodie.models

class RecipeModel(

    val label: String,
    val image: String,
    val url: String,
    val shareAs: String,
    val ingredientLines: List<String>,
    val ingredients: List<IngredientModel>,
    val calories: Double,
    val totalWegiht: Double,
    val totalTime: Double,
    val totalNutrients : TotalNutrientsMainModel

) : LocalModel