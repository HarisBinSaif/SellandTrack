package com.beyond.sellandtrack.screens.login

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var auth : FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater, R.layout.fragment_login, container, false
        )
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.letsGoButton.setOnClickListener {
            loginViewModel.loginFlow(binding)
            Handler().postDelayed(
                {
                    if (loginViewModel.getUser() != null) {
                        findNavController().navigate(R.id.action_loginFragment_to_loadingFragment)
                    } else {
                        //login failed error
                        val alertDialogBuilder = activity?.let { it1 -> AlertDialog.Builder(it1) }
                        alertDialogBuilder?.setTitle("Login Failed")
                        alertDialogBuilder?.setMessage("Could not Login. Please check your credentials")
                        alertDialogBuilder?.setCancelable(true)
                        alertDialogBuilder?.create()
                        alertDialogBuilder?.show()
                    }
                },
                2000 // value in milliseconds
            )



        }
        return binding.root

    }
}
