package com.stathis.foodie.listeners

import com.stathis.foodie.models.SuggestionItem

interface SuggestionItemClickListener {

    fun onCategoryClick(category : SuggestionItem)
}