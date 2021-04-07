package com.stathis.foodie.network

import androidx.annotation.WorkerThread
import com.stathis.foodie.models.ResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
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
        query: String,
        cuisineType: String,
        app_id: String,
        app_key: String
    ): Call<ResponseModel> {
        return api.getCuisineTypeResults(query, cuisineType, app_id, app_key)
    }

    fun getMealTypeResults(
        query: String,
        mealType: String,
        app_id: String,
        app_key: String
    ): Call<ResponseModel> {
        return api.getMealTypeResults(query, mealType, app_id, app_key)
    }

    fun getDishTypeResults(
        query: String,
        dishType: String,
        app_id: String,
        app_key: String
    ): Call<ResponseModel> {
        return api.getDishTypeResults(query, dishType, app_id, app_key)
    }
}