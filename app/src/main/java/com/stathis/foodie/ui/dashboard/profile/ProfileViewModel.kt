package com.stathis.foodie.ui.dashboard.profile

import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.stathis.foodie.adapters.FavoriteAdapter
import com.stathis.foodie.listeners.ItemClickListener
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.EmptyFavoriteModel
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.models.ShimmerModel

class ProfileViewModel : ViewModel(), ItemClickListener {

    private val repo = ProfileRepository()
    val data = repo.data
    val emptyFavorites = repo.emptyFavorites
    val userEmail = repo.userEmail
    val userImageLink = repo.userImageLink
    val username = repo.username
    val adapter = FavoriteAdapter(this)
    private lateinit var callback: RecipeClickListener

    init {
        adapter.submitList(listOf(ShimmerModel(), ShimmerModel(), ShimmerModel()))
    }

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
            adapter.notifyDataSetChanged()
        })

        emptyFavorites.observe(owner, Observer{
            when(it){
                true -> {
                    adapter.submitList(listOf(EmptyFavoriteModel()))
                    adapter.notifyDataSetChanged()
                }
                false -> Unit
            }
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
