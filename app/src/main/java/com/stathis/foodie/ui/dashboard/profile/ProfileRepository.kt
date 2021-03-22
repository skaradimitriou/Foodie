package com.stathis.foodie.ui.dashboard.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.stathis.foodie.APP_ID
import com.stathis.foodie.APP_KEY
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.models.ResponseModel
import com.stathis.foodie.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRepository {

    private val databaseReference : DatabaseReference by lazy { FirebaseDatabase.getInstance().reference }
    val data = MutableLiveData<List<RecipeMain>>()
    private val favoriteRecipeList = mutableListOf<RecipeMain>()

    fun getUserFavorites(){
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("favoriteRecipeList")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    data.value = null
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()) {
                        p0.children.forEach {
                            val fav = it.getValue(RecipeMain::class.java)
                            favoriteRecipeList.add(fav!!)
                            Log.d("it", it.toString())
                        }
                        data.value = favoriteRecipeList
                    }
                }
            })
    }
}