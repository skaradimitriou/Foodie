package com.stathis.foodie.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RecipeModel(

    val label: String,
    val image: String?,
    val url: String,
    val shareAs: String,
    val ingredientLines: List<String>,
    val ingredients: List<IngredientModel>,
    val calories: Double,
    val totalWegiht: Double,
    val totalTime: Double,
    val totalNutrients : TotalNutrientsMainModel

) : LocalModel,Parcelable {
    constructor() : this(
            "", "", "", "", emptyList(), emptyList(), 0.0, 0.0, 0.0,
            TotalNutrientsMainModel(
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, ""),
                NutrientModel("", 0.0, "")
            )
    )
}