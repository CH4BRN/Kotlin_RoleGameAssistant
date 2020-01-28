// File ProgressBarFragment.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.ui.new_character.NewCharacterActivity
import kotlinx.android.synthetic.main.fragment_progress_bar.*

/**
 *   Class "ProgressBarFragment" :
 *   Fragment class used to display a progress bar.
 *   The progression is bound to a field into the view model.
 **/
class ProgressBarFragment(val progression:Int =0) : Fragment() {

    /** Root view   **/
    private lateinit var initialRootView: View


    /** Fragment lifecycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    /** Fragment lifecycle  **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateProgressBar(progression)
    }

    /** Updated the progress bar    **/
    fun updateProgressBar(value: Int) {
        pb_progression.progress = value
    }

    /** Initialize the view **/
    fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_progress_bar, container, false
        )
        return initialRootView
    }

    companion object {
        @JvmStatic
        fun newInstance(activity: NewCharacterActivity, progression:Int): ProgressBarFragment {
            return ProgressBarFragment(progression = progression)
        }
    }

}