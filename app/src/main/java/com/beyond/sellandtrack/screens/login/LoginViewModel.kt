package com.beyond.sellandtrack.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.beyond.sellandtrack.data.Repository
import com.beyond.sellandtrack.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var repository: Repository
    private lateinit var user : FirebaseUser
    private lateinit var auth: FirebaseAuth
    init{
        repository = Repository()
        auth  = FirebaseAuth.getInstance()
    }

    fun loginFlow(binding: FragmentLoginBinding) {

        if(binding.emailEdittext.text.toString().isEmpty()){
            binding.emailEdittext.error = "Please Enter Email"
            binding.emailEdittext.requestFocus()
            return
        }
        else if(binding.passwordEdittext.text.toString().isEmpty()) {
            binding.passwordEdittext.error = "Please Enter Password"
            binding.passwordEdittext.requestFocus()
            return
        }
        else{
            authenticateUser(binding.emailEdittext.text.toString(),binding.passwordEdittext.text.toString())
        }
    }

    fun authenticateUser(email:String,password:String) = viewModelScope.launch {
        repository.login(email,password)
    }

    fun getUser() : FirebaseUser?{
        return repository.getCurrentUser()
    }



}