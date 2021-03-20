package com.stathis.foodie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.DiffUtilClass
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.models.LocalModel

class FavoriteAdapter(private val callback: ItemClickListener) :
    ListAdapter<LocalModel, FavoriteViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.holder_favorite_item, parent, false), callback
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}