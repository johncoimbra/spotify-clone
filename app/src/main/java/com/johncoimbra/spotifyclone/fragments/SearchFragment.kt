package com.johncoimbra.spotifyclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.johncoimbra.spotifyclone.R

class SearchFragment : Fragment() {

    companion object{
        fun newInstance(): SearchFragment{
            val fragmentSearch = SearchFragment()
            val arguments = Bundle()
            fragmentSearch.arguments = arguments
            return fragmentSearch
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}