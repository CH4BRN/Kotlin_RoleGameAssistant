// File DerivedValues2Fragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.derivedValues

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION

/**
 *   Class "DerivedValues2Fragment" :
 *   TODO("COMMENT")
 **/
class DerivedValues2Fragment(val activity: Activity) : Fragment() {

    private lateinit var initialRootView: View

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
        NewCharacterActivity.progression.value = 4
    }

    fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_derived_values_2, container, false
        )
        return initialRootView
    }

    companion object {
        @JvmStatic
        fun newInstance(activity: Activity, position: Int): DerivedValues2Fragment {

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