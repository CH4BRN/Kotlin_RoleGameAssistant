// File ProgressBarFragment.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.R
import kotlinx.android.synthetic.main.fragment_progress_bar.*

/**
 *   Class "ProgressBarFragment" :
 *   Fragment class used to display a progress bar.
 *   The progression is bound to a field into the view model.
 **/
class ProgressBarFragment(val progression: Int = 0, activity: Activity) : CustomFragment(activity) {

    /** Fragment lifecycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    /** Fragment life-cycle **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateProgressBar(progression)
    }

    /** Updated the progress bar    **/
    private fun updateProgressBar(value: Int) {
        pb_progression.progress = value
    }

    /** Initialize the view **/
    override fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_progress_bar, container, false
        )
        return initialRootView
    }

    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity): CustomFragment {
            return ProgressBarFragment(
                progression = 0,
                activity = activity
            )
        }

        @JvmStatic
        fun newInstance(activity: Activity, progression: Int): CustomFragment {
            return ProgressBarFragment(
                progression = progression,
                activity = activity
            )
        }
    }

}