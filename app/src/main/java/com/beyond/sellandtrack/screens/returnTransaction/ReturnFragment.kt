package com.beyond.sellandtrack.screens.returnTransaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil

import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.databinding.FragmentReturnBinding

/**
 * A simple [Fragment] subclass.
 */
class ReturnFragment : Fragment() {

    var Userinput: String = ""
    var btn: Array<TextView?> = arrayOfNulls<TextView>(14)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentReturnBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_return,container,false
        )

        BtnBindnumpad(binding)

        for (i in 0..10) {
            btn[i]?.setOnClickListener {
                appendToString(i)
                binding.userInput.setText(Userinput.toString())
            }
        }
        btn[11]?.setOnClickListener {
            binding.showOutput.setText(Userinput)
            Userinput = Userinput.substring(0,0)
        }



        return binding.root
    }

    private fun appendToString(i: Int) {
        if(i==10){
            if(Userinput.length>0) {
                Userinput = Userinput.substring(0,(Userinput.length-1))
            }
        }
        else
        {
            Userinput += (i.toString())
        }
    }

    private fun BtnBindnumpad(binding: FragmentReturnBinding){

        btn[0] = binding.t9Key0
        btn[1] = binding.t9Key1
        btn[2] = binding.t9Key2
        btn[3] = binding.t9Key3
        btn[4] = binding.t9Key4
        btn[5] = binding.t9Key5
        btn[6] = binding.t9Key6
        btn[7] = binding.t9Key7
        btn[8] = binding.t9Key8
        btn[9] = binding.t9Key9
        btn[11] = binding.t9KeyEnter
        btn[10] = binding.t9KeyClear
    }


}
