package com.stathis.foodie.abstraction

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.stathis.foodie.models.LocalModel

class DiffUtilClass<T : LocalModel> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}