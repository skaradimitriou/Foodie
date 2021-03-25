package com.stathis.foodie.ui.dashboard.main

import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.stathis.foodie.adapters.RecipeAdapter
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain
import java.util.*

class MainFragmentViewModel : ViewModel(), ItemClickListener {

    private lateinit var callback: RecipeClickListener
    private val repo = MainFragmentRepository()
    val recipes = repo.recipes
    val username = repo.username
    val userGreetingMessage = MutableLiveData<String>()
    val adapter = RecipeAdapter(this)

    fun observeData(owner: LifecycleOwner) {
        recipes.observe(owner, Observer {
            Log.d("data is", "data is $it")
            adapter.submitList(it.hits)
        })
    }

    fun removeObserver(owner: LifecycleOwner) {
        recipes.removeObservers(owner)
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is RecipeMain -> {
                callback.onRecipeClick(view.tag as RecipeMain)
            }
        }
    }

    fun getTimeOfDay(callback: RecipeClickListener) {
        this.callback = callback
        val calendar = Calendar.getInstance()

        when (calendar.get(Calendar.HOUR_OF_DAY)) {
            in 1..11 -> {
                userGreetingMessage.value = "Good Morning"
                repo.getMealTypeResults("breakfast")
            }
            in 12..15 -> {
                userGreetingMessage.value = "Good Afternoon"
                repo.getMealTypeResults("lunch")
            }
            in 16..24 -> {
                userGreetingMessage.value = "Good Evening"
                repo.getMealTypeResults("dinner")
            }
            else -> Unit
        }
    }

    fun getUsername() {
        repo.getUsername()
    }
}