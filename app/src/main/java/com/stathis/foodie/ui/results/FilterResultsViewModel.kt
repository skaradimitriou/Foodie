package com.stathis.foodie.ui.results

import android.view.View
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar
import com.stathis.foodie.adapters.RecipeAdapter
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.models.ShimmerModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilterResultsViewModel : ViewModel(), ItemClickListener {

    private val repo = FilterResultsRepository()
    val data = repo.data
    val errorCase = repo.errorData
    private lateinit var callback: RecipeClickListener
    val adapter = RecipeAdapter(this)

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
            when(it.size){
                0 -> Unit
                else -> {
                    adapter.submitList(it)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    fun removeObservers(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    fun clearCounters() {
        repo.clearCounters()
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is RecipeMain -> callback.onRecipeClick(view.tag as RecipeMain)
        }
    }
}
