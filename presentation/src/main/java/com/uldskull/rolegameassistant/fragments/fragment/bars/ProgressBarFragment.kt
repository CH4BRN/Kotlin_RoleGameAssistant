// File ProgressBarFragment.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.bars

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import kotlinx.android.synthetic.main.fragment_progress_bar.*

/**
 *   Class "ProgressBarFragment" :
 *   Fragment class used to display a progress bar.
 *   The progression is bound to a field into the view model.
 **/
class ProgressBarFragment(val progression: Int = 0) : CustomFragment() {


    /** Fragment lifecycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return initializeView(inflater, container)
    }

    /** Fragment life-cycle **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        updateProgressBar(progression)
    }

    /** Updated the progress bar    **/
    private fun updateProgressBar(value: Int) {
        Log.d(TAG, "updateProgressBar")
        pb_progression.progress = value
    }

    /** Initialize the view **/
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_progress_bar, container, false
        )
        return initialRootView
    }

    companion object : CustomCompanion() {
        private const val TAG = "ProgressBarFragment"
        @JvmStatic
        override fun newInstance(activity: Activity): CustomFragment {
            Log.d(TAG, "newInstance")
            val fragment = ProgressBarFragment(
                progression = 0
            )
            fragment.activity = activity
            return fragment
        }

        @JvmStatic
        fun newInstance(activity: Activity, progression: Int): CustomFragment {
            Log.d(TAG, "newInstance")
            val fragment = ProgressBarFragment(
                progression = progression
            )
            fragment.activity = activity
            return fragment
        }
    }

}