package com.stathis.foodie.ui.dashboard.main

import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.stathis.foodie.adapters.CategoriesAdapter
import com.stathis.foodie.adapters.HomepageAdapter
import com.stathis.foodie.adapters.RecipeAdapter
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.listeners.SuggestionItemClickListener
import com.stathis.foodie.models.HomeCategoryItem
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.models.ShimmerModel
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

class MainFragmentViewModel : ViewModel(), ItemClickListener {

    private lateinit var callback: RecipeClickListener
    private lateinit var categoryCallback: SuggestionItemClickListener
    private val repo = MainFragmentRepository()
    val recipes = repo.recipes
    val username = repo.username
    val userGreetingMessage = MutableLiveData<String>()
    val categories = repo.homeCategories
    val adapter = HomepageAdapter(this)
    val categoryAdapter = CategoriesAdapter(this)
    val pageTransformer = MutableLiveData<CompositePageTransformer>()

    init {
        adapter.submitList(listOf(ShimmerModel(), ShimmerModel(), ShimmerModel()))
    }

    fun observeData(owner: LifecycleOwner, categoryCallback: SuggestionItemClickListener) {
        this.categoryCallback = categoryCallback

        recipes.observe(owner, Observer {
            Log.d("data is", "data is $it")

            adapter.submitList(it.hits)
            adapter.notifyDataSetChanged()
        })

        categories.observe(owner, Observer {
            Log.d("home categories", "home categories : $it")
            categoryAdapter.submitList(it)
        })
    }

    fun removeObserver(owner: LifecycleOwner) {
        recipes.removeObservers(owner)
        categories.removeObservers(owner)
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

    fun getHomeCategories() {
        repo.getHomeCategories()
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is RecipeMain ->  callback.onRecipeClick(view.tag as RecipeMain)
            is HomeCategoryItem -> categoryCallback.onHomeCategoryClick(view.tag as HomeCategoryItem)
        }
    }

    fun createPageTransformer() {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page: View, position: Float ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        pageTransformer.value = compositePageTransformer
    }
}