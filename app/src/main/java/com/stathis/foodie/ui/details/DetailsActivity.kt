package com.stathis.foodie.ui.details

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.ui.webview.WebviewActivity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AbstractActivity(R.layout.activity_details) {

    private lateinit var viewModel: DetailsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun running() {
        /*
        As a User, I want to :

        a) be able to view the recipe details
        b) be able to view the ingredients of this recipe
        c) be able to see the nutritional value of this recipe
        d) be able to add this recipe to my favorite list
        e) be able to remove this recipe to my favorite list

         */

        val item = intent.getParcelableExtra<RecipeMain?>("RECIPE")
        Log.d("Recipe Item", item.toString())

        presentRecipeData(item)
        viewModel.getCookTime(item.recipe.totalTime)

        share_btn.setOnClickListener {
            Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT, "Check out this recipe!")
                .putExtra(Intent.EXTRA_TEXT, "I think that you might be interested in ${item.recipe.label}")
                .putExtra(Intent.EXTRA_STREAM, item.recipe.url)
            startActivity(Intent.createChooser(intent, "Share with"));
        }

        like_btn.setOnClickListener {
            /*
            Logic : check if favorite. If it is add it if its not remove it
             */
        }

        open_recipe_btn.setOnClickListener {
            startActivity( Intent(this, WebviewActivity::class.java)
                    .putExtra("URL", item.recipe.url))
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.cookTime.observe(this, Observer {
            recipe_time.text = it
        })
    }

    private fun presentRecipeData(item: RecipeMain) {
        Glide.with(this).load(item.recipe.image)
            .placeholder(R.color.orange).into(recipe_details_img)

        recipe_detail_label.text = item.recipe.label
        recipe_time.text = item.recipe.totalTime.toString()
        calories.text = "%.2fg".format(item.recipe.calories)
        protein.text = "%.2fg".format(item.recipe.totalNutrients.FAMS?.quantity)
        carbs.text = "%.2fg".format(item.recipe.totalNutrients.CHOCDF?.quantity)
        fats.text = "%.2fg".format(item.recipe.totalNutrients.FAT?.quantity)
    }

    override fun stopped() {}
}