package com.beyond.sellandtrack.screens.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.databinding.FragmentWelcomeBinding
import com.beyond.sellandtrack.screens.signup.SignupViewModel
import com.google.firebase.auth.FirebaseUser

/**
 * A simple [Fragment] subclass.
 */
class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var welcomeViewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentWelcomeBinding>(
            inflater,R.layout.fragment_welcome,container,false
        )
        welcomeViewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        binding.loginButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
        binding.signupButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_welcomeFragment_to_signupFragment)
        }
        return binding.root
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = welcomeViewModel.getCurrentUser()
        updateUI(currentUser)
    }


    private fun updateUI(currentUser : FirebaseUser?){
        if (currentUser!=null){
            findNavController().navigate(R.id.action_welcomeFragment_to_dashboardFragment)
        }
    }


}
