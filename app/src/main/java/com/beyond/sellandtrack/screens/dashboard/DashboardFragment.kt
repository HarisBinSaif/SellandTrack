package com.beyond.sellandtrack.screens.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.data.Repository
import com.beyond.sellandtrack.data.dataClasses.User
import com.beyond.sellandtrack.databinding.FragmentDashboardBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {
    private lateinit var binding  : FragmentDashboardBinding
    private lateinit var dashboardViewModel: DashboardViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate<FragmentDashboardBinding>(
            inflater,R.layout.fragment_dashboard,container,false
        )
        dashboardViewModel= ViewModelProvider(this).get(DashboardViewModel::class.java)



        setWelcomeMessage()
        binding.settings.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_manageFragment)
        }

        binding.transaction.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_transactionFragment)
        }

        binding.inventory.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_inventoryFragment)
        }

        binding.customers.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_customersFragment)
        }

        binding.Profile.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_profileFragment)
        }


        binding.journal.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_journalFragment)
        }

        binding.returnn.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_returnFragment)
        }

        return binding.root
    }

    private fun setWelcomeMessage() {
        binding.welcomeTxt.text = dashboardViewModel.getUserDisplayName()+"'s Dashboard"
    }

}
