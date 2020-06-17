package com.beyond.sellandtrack.screens.welcome

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class WelcomeViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var auth : FirebaseAuth
    init {
        auth = FirebaseAuth.getInstance()
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

}