package com.stathis.foodie.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RecipeMain(

    val recipe: RecipeModel
) : LocalModel, Parcelable {
    constructor() : this(
        RecipeModel(
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
    )
}