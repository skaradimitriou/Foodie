package com.stathis.foodie.ui.dashboard.search

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.stathis.foodie.APP_ID
import com.stathis.foodie.APP_KEY
import com.stathis.foodie.models.QueryModel
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.models.ResponseModel
import com.stathis.foodie.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository {

    private val databaseReference by lazy { FirebaseDatabase.getInstance().reference }
    val data = MutableLiveData<List<RecipeMain>>()
    val emptyQueries = MutableLiveData<Boolean>()
    val recentQueriesList = mutableListOf<QueryModel>()
    val recentQueries = MutableLiveData<List<QueryModel>>()

    private var testArray: MutableList<RecipeMain> = arrayListOf()

    private var noOfApiCalls = 0
    private var oldCounter = 0
    private var newCounter = 10

    fun getDataFromApi(query: String) {
        incrementCounters()

        ApiClient.getRecipesByPage(oldCounter, newCounter, query, APP_ID, APP_KEY)
            .enqueue(object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    response.body()!!.hits.forEach {
                        testArray.add(it)
                    }

                    data.value = testArray
                    emptyQueries.value = false
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    data.value = null
                }
            })
    }

    private fun incrementCounters() {
        noOfApiCalls += 1

        when (noOfApiCalls) {
            1 -> {
                oldCounter = 0
                newCounter = 10
            }
            else -> {
                oldCounter = (newCounter + 1)
                newCounter = oldCounter + 10
            }
        }
    }

    fun clearCounters() {
        noOfApiCalls = 0
        oldCounter = 0
        newCounter = 0
    }

    fun addQueryToDb(query: QueryModel) {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userSearchQueries")
            .child(query.queryName).setValue(query)
    }

    fun getRecentUserQueries() {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userSearchQueries")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    //
                }

                override fun onDataChange(p0: DataSnapshot) {
                    when (p0.exists()) {
                        true -> {
                            recentQueriesList.clear()
                            p0.children.forEach {
                                val query = it.getValue(QueryModel::class.java)
                                recentQueriesList.add(query!!)
                            }

                            recentQueries.value = recentQueriesList
                        }

                        false -> emptyQueries.value = true
                    }
                }
            })
    }
}