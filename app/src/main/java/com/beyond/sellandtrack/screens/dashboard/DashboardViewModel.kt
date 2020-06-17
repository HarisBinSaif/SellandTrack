package com.beyond.sellandtrack.screens.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beyond.sellandtrack.data.Repository
import com.beyond.sellandtrack.data.dataClasses.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.model.Document
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    //lateinit var user: User
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()
    private var repository: Repository = Repository()


    fun getUserDisplayName(): String? {
        return auth.currentUser?.displayName
    }

    fun signOut() {
        repository.signOut()
    }

}