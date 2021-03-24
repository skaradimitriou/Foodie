package com.stathis.foodie.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class IngredientModel(

    val text : String,
    val weight : Double,
    val foodCategory : String?,
    val foodId : String,
    val image : String?
) : Parcelable {
    constructor() : this(
        "",0.0,"","",""
    )
}