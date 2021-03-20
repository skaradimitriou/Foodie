package com.stathis.foodie.ui.dashboard.main

import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.stathis.foodie.adapters.RecipeAdapter
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.MainFragmentListener
import com.stathis.foodie.models.RecipeMain

class MainFragmentViewModel : ViewModel(), ItemClickListener {

    private lateinit var callback: MainFragmentListener
    private val repo = MainFragmentRepository()
    val data = repo.data
    val adapter = RecipeAdapter(this)

    fun getDataFromRepository(callback: MainFragmentListener) {
        this.callback = callback
        repo.getDataFromApi()
    }

    fun observeData(owner: LifecycleOwner) {
        data.observe(owner, Observer {
            Log.d("data is", "data is $it")
            adapter.submitList(it.hits)
        })
    }

    fun removeObserver(owner: LifecycleOwner) {
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