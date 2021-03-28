package com.stathis.foodie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.DiffUtilClass
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.models.EmptyModel
import com.stathis.foodie.models.LocalModel
import com.stathis.foodie.models.QueryModel
import com.stathis.foodie.models.RecipeMain

class SearchAdapter(private val callback: ItemClickListener) :
    ListAdapter<LocalModel, SearchViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return SearchViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is RecipeMain -> R.layout.holder_search_results_card_item
        is QueryModel -> R.layout.holder_search_results_item
        is EmptyModel -> R.layout.holder_empty_query_item
        else -> R.layout.holder_empty_item
    }
}