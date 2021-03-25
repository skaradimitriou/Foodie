package com.stathis.foodie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.DiffUtilClass
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.models.HomeCategoryItem
import com.stathis.foodie.models.LocalModel
import com.stathis.foodie.models.SuggestionItem

class CategoriesAdapter(private val callback: ItemClickListener) :
    ListAdapter<LocalModel, CategoriesViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return CategoriesViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SuggestionItem -> R.layout.holder_category_item_row
        is HomeCategoryItem -> R.layout.holder_categories_item_row
        else -> R.layout.holder_empty_item
    }
}