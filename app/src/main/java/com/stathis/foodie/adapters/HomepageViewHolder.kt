package com.stathis.foodie.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractViewHolder
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.models.LocalModel
import com.stathis.foodie.models.RecipeMain
import kotlinx.android.synthetic.main.holder_homepage_carousel_item.view.*

class HomepageViewHolder(itemView : View, callback : ItemClickListener) : AbstractViewHolder(itemView,callback) {

    override fun presentData(data: LocalModel) {
        when(data){
            is RecipeMain -> {
                itemView.recipe_carousel_recipe_name.text = data.recipe.label
                Glide.with(itemView).load(data.recipe.image).placeholder(R.color.orange)
                    .into(itemView.recipe_carousel_img)
            }
        }
    }
}