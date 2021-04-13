package com.stathis.foodie.ui.results

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

class FilterResultsRepository {

    val data = MutableLiveData<List<RecipeMain>>()
    private var recipes: MutableList<RecipeMain> = arrayListOf()
    private val apiPager = ApiPagingHelper()

    fun getDataFromApi(
        kcalMinValue: Int,
        kcalMaxValue: Int,
        mealType: String,
        dietType: String
    ) {
        apiPager.incrementCounters()

        ApiClient.getCustomRecipes(apiPager.from,apiPager.to,
            "",
            "${kcalMinValue}-${kcalMaxValue}",
            mealType,
            dietType,
            APP_ID,
            APP_KEY
        ).enqueue(object : Callback<ResponseModel> {
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
}