package com.stathis.foodie.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.stathis.foodie.abstraction.AbstractViewHolder
import com.stathis.foodie.models.IngredientModel
import com.stathis.foodie.models.LocalModel
import kotlinx.android.synthetic.main.holder_details_ingredient_item_row.view.*

class RecipeIngredientsViewHolder(itemView : View) : AbstractViewHolder(itemView) {

    override fun presentData(data: LocalModel) {
        when(data){
            is IngredientModel -> {
                Glide.with(itemView).load(data.image).into(itemView.ingredient_img)
                itemView.ingredient_name.text = data.text
            }
        }
    }
}