package com.stathis.foodie.ui.categories

import androidx.lifecycle.MutableLiveData
import com.stathis.foodie.APP_ID
import com.stathis.foodie.APP_KEY
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.models.ResponseModel
import com.stathis.foodie.network.ApiClient
import com.stathis.foodie.utils.ApiPagingHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NullPointerException

class CategoriesResultsRepository {

    val data = MutableLiveData<List<RecipeMain>>()
    private var recipes: MutableList<RecipeMain> = arrayListOf()
    private val apiPager = ApiPagingHelper()

    fun getCuisineTypeResults(cuisineType: String) {
        apiPager.incrementCounters()

        ApiClient.getCuisineTypeResults(
            apiPager.from,
            apiPager.to,
            "",
            cuisineType,
            APP_ID,
            APP_KEY
        ).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                response.body()!!.hits.forEach {
                    recipes.add(it)
                }

                data.value = recipes
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                data.value = null
            }
        })
    }

    fun getMealTypeResults(mealType: String) {
        apiPager.incrementCounters()

        ApiClient.getMealTypeResults(apiPager.from, apiPager.to, "", mealType, APP_ID, APP_KEY)
            .enqueue(object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    response.body()!!.hits.forEach {
                        recipes.add(it)
                    }

                    data.value = recipes
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    data.value = null
                }
            })
    }

    fun getDishTypeResults(dishType: String) {
        apiPager.incrementCounters()

        ApiClient.getDishTypeResults(apiPager.from, apiPager.to, "", dishType, APP_ID, APP_KEY)
            .enqueue(object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    response.body()!!.hits.forEach {
                        recipes.add(it)
                    }

                    data.value = recipes
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    data.value = null
                }
            })
    }

    fun clearCounters() {
        apiPager.clearCounters()
    }
}
