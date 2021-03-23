package com.stathis.foodie.ui.dashboard.profile

import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.stathis.foodie.adapters.FavoriteAdapter
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain

class ProfileViewModel : ViewModel(), ItemClickListener {

    private val repo = ProfileRepository()
    val data = repo.data
    val userEmail = repo.userEmail
    val userImageLink = repo.userImageLink
    val adapter = FavoriteAdapter(this)
    private lateinit var callback: RecipeClickListener

    fun getFavoriteData(callback: RecipeClickListener) {
        this.callback = callback
        repo.getUserFavorites()
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is RecipeMain -> {
                callback.onRecipeClick(view.tag as RecipeMain)
            }
        }
    }

    fun observe(owner: LifecycleOwner) {
        data.observe(owner, Observer {
            adapter.submitList(it)
        })
    }

    fun removeObservers(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    fun saveCameraPhotoToDb(imgBitmap: Bitmap) {
        repo.saveCameraPhotoToDb(imgBitmap)
    }

    fun saveGalleryPhotoToDb(imageUri: Uri) {
        repo.saveGalleryPhotoToDb(imageUri)
    }

    fun getUserPhoto() {
        repo.getUserPhoto()
    }

    fun getUserProfileData() {
        repo.getUserProfileData()
    }
}
