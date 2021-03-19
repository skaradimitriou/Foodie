package com.stathis.foodie.abstraction

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.models.LocalModel

abstract class AbstractViewHolder(itemView: View, callback : ItemClickListener? = null) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            callback?.onItemClick(it)
        }
    }

    abstract fun present(localModel: LocalModel, callback : ItemClickListener)
}