package com.stathis.foodie.ui.onboarding

import androidx.lifecycle.ViewModel
import com.stathis.foodie.R
import com.stathis.foodie.models.OnboardingItem
import com.stathis.foodie.ui.onboarding.adapter.OnboardingAdapter

class OnboardingRepository : ViewModel() {

    val adapter = OnboardingAdapter()

    init {
        adapter.submitList(
            listOf(
                OnboardingItem(
                    R.drawable.onboarding_icon_one,
                    "Find your next recipe",
                    "You can now find your next recipe according to what you desire.Search over 2 million recipes by diets, calories and nutrient ranges"
                ),
                OnboardingItem(
                    R.drawable.onboarding_two_icon,
                    "Add recipes to your favorites",
                    "You can now save your favorite recipes. You can now access your favorite recipes in the Favorites section."
                ),
                OnboardingItem(
                    R.drawable.onboarding_icon_three,
                    "Share a recipe",
                    "Do you want to send a recipe to someone? We got you covered. You can share a recipe by clicking the share button."
                )
            )
        )
    }
}