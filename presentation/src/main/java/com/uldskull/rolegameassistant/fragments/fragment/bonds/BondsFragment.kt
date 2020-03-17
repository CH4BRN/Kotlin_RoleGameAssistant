// File BondFragment.kt
// @Author pierre.antoine - 02/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.bonds

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.BONDS_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION

/**
 *   Class "BondFragment" :
 *   TODO: Fill class use.
 **/
class BondsFragment(activity: Activity) : CustomFragment(activity) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    override fun onResume() {
        super.onResume()

        Log.i("BondsFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = BONDS_FRAGMENT_POSITION
        Log.i("BondsFragment_2", NewCharacterActivity.progression.value.toString())

    }

    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_bonds, container, false
        )
        return initialRootView
    }

    companion object: CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity): BondsFragment {
            val fragment =
                BondsFragment(
                    activity
                )

            val args = Bundle()

            args.putInt(KEY_POSITION, BONDS_FRAGMENT_POSITION)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_bonds,
                BondsRecyclerViewFragment.newInstance(activity)
            )

            return fragment
        }
    }
}