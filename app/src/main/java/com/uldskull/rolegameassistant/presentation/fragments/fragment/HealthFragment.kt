// File HealthFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.presentation.fragments.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.presentation.activities.NewCharacterActivity

/**
 *   Class "HealthFragment" :
 *   Manage the health fragment view.
 **/
class HealthFragment : Fragment() {

    /** Fragment life-cycle **/
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

    /** Fragment Lifecycle  **/
    override fun onResume() {
        super.onResume()
        NewCharacterActivity.progression.value = 4
    }
    companion object {

        @JvmStatic
        fun newInstance(activity: Activity, position: Int): HealthFragment {
            val fragment =
                HealthFragment()
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }

        private const val KEY_POSITION = "position"

        private lateinit var initialRootView: View

    }
}