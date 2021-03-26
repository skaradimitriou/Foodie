package com.stathis.foodie.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractViewHolder
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.models.LocalModel
import com.stathis.foodie.models.QueryModel
import com.stathis.foodie.models.RecipeMain
import kotlinx.android.synthetic.main.holder_search_results_card_item.view.*
import kotlinx.android.synthetic.main.holder_search_results_item.view.*

class SearchViewHolder(itemView: View, callback: ItemClickListener) :
    AbstractViewHolder(itemView, callback) {

    override fun presentData(data: LocalModel) {
        when (data) {
            is RecipeMain -> {
                Glide.with(itemView).load(data.recipe.image).placeholder(R.mipmap.foodie_app_icon).into(itemView.recipe_card_item_img)
                itemView.recipe_card_item_label.text = data.recipe.label
            }

            is QueryModel -> {
                itemView.search_results_item_label.text = data.queryName
            }
        }
    }
}