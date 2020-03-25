// HobbiesFragment.kt created by UldSkull - 09/03/2020

package com.uldskull.rolegameassistant.fragments.fragment.hobbies

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.HOBBIES_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION

/**
Class "HobbiesFragment"

TODO: Describe class utility.
 */
class HobbiesFragment(activity: Activity) : CustomFragment(activity) {
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_hobbies, container, false
        )
        return initialRootView
    }

    /**
     * Called when the focus return on this view
     */
    override fun onResume() {
        super.onResume()
        Log.d("HobbiesFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = HOBBIES_FRAGMENT_POSITION
        Log.d("HobbiesFragment_2", NewCharacterActivity.progression.value.toString())
    }

    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity): HobbiesFragment {
            val fragment =
                HobbiesFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, HOBBIES_FRAGMENT_POSITION)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_hobbiesSkills,
                HobbiesSkillsRecyclerViewFragment.newInstance(
                    activity
                )
            )


            return fragment
        }
    }
}