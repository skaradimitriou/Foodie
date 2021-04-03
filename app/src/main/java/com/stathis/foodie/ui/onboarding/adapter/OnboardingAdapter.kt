package com.stathis.foodie.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.DiffUtilClass
import com.stathis.foodie.models.LocalModel

class OnboardingAdapter : ListAdapter<LocalModel, OnboardingViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_onboarding_item, parent, false)
        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) =
        holder.present(getItem(position))
}