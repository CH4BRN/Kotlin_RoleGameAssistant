// File BasicInfoFragment.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.ui.new_character.activities.NewCharacterActivity

/**
 *   Class "BasicInfoFragment" :
 *   Fragment to fill information concerning basic info.
 **/
class BasicInfoFragment : Fragment() {

    /** Fragment Lifecycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        NewCharacterActivity.progression.value = 0
        return initializeView(inflater, container)
    }

    /** Initialize the view corresponding to this fragment class    **/
    private fun initializeView( inflater: LayoutInflater, container: ViewGroup?): View?{
        initialRootView = inflater.inflate(
            R.layout.fragment_basic_info, container, false)

        return  initialRootView

    }

    companion object {
        @JvmStatic
        fun newInstance(activity: Activity, position: Int): BasicInfoFragment {
            var fragment = BasicInfoFragment()
            var args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }

        private const val KEY_POSITION = "position"
        private lateinit var initialRootView: View
    }
}