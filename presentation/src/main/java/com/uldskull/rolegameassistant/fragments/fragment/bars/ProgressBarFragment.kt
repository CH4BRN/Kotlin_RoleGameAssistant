// File ProgressBarFragment.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.bars

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomFragment
import com.uldskull.rolegameassistant.viewmodels.ProgressionBarViewModel
import kotlinx.android.synthetic.main.fragment_progress_bar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "ProgressBarFragment" :
 *   Fragment class used to display a progress bar.
 *   The progression is bound to a field into the view model.
 **/
class ProgressBarFragment : CustomFragment() {

    /**
     * Progression bar view model
     */
    private val progressionBarViewModel:ProgressionBarViewModel by sharedViewModel()


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


        observeProgression()
    }

    /**
     * Observe progression from view model
     */
    private fun observeProgression() {
        progressionBarViewModel.progression.observe(this, Observer { progression ->
            updateProgressBar(progression)
        })
    }

    /** Updated the progress bar    **/
    private fun updateProgressBar(value: Int) {
        Log.d(TAG, "updateProgressBar")

        val progress = pb_progression.progress
        val progressBar = pb_progression
        val anim = ProgressBarAnimation(progressBar, progress.toFloat(), value.toFloat())
        anim.duration = 250
        progressBar.startAnimation(anim)

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
            )
            fragment.activity = activity
            return fragment
        }


    }

}