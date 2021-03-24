package com.stathis.foodie.ui.categories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.stathis.foodie.APP_ID
import com.stathis.foodie.APP_KEY
import com.stathis.foodie.models.ResponseModel
import com.stathis.foodie.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesResultsRepository {

    val data = MutableLiveData<ResponseModel>()

    fun getCuisineTypeResults(cuisineType : String){
        ApiClient.getCuisineTypeResults("", cuisineType, APP_ID, APP_KEY).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                data.value = null
            }
        })
    }

    fun getMealTypeResults(mealType : String){
        ApiClient.getMealTypeResults("", mealType, APP_ID, APP_KEY).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                data.value = null
            }
        })
    }

    fun getDishTypeResults(dishType : String){
        ApiClient.getDishTypeResults("", dishType, APP_ID, APP_KEY).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                data.value = null
            }
        })
    }
}
