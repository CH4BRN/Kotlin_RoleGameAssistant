// File ProgressBarFragment.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.ui.new_character.NewCharacterActivity
import com.uldskull.rolegameassistant.ui.new_character.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_progress_bar.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "ProgressBarFragment" :
 *   Fragment class used to display a progress bar.
 *   The progression is bound to a field into the view model.
 **/
class ProgressBarFragment : Fragment() {

    private lateinit var initialRootView: View
    private lateinit var newCharacterViewModel: NewCharacterViewModel

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


        newCharacterViewModel = getViewModel ()
        newCharacterViewModel.progressStatus.observe(this, Observer {
            updateProgressBar(it)
        })

    }
    /** Updated the progress bar    **/
    private fun updateProgressBar(value:Int) {
        pb_progression.progress = value
    }

    /** Initialize the view **/
    fun initializeView(inflater: LayoutInflater, container:ViewGroup?): View?{
        initialRootView = inflater.inflate(
            R.layout.fragment_progress_bar, container, false
        )
        return  initialRootView
    }

    companion object {
        @JvmStatic
        fun newInstance(activity: NewCharacterActivity) : ProgressBarFragment {
            return ProgressBarFragment()
        }
    }

}