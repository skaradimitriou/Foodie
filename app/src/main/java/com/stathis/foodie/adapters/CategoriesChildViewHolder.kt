package com.stathis.foodie.adapters

import android.view.View
import com.stathis.foodie.abstraction.AbstractViewHolder
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.models.LocalModel
import com.stathis.foodie.models.SuggestionItem
import kotlinx.android.synthetic.main.holder_category_item_row.view.*

class CategoriesChildViewHolder(itemView : View,callback : ItemClickListener) : AbstractViewHolder(itemView,callback) {

    override fun presentData(data: LocalModel) {
        when(data){
            is SuggestionItem -> {
                itemView.category_img.setImageResource(data.categoryImg)
                itemView.category_txt.text = data.categoryName
            }
        }
    }
}
