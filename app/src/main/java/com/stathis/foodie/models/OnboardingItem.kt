package com.stathis.foodie.models

import com.stathis.foodie.models.LocalModel

data class OnboardingItem(
    val onboardingImg: Int,
    val title: String,
    val description: String
) : LocalModel