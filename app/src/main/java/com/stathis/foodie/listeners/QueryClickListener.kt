package com.stathis.foodie.listeners

import com.stathis.foodie.models.QueryModel

interface QueryClickListener {

    fun onQueryClick(query : QueryModel)
}