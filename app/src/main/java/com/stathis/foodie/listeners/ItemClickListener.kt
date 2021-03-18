package com.stathis.foodie.listeners

import com.stathis.foodie.models.LocalModel

interface ItemClickListener {

    fun onItemClick(localModel: LocalModel)
}