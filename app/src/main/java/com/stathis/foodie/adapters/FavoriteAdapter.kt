package com.stathis.foodie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.facebook.shimmer.Shimmer
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.DiffUtilClass
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.models.EmptyFavoriteModel
import com.stathis.foodie.models.LocalModel
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.models.ShimmerModel

class FavoriteAdapter(private val callback: ItemClickListener) :
    ListAdapter<LocalModel, FavoriteViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return FavoriteViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is RecipeMain -> R.layout.holder_favorite_item
        is ShimmerModel -> R.layout.holder_shimmer_favorite_item
        is EmptyFavoriteModel -> R.layout.holder_empty_favorite_item
        else -> R.layout.holder_empty_item
    }
}