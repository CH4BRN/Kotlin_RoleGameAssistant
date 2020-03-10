// HobbiesFragment.kt created by UldSkull - 09/03/2020

package com.uldskull.rolegameassistant.fragments.fragment.hobby

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.skills.HobbiesSkillsRecyclerViewFragment

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

    companion object {
        @JvmStatic
        fun newInstance(activity: Activity, position: Int): HobbiesFragment {
            val fragment =
                HobbiesFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_hobbiesSkills,
                HobbiesSkillsRecyclerViewFragment.newInstance(activity, 44)
            )


            return fragment
        }
    }
}