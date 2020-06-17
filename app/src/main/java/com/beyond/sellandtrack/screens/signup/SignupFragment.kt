package com.beyond.sellandtrack.screens.signup

import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * A simple [Fragment] subclass.
 */
class SignupFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
    private lateinit var signupViewModel: SignupViewModel
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate<FragmentSignupBinding>(
            inflater,R.layout.fragment_signup,container,false
        )
        auth = FirebaseAuth.getInstance()
        signupViewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding.getStartedButton.setOnClickListener {
            signupViewModel.signupFlow(binding)
            auth.signOut()
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)

        }

        return binding.root
    }


}
