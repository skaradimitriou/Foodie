package com.stathis.foodie.ui.dashboard.search

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.stathis.foodie.adapters.SearchAdapter
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.QueryClickListener
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.EmptyModel
import com.stathis.foodie.models.QueryModel
import com.stathis.foodie.models.RecipeMain

class SearchViewModel : ViewModel(), ItemClickListener {

    private val repo = SearchRepository()
    val data = repo.data
    val recentQueries = repo.recentQueries
    val emptyQueries = repo.emptyQueries
    private lateinit var callback: RecipeClickListener
    private lateinit var queryCallback: QueryClickListener
    val adapter = SearchAdapter(this)

    fun getDataFromRepository(query: String, callback: RecipeClickListener) {
        this.callback = callback
        repo.getDataFromApi(query)
    }

    fun observeData(owner: LifecycleOwner, queryCallback: QueryClickListener) {
        this.queryCallback = queryCallback

        data.observe(owner, Observer {
            it?.let {
                adapter.submitList(it.hits)
                adapter.notifyDataSetChanged()
            }
        })

        recentQueries.observe(owner, Observer {
            it?.let { adapter.submitList(it) }
        })

        emptyQueries.observe(owner, Observer {
            when(it){
                true -> {
                    adapter.submitList(listOf(EmptyModel()))
                    adapter.notifyDataSetChanged()
                }
                false -> Unit
            }
        })
    }

    fun removeObservers(owner: LifecycleOwner) {
        data.removeObservers(owner)
        recentQueries.removeObservers(owner)
        emptyQueries.removeObservers(owner)
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