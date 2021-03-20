package com.stathis.foodie.ui.dashboard.main

import androidx.lifecycle.MutableLiveData
import com.stathis.foodie.APP_ID
import com.stathis.foodie.APP_KEY
import com.stathis.foodie.models.ResponseModel
import com.stathis.foodie.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentRepository {

    val data = MutableLiveData<ResponseModel>()

    fun getDataFromApi(){
        ApiClient.getRecipes("chicken", APP_ID, APP_KEY).enqueue(object : Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                data.value = null
            }
        })
    }
}