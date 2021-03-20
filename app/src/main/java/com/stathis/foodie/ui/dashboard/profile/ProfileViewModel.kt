package com.stathis.foodie.ui.dashboard.profile

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.stathis.foodie.adapters.FavoriteAdapter
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain

class ProfileViewModel : ViewModel(), ItemClickListener {

    private val repo = ProfileRepository()
    val data = repo.data
    val adapter = FavoriteAdapter(this)
    private lateinit var callback: RecipeClickListener

    fun getFavoriteData(string: String, callback: RecipeClickListener) {
        this.callback = callback
        repo.getDataFromApi(string)
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is RecipeMain -> {
                callback.onRecipeClick(view.tag as RecipeMain)
            }
        }
    }

    fun observe(owner: LifecycleOwner) {
        data.observe(owner, Observer {
            adapter.submitList(it.hits)
        })
    }

    fun removeObservers(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }
}
