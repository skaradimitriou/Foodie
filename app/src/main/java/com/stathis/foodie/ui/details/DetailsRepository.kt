package com.stathis.foodie.ui.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.stathis.foodie.models.RecipeMain

class DetailsRepository {

    private var databaseReference : DatabaseReference = FirebaseDatabase.getInstance().reference
    val isFavoriteRecipe = MutableLiveData<Boolean>()

    fun isFavoriteRecipe(recipe: RecipeMain) {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("favoriteRecipeList")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    isFavoriteRecipe.value = null
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()) {
                        p0.children.forEach {
                            val fav = it.getValue(RecipeMain::class.java)
                            if (fav?.recipe?.label == recipe.recipe.label) {
                                isFavoriteRecipe.value = true
                            }
                            Log.d("it", it.toString())
                        }
                    }
                }
            })
    }

    fun addRecipeToFavorites(recipe: RecipeMain) {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("favoriteRecipeList")
            .child(recipe.recipe.label).setValue(recipe)
        isFavoriteRecipe.value = true
    }

    fun removeRecipeFromFavorites(recipe: RecipeMain) {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("favoriteRecipeList")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    //
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()) {
                        p0.children.forEach {
                            val favorite = it.getValue(RecipeMain::class.java)
                            if (favorite?.recipe?.label == recipe.recipe.label) {
                                it.ref.removeValue()
                                isFavoriteRecipe.value = false
                            }
                        }
                    }
                }
            })
    }
}