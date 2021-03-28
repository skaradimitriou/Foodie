package com.stathis.foodie.pushNotifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebasePushService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("", token)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        Log.d("", p0.toString())
    }
}