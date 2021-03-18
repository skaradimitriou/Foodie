package com.stathis.foodie.network

import com.stathis.foodie.models.ResponseModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val BASE_URL = "https://api.edamam.com/"
    private val api: FoodieApi

    init {
        api = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodieApi::class.java)
    }

    fun getRecipes(recipe: String, app_id: String, app_key: String): Call<ResponseModel> {
        return api.getRecipes(recipe, app_id, app_key)
    }
}