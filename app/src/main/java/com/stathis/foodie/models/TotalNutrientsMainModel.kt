package com.stathis.foodie.models

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TotalNutrientsMainModel(

    @PropertyName("enerc_KCAL")
    val ENERC_KCAL: NutrientModel?,
    @PropertyName("fat")
    val FAT: NutrientModel?,
    @PropertyName("fasat")
    val FASAT: NutrientModel?,
    @PropertyName("fatrn")
    val FATRN: NutrientModel?,
    @PropertyName("fams")
    val FAMS: NutrientModel?,
    @PropertyName("fapu")
    val FAPU: NutrientModel?,
    @PropertyName("chocdf")
    val CHOCDF: NutrientModel?,
    @PropertyName("fibtg")
    val FIBTG: NutrientModel?,
    @PropertyName("sugar")
    val SUGAR: NutrientModel?,
    @PropertyName("procnt")
    val PROCNT: NutrientModel?,
    @PropertyName("chole")
    val CHOLE: NutrientModel?,
    @PropertyName("na")
    val NA: NutrientModel?,
    @PropertyName("ca")
    val CA: NutrientModel?,
    @PropertyName("mg")
    val MG: NutrientModel?,
    @PropertyName("k")
    val K: NutrientModel?,
    @PropertyName("fe")
    val FE: NutrientModel?,
    @PropertyName("zn")
    val ZN: NutrientModel?,
    @PropertyName("p")
    val P: NutrientModel?,
    @PropertyName("vita_RAE")
    val VITA_RAE: NutrientModel?,
    @PropertyName("vitc")
    val VITC: NutrientModel?,
    @PropertyName("thia")
    val THIA: NutrientModel?,
    @PropertyName("ribf")
    val RIBF: NutrientModel?,
    @PropertyName("nia")
    val NIA: NutrientModel?,
    @PropertyName("vitb76A")
    val VITB6A: NutrientModel?,
    @PropertyName("foldfe")
    val FOLDFE: NutrientModel?,
    @PropertyName("folfd")
    val FOLFD: NutrientModel?,
    @PropertyName("folac")
    val FOLAC: NutrientModel?,
    @PropertyName("vitb12")
    val VITB12: NutrientModel?,
    @PropertyName("vitd")
    val VITD: NutrientModel?,
    @PropertyName("tocpha")
    val TOCPHA: NutrientModel?,
    @PropertyName("vitk1")
    val VITK1: NutrientModel?,
    @PropertyName("water")
    val WATER: NutrientModel?
) : LocalModel, Parcelable {
    constructor() : this (
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
}
