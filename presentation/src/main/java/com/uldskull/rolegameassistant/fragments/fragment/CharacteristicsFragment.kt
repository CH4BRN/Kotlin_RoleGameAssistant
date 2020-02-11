// File CharacteristicsFragment.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity


/**
 *   Class "CharacteristicsFragment" :
 *   Fragment to fill information concerning characteristics.
 **/
class CharacteristicsFragment : Fragment() {

    /** Fragment Lifecycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return initializeView(inflater, container)
    }

    /** Fragment Lifecycle  **/
    override fun onResume() {
        super.onResume()
        setProgression()
    }

    /** Set progress bar progression  **/
    private fun setProgression() {
        NewCharacterActivity.progression.value = 1
    }

    /** Fragment Lifecycle  **/
    override fun onPause() {
        super.onPause()
        setProgression()
    }

    /** Initialize the view corresponding to this fragment class    **/
    private fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_characteristics, container, false
        )
        loadPictureFragment()
        return initialRootView
    }

    /** Load picture fragment   **/
    private fun loadPictureFragment() {
        val fragmentManager = activity?.supportFragmentManager
        val pictureTransaction = fragmentManager?.beginTransaction()
        pictureTransaction?.replace(R.id.container_picture, PictureFragment(activity as Activity))
            ?.commit()
    }

    companion object {

        @JvmStatic
        fun newInstance(activity: Activity, position: Int): CharacteristicsFragment {
            val fragment =
                CharacteristicsFragment()
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }

        private const val KEY_POSITION = "position"
        private lateinit var initialRootView: View

    }

}