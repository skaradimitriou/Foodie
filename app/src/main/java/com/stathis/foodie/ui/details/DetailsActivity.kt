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
        val item = intent.getParcelableExtra<RecipeMain?>("RECIPE")
        Log.d("Recipe Item", item.toString())

        Glide.with(this).load(item?.recipe?.image).into(recipe_detail_img)
        recipe_detail_label.text = item?.recipe?.label
    }

    override fun stopped() {}
}