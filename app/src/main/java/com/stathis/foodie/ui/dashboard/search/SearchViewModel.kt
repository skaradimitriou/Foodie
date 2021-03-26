package com.stathis.foodie.ui.dashboard.search

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.stathis.foodie.adapters.SearchAdapter
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.QueryClickListener
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.QueryModel
import com.stathis.foodie.models.RecipeMain

class SearchViewModel : ViewModel(), ItemClickListener {

    private val repo = SearchRepository()
    val data = repo.data
    val recentQueries = repo.recentQueries
    private lateinit var callback: RecipeClickListener
    private lateinit var queryCallback : QueryClickListener
    val adapter = SearchAdapter(this)

    fun getDataFromRepository(query: String, callback: RecipeClickListener) {
        this.callback = callback
        repo.getDataFromApi(query)
    }

    fun observeData(owner: LifecycleOwner, queryCallback : QueryClickListener) {
        this.queryCallback = queryCallback

        data.observe(owner, Observer {
            adapter.submitList(it.hits)
        })

        recentQueries.observe(owner, Observer {
            adapter.submitList(it)
        })
    }

    fun removeObservers(owner: LifecycleOwner) {
        data.removeObservers(owner)
        recentQueries.removeObservers(owner)
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is RecipeMain -> callback.onRecipeClick(view.tag as RecipeMain)
            is QueryModel -> queryCallback.onQueryClick(view.tag as QueryModel)
        }
    }

    fun getRecentUserQueries() {
        repo.getRecentUserQueries()
    }

    fun addQueryToDb(query: QueryModel) {
        repo.addQueryToDb(query)
    }
}