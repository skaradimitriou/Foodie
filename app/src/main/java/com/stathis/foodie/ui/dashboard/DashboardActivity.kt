package com.stathis.foodie.ui.dashboard

import android.util.Log
import com.stathis.foodie.APP_ID
import com.stathis.foodie.APP_KEY
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import com.stathis.foodie.models.ResponseModel
import com.stathis.foodie.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AbstractActivity(R.layout.activity_dashboard) {

    override fun init() {
        val recipe = "pizza"
        ApiClient.getRecipes(recipe, APP_ID, APP_KEY).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                Log.d("", response.body().toString())
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("", t.message.toString())
            }
        })
    }

    override fun running() {
        //
    }

    override fun stopped() {
        //
    }
}