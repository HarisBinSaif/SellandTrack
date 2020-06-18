package com.beyond.sellandtrack.screens.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.databinding.FragmentProfileBinding

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(
               inflater,R.layout.fragment_profile, container, false
       )
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        profileViewModel.setUserInfo(binding)

        binding.logout.setOnClickListener{
            profileViewModel.signout()
            findNavController().navigate(R.id.action_profileFragment_to_welcomeFragment)
        }

        return binding.root
    }

}
