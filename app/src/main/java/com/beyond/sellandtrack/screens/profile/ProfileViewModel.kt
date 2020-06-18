package com.beyond.sellandtrack.screens.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.beyond.sellandtrack.data.Repository
import com.beyond.sellandtrack.databinding.FragmentProfileBinding

class ProfileViewModel(application: Application) : AndroidViewModel(application) {


    private lateinit var repository: Repository

    init{
        repository = Repository()
    }


    fun signout() {
        repository.signOut()
    }

    fun setUserInfo(binding: FragmentProfileBinding?) {
        val current = repository.getCurrentUser()
        binding?.userProfileName?.text = current?.displayName
        binding?.userProfileEmail?.text = current?.email
    }


}