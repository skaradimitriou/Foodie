package com.stathis.foodie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.DiffUtilClass
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.models.LocalModel
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.models.ShimmerModel

class RecipeAdapter(private val callback: ItemClickListener) :
    ListAdapter<LocalModel, RecipeViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return RecipeViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is RecipeMain -> R.layout.holder_recipe_item_row
        is ShimmerModel -> R.layout.holder_shimmer_recipe_item_row
        else -> R.layout.holder_empty_item
    }
}