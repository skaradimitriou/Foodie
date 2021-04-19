package com.stathis.foodie.ui.categories

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.stathis.foodie.R
import com.stathis.foodie.adapters.RecipeAdapter
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.models.ShimmerModel
import com.stathis.foodie.models.SuggestionItem

class CategoriesResultsViewModel : ViewModel(), ItemClickListener {

    private val repo = CategoriesResultsRepository()
    val data = repo.data
    val isLoading = MutableLiveData<Boolean>()
    val adapter = RecipeAdapter(this)
    private lateinit var callback: RecipeClickListener

    init {
        adapter.submitList(
            listOf(
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel()
            )
        )
    }

    fun getResults(cuisineType: String) {
        isLoading.value = true
        val cleanCuisineType = cuisineType.replace("\\s".toRegex(), "")
        when (cleanCuisineType) {
            "breakfast", "lunch","dinner","snack","teatime" -> repo.getMealTypeResults(cleanCuisineType)
            "soup", "salad", "dessert","cereals","pancake","starter","omelet" -> repo.getDishTypeResults(cleanCuisineType)
            "indian", "chinese", "italian","mexican","japanese" -> repo.getCuisineTypeResults(cleanCuisineType)
            else -> Unit
        }
    }

    fun observeData(owner: LifecycleOwner, callback: RecipeClickListener) {
        this.callback = callback

        data.observe(owner, Observer {
            isLoading.value = false
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }

    fun removeObserve(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    fun loadMoreRecipes(cuisineType: String) {
        getResults(cuisineType)
    }

    fun clearCounters() {
        repo.clearCounters()
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is RecipeMain -> {
                callback.onRecipeClick(view.tag as RecipeMain)
            }
        }
    }
}
