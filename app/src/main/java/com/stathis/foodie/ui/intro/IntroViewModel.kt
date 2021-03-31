package com.stathis.foodie.ui.intro

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class IntroViewModel : ViewModel() {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()
}
