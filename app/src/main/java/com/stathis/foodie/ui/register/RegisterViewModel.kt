package com.stathis.foodie.ui.register

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterViewModel : ViewModel() {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    val databaseReference by lazy { FirebaseDatabase.getInstance().reference }
    val registerSuccessful = MutableLiveData<Boolean>()

    fun validateUserInput(
        username : TextInputEditText,
        emailField: TextInputEditText,
        passwordField: TextInputEditText,
        passwordConfigField: TextInputEditText
    ) {
        when (validateRegisterCredentials(
            emailField,
            passwordField,
            passwordConfigField
        )) {
            true -> {
                //if the 2 passwords match, I am registering him to the RealTime Db
                if (passwordField.text.toString() == passwordConfigField.text.toString()) {
                    auth.createUserWithEmailAndPassword(
                        emailField.text.toString(),
                        passwordField.text.toString()
                    )
                        .addOnCompleteListener { task ->
                            registerSuccessful.value = task.isSuccessful
                            saveUsernameToDb(username.text.toString())
                        }
                }
            }
            false -> registerSuccessful.value = false
        }
    }

    private fun validateRegisterCredentials(
        emailField: TextInputEditText,
        passwordField: TextInputEditText,
        passwordConfigField: TextInputEditText
    ): Boolean {
        if (emailField.text.toString().isEmpty()) {
            emailField.error = "Please enter your e-mail"
            emailField.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailField.text.toString()).matches()) {
            emailField.error = "Please enter a valid e-mail address"
            emailField.requestFocus()
            return false
        }

        if (passwordField.text.toString().isEmpty()) {
            passwordField.error = "Please enter your password"
            passwordField.requestFocus()
            return false
        }

        if (passwordConfigField.text.toString().isEmpty()) {
            passwordConfigField.error = "Please confirm your password"
            passwordConfigField.requestFocus()
            return false
        }
        return true
    }

    fun saveUsernameToDb(username : String){
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userData").setValue(username)
    }
}
