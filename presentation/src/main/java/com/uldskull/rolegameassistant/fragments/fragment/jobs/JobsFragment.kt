// File JobsFragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.jobs

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
import com.uldskull.rolegameassistant.fragments.fragment.skills.SkillsRecyclerViewFragment

/**
 *   Class "JobsFragment" :
 *   TODO: Fill class use.
 **/
class JobsFragment(activity: Activity) : CustomFragment(activity) {
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_jobs, container, false
        )
        return initialRootView
    }

    companion object {
        @JvmStatic
        fun newInstance(activity: Activity, position: Int): JobsFragment {
            val fragment =
                JobsFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_jobs,
                SkillsRecyclerViewFragment.newInstance(activity, 43)
            )

            return fragment
        }
    }


}