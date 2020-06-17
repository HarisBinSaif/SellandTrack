package com.beyond.sellandtrack.screens.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.beyond.sellandtrack.data.dataClasses.User
import com.beyond.sellandtrack.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore


class SignupViewModel(application:Application):AndroidViewModel(application) {

    private lateinit var db : FirebaseFirestore
    private lateinit var auth : FirebaseAuth


    init{
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
    }

    fun signupFlow(binding: FragmentSignupBinding){
        //verify empty fields
        if(binding.nameEdittext.text.toString().isEmpty()) {
            binding.nameEdittext.error = "Please Enter Name"
            binding.nameEdittext.requestFocus()
            return
        }
        else if(binding.emailEdittext.text.toString().isEmpty()){
            binding.emailEdittext.error = "Please Enter Email"
            binding.emailEdittext.requestFocus()
            return
        }
        else if(binding.passwordEdittext.text.toString().isEmpty()) {
            binding.passwordEdittext.error = "Please Enter Password"
            binding.passwordEdittext.requestFocus()
            return
        }

        //authorize new user
        auth.createUserWithEmailAndPassword(binding.emailEdittext.text.toString(),binding.passwordEdittext.text.toString()).addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val uid = auth.currentUser!!.uid
                val user = User(
                    uid,
                    binding.nameEdittext.text.toString(),
                    binding.emailEdittext.text.toString(),
                    true
                )

                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(user.name)
                    .build()
                auth.currentUser!!.updateProfile(profileUpdates)
                db.collection("users").document(uid).set(user)

            } else {
                // If sign in fails, display a message to the user.
            }
        }
    }




}