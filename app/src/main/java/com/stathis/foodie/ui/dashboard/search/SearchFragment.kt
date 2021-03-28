package com.stathis.foodie.ui.dashboard.search

import android.app.DownloadManager
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractFragment
import com.stathis.foodie.listeners.QueryClickListener
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.QueryModel
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : AbstractFragment(R.layout.fragment_search) {

    private lateinit var viewModel: SearchViewModel

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun running() {
        viewModel.getRecentUserQueries()

        search_searchbar.setOnClickListener {
            search_searchbar.isIconified = false
        }

        search_searchbar.clearFocus()

        search_searchbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search_searchbar.clearFocus()
                search_searchbar.setQuery("", false)
                Log.d("HELLO", query)

                viewModel.addQueryToDb(QueryModel(query!!))
                callApiForResuls(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        search_recycler.adapter = viewModel.adapter

        viewModel.observeData(this,object : QueryClickListener{
            override fun onQueryClick(query: QueryModel) {
                callApiForResuls(query.queryName)
            }
        })
    }

    override fun stop() {
        viewModel.removeObservers(this)
    }

    private fun callApiForResuls(query : String){
        viewModel.getDataFromRepository(query, object : RecipeClickListener {
            override fun onRecipeClick(recipe: RecipeMain) {
                startActivity( Intent(requireContext(), DetailsActivity::class.java)
                    .putExtra("RECIPE", recipe))
            }
        })
    }
}