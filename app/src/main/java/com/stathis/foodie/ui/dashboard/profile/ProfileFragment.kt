package com.stathis.foodie.ui.dashboard.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractFragment
import com.stathis.foodie.listeners.RecipeClickListener
import com.stathis.foodie.models.RecipeMain
import com.stathis.foodie.ui.details.DetailsActivity
import com.stathis.foodie.ui.editProfile.EditProfileActivity
import com.stathis.foodie.ui.intro.IntroActivity
import com.stathis.foodie.ui.onboarding.OnboardingActivity
import kotlinx.android.synthetic.main.bottom_sheet_choose_option.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.logout_item.view.*


class ProfileFragment : AbstractFragment(R.layout.fragment_profile) {

    private lateinit var viewModel: ProfileViewModel
    private val REQUEST_IMAGE_CAPTURE = 100
    private val IMAGE_PICK_CODE = 200
    private val PERMISSION_CODE = 201

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun running() {
        viewModel.getUserPhoto()
        viewModel.getUserProfileData()

        profile_email.text = getString(R.string.profile_dummy_email)

        profile_photo.setOnClickListener {
            showUploadPhotoOptions()
        }

        edit_profile_btn.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
        }

        logout.apply {
            this.logout_icon.setOnClickListener { askForLogout() }
            this.logout_text.setOnClickListener { askForLogout() }
        }

        profile_favorites_recycler.adapter = viewModel.adapter

        viewModel.getFavoriteData(object : RecipeClickListener {
            override fun onRecipeClick(recipe: RecipeMain) {
                startActivity(
                    Intent(requireContext(), DetailsActivity::class.java)
                        .putExtra("RECIPE", recipe)
                )
            }
        })

        viewModel.observe(this)
        viewModel.userEmail.observe(this, Observer {
            profile_email.text = it
        })

        viewModel.userImageLink.observe(this, Observer {
            Glide.with(this).load(it).into(profile_photo)
        })

        viewModel.username.observe(this, Observer {
            profile_username.text = it
        })
    }

    override fun stop() {
        viewModel.removeObservers(this)
        viewModel.userEmail.removeObservers(this)
        viewModel.userImageLink.removeObservers(this)
        viewModel.username.removeObservers(this)
    }

    private fun showUploadPhotoOptions() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_choose_option, null)
        val dialog = BottomSheetDialog(view.context)
        dialog.setContentView(view)
        dialog.show()
        view.uploadFromCamera.setOnClickListener {
            takePictureIntent()
            dialog.dismiss()
        }
        view.uploadFromGallery.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (activity?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    uploadFromGallery()
                }
            } else {
                uploadFromGallery()
            }
            dialog.dismiss()
        }
    }

    private fun takePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { pictureIntent ->
            pictureIntent.resolveActivity(activity?.packageManager!!)?.also {
                startActivityForResult(pictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun uploadFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imgBitmap = data?.extras?.get("data") as Bitmap
            viewModel.saveCameraPhotoToDb(imgBitmap)

        } else if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            // I have to save the url to the db
            val imageUri = data?.data
            if (imageUri != null) {
                viewModel.saveGalleryPhotoToDb(imageUri)
                viewModel.getUserPhoto()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                    uploadFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun askForLogout() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
        builder.setMessage("Do you want to log out?")

        builder.setPositiveButton("YES") { dialog, which ->
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(requireContext(), IntroActivity::class.java))
        }

        builder.setNegativeButton("CANCEL") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
}