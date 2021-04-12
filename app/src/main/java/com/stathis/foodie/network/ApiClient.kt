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

    fun getRecipesByPage(
        from: Int,
        to: Int,
        recipe: String,
        app_id: String,
        app_key: String
    ): Call<ResponseModel> {
        return api.getRecipesByPage(from, to, recipe, app_id, app_key)
    }

    fun getCustomRecipes(
        query: String,
        calories: String,
        mealType: String,
        dietType: String,
        app_id: String,
        app_key: String
    ): Call<ResponseModel> {
        return api.getCustomRecipes(query, calories, mealType, dietType, app_id, app_key)
    }

    fun getCuisineTypeResults(
        from: Int,
        to: Int,
        query: String,
        cuisineType: String,
        app_id: String,
        app_key: String
    ): Call<ResponseModel> {
        return api.getCuisineTypeResults(from,to,query, cuisineType, app_id, app_key)
    }

    fun getMealTypeResults(
        from: Int,
        to: Int,
        query: String,
        mealType: String,
        app_id: String,
        app_key: String
    ): Call<ResponseModel> {
        return api.getMealTypeResults(from,to,query, mealType, app_id, app_key)
    }

    fun getDishTypeResults(
        from: Int,
        to: Int,
        query: String,
        dishType: String,
        app_id: String,
        app_key: String
    ): Call<ResponseModel> {
        return api.getDishTypeResults(from,to,query, dishType, app_id, app_key)
    }
}