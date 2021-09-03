package com.johncoimbra.spotifyclone.extensions

import android.app.Activity
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.johncoimbra.spotifyclone.R

fun Activity.addFragmentOne(mFLoaded: Fragment, mSFManager: FragmentManager, mFLayout: FrameLayout) {
    val fragment = mSFManager.beginTransaction()
    fragment.replace(mFLayout.id, mFLoaded)
    fragment.commit()
}

fun Activity.addFragmentTwo(mFragment: Fragment, supportFragmentManager: FragmentManager){
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.content, mFragment, mFragment.javaClass.simpleName)
        .commit()
}