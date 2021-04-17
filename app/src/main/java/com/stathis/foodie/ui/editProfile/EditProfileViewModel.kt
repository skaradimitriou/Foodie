package com.stathis.foodie.ui.editProfile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EditProfileViewModel : ViewModel() {

    val auth = FirebaseAuth.getInstance()
    val database by lazy { FirebaseDatabase.getInstance().reference }
    val isUsernameEditable = MutableLiveData<Boolean>()
    val isEmailEditable = MutableLiveData<Boolean>()
    val isPhoneEditable = MutableLiveData<Boolean>()
    val userEmail = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    private var usernameEditable = false
    private var emailEditable = false
    private var phoneEditable = false

    fun getUserData() {
        userEmail.value = FirebaseAuth.getInstance().currentUser?.email.toString()
        getUserName()
        getUserPhoneNo()
    }

    fun makeUsernameEditable() {
        usernameEditable = !usernameEditable
        isUsernameEditable.value = usernameEditable
    }

    fun makeEmailEditable() {
        emailEditable = !emailEditable
        isEmailEditable.value = emailEditable
    }

    fun makePhoneEditable() {
        phoneEditable = !phoneEditable
        isPhoneEditable.value = phoneEditable
    }

    private fun getUserPhoneNo() {
        database.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userPhoneNo")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    when (p0.exists()) {
                        true -> phoneNumber.value = p0.value.toString()
                        false -> phoneNumber.value = "Not Added yet"
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    phoneNumber.value = null
                }
            })
    }

    private fun getUserName() {
        database.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userData")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    when (p0.exists()) {
                        true -> username.value = p0.value.toString()
                        false -> Unit
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    username.value = null
                }
            })
    }

    fun savePhoneToDb(phoneNumber: String) {
        database.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userPhoneNo").setValue(phoneNumber)
        getUserData()
    }

    fun saveUsernameToDb(username: String) {
        database.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userData").setValue(username)
        getUserData()
    }

    fun saveEmailToDb(oldEmail: String, userPassword: String, newEmail: String) {
        auth.signInWithEmailAndPassword(oldEmail, userPassword)
            .addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    auth.currentUser!!.updateEmail(newEmail).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            userEmail.value = newEmail
                        }
                    }
                }
            }
    }

    fun logoutUser() {
        auth.signOut()
    }
}