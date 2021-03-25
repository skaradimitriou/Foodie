package com.stathis.foodie.ui.dashboard.search

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractFragment
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : AbstractFragment(R.layout.fragment_search) {

    private lateinit var viewModel: SearchViewModel

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        search_searchbar.isIconified = false
    }

    override fun running() {
        /*
        As a User, I want to :

        a) be able to search for a recipe  | DONE
        b) be able to view the results from my query vertically | DONE
        c) be able to click the results and navigate to the recipe screen | DONE
        d) be able to view my recent queries | TO DO
        e) be able to click to my recent queries and get results | TO DO

        Fix the bug that opens the keyboard when you click on searchview
         */

        search_searchbar.setOnClickListener {
            search_searchbar.isIconified = false
        }

        search_searchbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search_searchbar.clearFocus()
                search_searchbar.setQuery("", false)
                Log.d("HELLO", query)

                // The user searches for that query so we need to call the api for results
                viewModel.getDataFromRepository(query!!, object : RecipeClickListener {
                    override fun onRecipeClick(recipe: RecipeMain) {
                        startActivity( Intent(requireContext(), DetailsActivity::class.java)
                            .putExtra("RECIPE", recipe))
                    }
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        search_recycler.adapter = viewModel.adapter

        viewModel.observeData(this)
    }

    override fun stop() {
        viewModel.removeObservers(this)
    }
}