package com.beyond.sellandtrack.screens.manage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.databinding.FragmentManageBinding

/**
 * A simple [Fragment] subclass.
 */
class ManageFragment : Fragment() {

    private lateinit var manageViewModel: ManageViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentManageBinding>(
            inflater,R.layout.fragment_manage,container,false
        )

        manageViewModel = ViewModelProvider(this).get(ManageViewModel::class.java)

        binding.addNewProd.setOnClickListener {
            findNavController().navigate(R.id.action_manageFragment_to_addProductFragment)
        }

        binding.delProd.setOnClickListener {
            deletedDialog()
        }

        return binding.root
    }

    private fun deletedDialog() {
        val deleteProductForm = LayoutInflater.from(activity).inflate(R.layout.delete_product_form,null)
        val dialog = activity?.let { it1 -> AlertDialog.Builder(it1) }
        val item_code= deleteProductForm.findViewById<EditText>(R.id.item_code_edittext)
        val btn = deleteProductForm.findViewById<Button>(R.id.del_button)
        dialog?.setView(deleteProductForm)
        val alertDialog = dialog?.show()


        btn.setOnClickListener {
            alertDialog?.dismiss()
            val code= item_code.text.toString()
            manageViewModel.deleteProduct(code)
        }
    }

}
