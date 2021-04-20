package com.stathis.foodie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.DiffUtilClass
import com.stathis.foodie.models.LocalModel

class RecipeIngredientsAdapter() :
    ListAdapter<LocalModel, RecipeIngredientsViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeIngredientsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_details_ingredient_item_row, parent, false)
        return RecipeIngredientsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeIngredientsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}