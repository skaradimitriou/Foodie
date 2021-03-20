package com.stathis.foodie.ui.details

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import com.stathis.foodie.models.RecipeMain
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

        Glide.with(this).load(item?.recipe?.image)
            .placeholder(R.color.orange).into(recipe_detail_img)

        recipe_detail_label.text = item?.recipe?.label
    }

    override fun stopped() {}
}