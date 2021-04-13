package com.stathis.foodie.network

import com.stathis.foodie.models.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodieApi {

    @GET("search")
    fun getRecipes(
        @Query("q") query: String,
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String
    ): Call<ResponseModel>

    @GET("search")
    fun getRecipesByPage(
        @Query("from") from: Int,
        @Query("to") to: Int,
        @Query("q") query: String,
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String
    ): Call<ResponseModel>

    @GET("search")
    fun getCustomRecipes(
        @Query("from") from: Int,
        @Query("to") to: Int,
        @Query("q") query: String,
        @Query("calories") calories: String,
        @Query("mealType") mealType: String,
        @Query("diet") dietType: String,
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String
    ): Call<ResponseModel>

    @GET("search")
    fun getCuisineTypeResults(
        @Query("from") from: Int,
        @Query("to") to: Int,
        @Query("q") query: String,
        @Query("cuisineType") cuisineType: String,
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String
    ): Call<ResponseModel>

    @GET("search")
    fun getMealTypeResults(
        @Query("from") from: Int,
        @Query("to") to: Int,
        @Query("q") query: String,
        @Query("mealType") mealType: String,
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String
    ): Call<ResponseModel>

    @GET("search")
    fun getDishTypeResults(
        @Query("from") from: Int,
        @Query("to") to: Int,
        @Query("q") query: String,
        @Query("dishType") dishType: String,
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String
    ): Call<ResponseModel>
}