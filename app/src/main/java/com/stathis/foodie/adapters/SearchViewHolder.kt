package com.stathis.foodie.adapters

import android.view.View
import com.stathis.foodie.abstraction.AbstractViewHolder
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.models.LocalModel
import com.stathis.foodie.models.RecipeMain
import kotlinx.android.synthetic.main.holder_search_results_item.view.*

class SearchViewHolder(itemView: View, callback: ItemClickListener) :
    AbstractViewHolder(itemView, callback) {

    override fun presentData(data: LocalModel) {
        when (data) {
            is RecipeMain -> {
                itemView.search_results_item_label.text = data.recipe.label
            }
        }
    }
}