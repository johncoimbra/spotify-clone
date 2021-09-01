package com.johncoimbra.spotifyclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.johncoimbra.spotifyclone.databinding.ActivityMainBinding
import com.johncoimbra.spotifyclone.extensions.addFragmentOne
import com.johncoimbra.spotifyclone.extensions.addFragmentTwo
import com.johncoimbra.spotifyclone.fragments.HomeFragment
import com.johncoimbra.spotifyclone.fragments.LibraryFragment
import com.johncoimbra.spotifyclone.fragments.SearchFragment

class MainActivity : AppCompatActivity() {

    private var mContent: FrameLayout? = null

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())

        binding.bottomMenu.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_home -> {
                    val fragment = HomeFragment.newInstance()
                    loadFragment(fragment)
                    return@setOnItemSelectedListener true
                }

                R.id.nav_search -> {
                    val fragment = SearchFragment.newInstance()
                    loadFragment(fragment)
                    return@setOnItemSelectedListener true
                }

                R.id.nav_library -> {
                    val fragment = LibraryFragment.newInstance()
                    loadFragment(fragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    private fun loadFragment(mFragmentLoaded: Fragment){
        addFragmentOne(mFragmentLoaded, supportFragmentManager, binding.content)
    }

}