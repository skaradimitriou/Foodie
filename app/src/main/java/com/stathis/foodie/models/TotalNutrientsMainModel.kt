package com.stathis.foodie.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TotalNutrientsMainModel(

    val ENERC_KCAL: NutrientModel?,
    val FAT: NutrientModel?,
    val FASAT: NutrientModel?,
    val FATRN: NutrientModel?,
    val FAMS: NutrientModel?,
    val FAPU: NutrientModel?,
    val CHOCDF: NutrientModel?,
    val FIBTG: NutrientModel?,
    val SUGAR: NutrientModel?,
    val PROCNT: NutrientModel?,
    val CHOLE: NutrientModel?,
    val  NA: NutrientModel?,
    val CA: NutrientModel?,
    val MG: NutrientModel?,
    val K: NutrientModel?,
    val FE: NutrientModel?,
    val ZN: NutrientModel?,
    val P: NutrientModel?,
    val VITA_RAE: NutrientModel?,
    val VITC: NutrientModel?,
    val THIA: NutrientModel?,
    val RIBF: NutrientModel?,
    val NIA: NutrientModel?,
    val VITB6A: NutrientModel?,
    val FOLDFE: NutrientModel?,
    val  FOLFD: NutrientModel?,
    val FOLAC: NutrientModel?,
    val VITB12: NutrientModel?,
    val VITD: NutrientModel?,
    val TOCPHA: NutrientModel?,
    val VITK1: NutrientModel?,
    val WATER: NutrientModel?
) : LocalModel, Parcelable
