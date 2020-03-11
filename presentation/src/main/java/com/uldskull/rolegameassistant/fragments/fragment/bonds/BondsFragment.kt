// File BondFragment.kt
// @Author pierre.antoine - 02/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.bonds

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION

/**
 *   Class "BondFragment" :
 *   TODO: Fill class use.
 **/
class BondsFragment(val activity: Activity) : Fragment() {
    private lateinit var initialRootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    override fun onResume() {
        super.onResume()
        NewCharacterActivity.progression.value = 0
    }

    private fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_bonds, container, false
        )
        return initialRootView
    }

    companion object {
        @JvmStatic
        fun newInstance(activity: Activity, position: Int): BondsFragment {
            val fragment =
                BondsFragment(
                    activity
                )

            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_bonds,
                BondsRecyclerViewFragment.newInstance(activity, 40)
            )

            return fragment
        }
    }
}