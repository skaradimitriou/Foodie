package com.stathis.foodie.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RecipeMain(

    val recipe : RecipeModel
) : LocalModel, Parcelable