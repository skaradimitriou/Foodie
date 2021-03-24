package com.stathis.foodie.ui.categories

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.stathis.foodie.R
import com.stathis.foodie.adapters.RecipeAdapter
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.models.SuggestionItem

class CategoriesResultsViewModel : ViewModel(), ItemClickListener {

    private val repo = CategoriesResultsRepository()
    val data = repo.data
    val adapter = RecipeAdapter(this)
    private lateinit var callback: RecipeClickListener

    fun getResults(cuisineType: String) {
        when (cuisineType) {
            "breakfast", "lunch" -> repo.getMealTypeResults(cuisineType)
            "soup", "salad", "dessert" -> repo.getDishTypeResults(cuisineType)
            "indian", "chinese", "italian" -> repo.getCuisineTypeResults(cuisineType)
            else -> Unit
        }
    }

    fun observeData(owner: LifecycleOwner, callback: RecipeClickListener) {
        this.callback = callback

        data.observe(owner, Observer {
            adapter.submitList(it.hits)
        })
    }

    fun removeObserve(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is RecipeMain -> {
                callback.onRecipeClick(view.tag as RecipeMain)
            }
        }
    }
}
