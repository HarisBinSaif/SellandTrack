package com.beyond.sellandtrack.screens.journal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.beyond.sellandtrack.R

/**
 * A simple [Fragment] subclass.
 */
class JournalFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_journal, container, false)
    }

}
