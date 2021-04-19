package com.stathis.foodie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.DiffUtilClass
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.models.LocalModel

class CategoriesChildAdapter(private val callback: ItemClickListener) :
    ListAdapter<LocalModel, CategoriesChildViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesChildViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_category_child_item_row, parent, false)
        return CategoriesChildViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: CategoriesChildViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}