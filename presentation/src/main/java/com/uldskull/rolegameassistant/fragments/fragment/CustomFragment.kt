// File CustomFragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 *   Class "CustomFragment" :
 *   Abstract class for custom fragment.
 **/
abstract class CustomFragment(val activity: Activity) : Fragment() {
    /**
     * Initial root view.
     */
    protected lateinit var initialRootView: View

    /**
     * Fragment life-cycle : Called when the view is created.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(javaClass.simpleName.capitalize(), ONCREATE)
    }

    private val ONCREATE = "OnCreate"

    /**
     * Initialize the initial root view.
     */
    abstract fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View?
}