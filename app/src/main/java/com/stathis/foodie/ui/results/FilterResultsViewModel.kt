package com.stathis.foodie.ui.results

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.stathis.foodie.adapters.RecipeAdapter
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain

class FilterResultsViewModel : ViewModel(), ItemClickListener {

    private val repo = FilterResultsRepository()
    val data = repo.data
    private lateinit var callback: RecipeClickListener
    val adapter = RecipeAdapter(this)

    fun getRecipeData(
        kcalMinValue: Int,
        kcalMaxValue: Int,
        mealType: String,
        dietType: String,
        callback: RecipeClickListener
    ) {
        this.callback = callback
        repo.getDataFromApi(kcalMinValue, kcalMaxValue, mealType, dietType)
    }

    fun observeData(owner: LifecycleOwner) {
        data.observe(owner, Observer {
            adapter.submitList(it.hits)
        })
    }

    fun removeObservers(owner: LifecycleOwner) {
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
