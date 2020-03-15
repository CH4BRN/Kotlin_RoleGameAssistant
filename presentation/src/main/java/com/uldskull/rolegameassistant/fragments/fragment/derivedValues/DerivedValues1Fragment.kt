// File DerivedValues1Fragment.kt
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
import com.uldskull.rolegameassistant.fragments.adapter.DERIVED_VALUES_1_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION

/**
 *   Class "DerivedValues1Fragment" :
 *   Fragment that handle derived values 1
 **/
class DerivedValues1Fragment(activity: Activity) : CustomFragment(activity) {

    /**
     * Called when the view is created
     */
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
        Log.i("DerivedValues1Fragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = DERIVED_VALUES_1_FRAGMENT_POSITION
        Log.i("DerivedValues1Fragment_2", NewCharacterActivity.progression.value.toString())
    }

    /**
     * Initialize the view
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_derived_values_1, container, false
        )
        return initialRootView
    }

    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity, position: Int): DerivedValues1Fragment {

            val fragment =
                DerivedValues1Fragment(
                    activity
                )

            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }
}