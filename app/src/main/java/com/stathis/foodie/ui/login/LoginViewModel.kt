package com.stathis.foodie.ui.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel : ViewModel() {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    val loginSuccessful = MutableLiveData<FirebaseUser>()
    val loginFailed = MutableLiveData<Boolean>()


    fun validateUserInput(
        emailField: TextInputEditText,
        passwordField: TextInputEditText
    ) {
        when (validateLoginCredentials(emailField, passwordField)) {
            true -> {
                auth.signInWithEmailAndPassword(
                    emailField.text.toString(),
                    passwordField.text.toString()
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            loginSuccessful.value = auth.currentUser
                            loginFailed.value = false
                        } else {
                            loginFailed.value = true
                            loginSuccessful.value = null
                        }
                    }
            }
            false -> Unit
        }
    }

    private fun validateLoginCredentials(
        emailField: TextInputEditText,
        passwordField: TextInputEditText
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
        return true
    }
}