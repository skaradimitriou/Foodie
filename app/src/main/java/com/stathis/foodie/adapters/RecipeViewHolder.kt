package com.stathis.foodie.adapters

import android.view.View
import com.stathis.foodie.abstraction.AbstractViewHolder
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.models.LocalModel

class RecipeViewHolder(itemView : View) : AbstractViewHolder(itemView) {

    override fun present(localModel: LocalModel, callback: ItemClickListener) {
        when(localModel){

        }
    }
}