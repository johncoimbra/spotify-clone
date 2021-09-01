package com.johncoimbra.spotifyclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.johncoimbra.spotifyclone.R

class LibraryFragment : Fragment() {

    companion object {
        fun newInstance(): LibraryFragment {
            val fragmentLibrary = LibraryFragment()
            val arguments = Bundle()
            fragmentLibrary.arguments = arguments
            return fragmentLibrary
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

}