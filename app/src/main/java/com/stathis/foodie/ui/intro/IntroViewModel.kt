package com.stathis.foodie.ui.intro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class IntroViewModel : ViewModel() {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    val loginSuccessful = MutableLiveData<FirebaseUser>()
    val loginFailed = MutableLiveData<Boolean>()

}
