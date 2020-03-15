// File DerivedValues2Fragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.derivedValues

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.fragments.adapter.DERIVED_VALUES_2_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION

/**
 *   Class "DerivedValues2Fragment" :
 *   TODO("COMMENT")
 **/
class DerivedValues2Fragment(activity: Activity) : CustomFragment(activity) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    /**
     * Called when the focus return on this view
     */
    override fun onResume() {
        super.onResume()
        Log.i("DerivedValues2Fragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = DERIVED_VALUES_2_FRAGMENT_POSITION
        Log.i("DerivedValues2Fragment_2", NewCharacterActivity.progression.value.toString())
    }

    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_derived_values_2, container, false
        )
        return initialRootView
    }

    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity, position: Int): DerivedValues2Fragment {

            val fragment =
                DerivedValues2Fragment(
                    activity
                )

            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }

}