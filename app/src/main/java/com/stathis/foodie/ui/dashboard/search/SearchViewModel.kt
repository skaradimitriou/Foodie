package com.stathis.foodie.ui.dashboard.search

import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.stathis.foodie.adapters.SearchAdapter
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain

class SearchViewModel : ViewModel(), ItemClickListener {

    private val repo = SearchRepository()
    val data = repo.data
    private lateinit var callback: RecipeClickListener
    val adapter = SearchAdapter(this)

    fun getDataFromRepository(query: String, callback: RecipeClickListener) {
        this.callback = callback
        repo.getDataFromApi(query)
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