// File HealthFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R

/**
 *   Class "HealthFragment" :
 *   Manage the health fragment view.
 **/
class HealthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    /** Initialize the view corresponding to this fragment class    **/
    private fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_health, container, false
        )

        return initialRootView

    }

    companion object {

        @JvmStatic
        fun newInstance(activity: Activity): HealthFragment {
            return HealthFragment()
        }

        private lateinit var initialRootView: View

    }
}