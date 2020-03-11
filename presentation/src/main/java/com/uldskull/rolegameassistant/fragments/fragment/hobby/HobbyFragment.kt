// File HobbyFragment.kt
// @Author pierre.antoine - 09/03/2020 - No copyright.

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

/**
 *   Class "HobbyFragment" :
 *   TODO: Fill class use.
 **/
class HobbyFragment (activity: Activity) : CustomFragment(activity) {
    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_hobby, container, false
        )
        return initialRootView
    }

    companion object{
        @JvmStatic
        fun newInstance(activity: Activity, position:Int) : HobbyFragment{
            val fragment =
                HobbyFragment(
                    activity
                )

            val args = Bundle()

            args.putInt(KEY_POSITION, position)

            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_hobbySkills,
                HobbySkillsRecyclerViewFragment.newInstance(activity, 46)
            )

            return fragment
        }
    }

}