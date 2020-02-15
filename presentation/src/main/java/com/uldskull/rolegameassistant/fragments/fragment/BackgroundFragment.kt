// File BackgroundFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel

import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "BackgroundFragment" :
 *   Class to handle Background fragment view
 **/
class BackgroundFragment : Fragment() {


    private lateinit var newCharacterViewModel: NewCharacterViewModel
    /** Fragment Lifecycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newCharacterViewModel = getViewModel()


        return initializeView(inflater, container)
    }


    /** Initialize the view corresponding to this fragment class    **/
    private fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_background, container, false
        )

        return initialRootView

    }

    /** Fragment Lifecycle  **/
    override fun onResume() {
        super.onResume()
        NewCharacterActivity.progression.value = 2
    }
    private lateinit var initialRootView: View
    companion object {
        @JvmStatic
        fun newInstance(activity: Activity, position: Int): BackgroundFragment {
            val fragment =
                BackgroundFragment()

            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }

        private const val KEY_POSITION = "position"

    }
}