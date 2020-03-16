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
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NEW_JOB_ACTIVITY
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.JOBS_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.adapter.JOBS_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_JOBS_NEW_JOB
import kotlinx.android.synthetic.main.fragment_jobs.*

/**
 *   Class "JobsFragment" :
 *   Fragment that manages and displays jobs.
 **/
class JobsFragment(activity: Activity) : CustomFragment(activity) {

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_jobs, container, false
        )
        return initialRootView
    }

    /**
     * Called immediately after [.onCreateView]
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     * @param view The View returned by [.onCreateView].
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setJobsSpinner()
        setButtonAddJob()
    }

    /**
     * Set the add job button.
     */
    private fun setButtonAddJob() {
        if (btn_addJob != null) {
            btn_addJob!!.setOnClickListener {
                val intent = Intent(activity, NEW_JOB_ACTIVITY)
                startActivityForResult(intent, REQUEST_CODE_JOBS_NEW_JOB)
            }
        }
    }

    /**
     * Set the spinner that displays the available jobs.
     */
    private fun setJobsSpinner() {
        val arrayAdapter =
            ArrayAdapter(activity, android.R.layout.simple_spinner_item, listOfJobs)

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_jobs?.adapter = arrayAdapter
    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to [Activity.onResume] of the containing
     * Activity's lifecycle.
     */
    override fun onResume() {
        super.onResume()
        Log.i("JobsFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = JOBS_FRAGMENT_POSITION
        Log.i("JobsFragment_2", NewCharacterActivity.progression.value.toString())
    }

    private var listOfJobs = arrayOf("Job1", "Job2", "Job3")

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