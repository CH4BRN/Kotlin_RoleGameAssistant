// File CustomFragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 *   Class "CustomFragment" :
 *   TODO: Fill class use.
 **/
abstract class CustomFragment(val activity: Activity) : Fragment() {
    protected lateinit var initialRootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    abstract fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View?

// TODO : Fill class.
}