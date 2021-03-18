package com.stathis.foodie.network

import com.stathis.foodie.models.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodieApi {

    @GET("search")
    fun getRecipes (
        @Query("q") query : String,
        @Query("app_id") app_id : String,
        @Query("app_key") app_key : String
    ): Call<ResponseModel>
}