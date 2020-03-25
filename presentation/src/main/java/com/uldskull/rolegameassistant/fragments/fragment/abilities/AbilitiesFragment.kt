// File AbilitiesFragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.abilities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.ABILITIES_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION

/**
 *   Class "AbilitiesFragment" :
 *   TODO: Fill class use.
 **/
class AbilitiesFragment(activity: Activity) : CustomFragment(activity) {
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
     * Initialize the view
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_abilities, container, false
        )
        return initialRootView
    }
    override fun onResume() {
        super.onResume()
        Log.d("AbilitiesFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = ABILITIES_FRAGMENT_POSITION
        Log.d("AbilitiesFragment_2", NewCharacterActivity.progression.value.toString())
    }

    companion object : CustomCompanion(){
        @JvmStatic
        override fun newInstance(activity: Activity): AbilitiesFragment {
            val fragment =
                AbilitiesFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, ABILITIES_FRAGMENT_POSITION)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_abilities,
                AbilitiesRecyclerViewFragment.newInstance(activity)
            )

            return fragment
        }
    }
}