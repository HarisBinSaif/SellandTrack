package com.beyond.sellandtrack.screens

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.databinding.FragmentLoadingBinding
import com.beyond.sellandtrack.screens.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_loading.*

/**
 * A simple [Fragment] subclass.
 */
class LoadingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentLoadingBinding>(
            inflater,R.layout.fragment_loading,container,false
        )
        binding.rotateloading.start()
        Handler().postDelayed(
            {
                binding.rotateloading.stop()
                findNavController().navigate(R.id.action_loadingFragment_to_dashboardFragment)
            },
            2500 // value in milliseconds
        )


        return binding.root
    }

}
