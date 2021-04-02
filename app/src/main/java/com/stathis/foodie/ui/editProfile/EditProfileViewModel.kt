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
    val isEditable = MutableLiveData<Boolean>()
    val userEmail = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    private var editableModeOn = false

    fun getUserData() {
        userEmail.value = FirebaseAuth.getInstance().currentUser?.email.toString()

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

    fun makeFieldsEditable() {
        editableModeOn = !editableModeOn
        isEditable.value = editableModeOn
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
}
