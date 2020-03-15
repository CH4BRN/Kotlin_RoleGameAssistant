// File JobsFragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.jobs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.NewSkillActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.JOBS_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.adapter.JOBS_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import kotlinx.android.synthetic.main.fragment_jobs.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayAdapter =
            ArrayAdapter(activity, android.R.layout.simple_spinner_item, listOfJobs)

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_jobs?.adapter = arrayAdapter


    }

    /**
     * Called when the focus return on this view
     */
    override fun onResume() {
        super.onResume()
        Log.i("JobsFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = JOBS_FRAGMENT_POSITION
        Log.i("JobsFragment_2", NewCharacterActivity.progression.value.toString())
    }

    private var listOfJobs = arrayOf("Job1", "Job2", "Job3")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity, position: Int): JobsFragment {
            val fragment =
                JobsFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_jobs,
                JobsSkillsRecyclerViewFragment.newInstance(
                    activity,
                    JOBS_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION
                )
            )

            return fragment
        }
    }


}