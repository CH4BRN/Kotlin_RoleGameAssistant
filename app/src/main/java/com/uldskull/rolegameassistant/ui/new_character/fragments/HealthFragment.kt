// File HealthFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.ui.new_character.NewCharacterActivity

/**
 *   Class "HealthFragment" :
 *   TODO: Fill class use.
 **/
class HealthFragment : Fragment() {


    /** Initialize the view corresponding to this fragment class    **/
    private fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_health, container, false
        )

        return initialRootView

    }

    companion object {

        @JvmStatic
        fun newInstance(activity: NewCharacterActivity): HealthFragment {
            return HealthFragment()
        }

        private lateinit var initialRootView: View

    }
}