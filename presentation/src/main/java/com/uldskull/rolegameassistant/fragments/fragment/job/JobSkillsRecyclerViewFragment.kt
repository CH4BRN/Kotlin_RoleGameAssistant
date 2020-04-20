// File JobSkillsRecyclerViewFragment.kt
// @Author pierre.antoine - 05/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.job

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.adapter.JOB_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.viewmodels.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "JobSkillsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class JobSkillsRecyclerViewFragment(activity: Activity) : CustomRecyclerViewFragment(activity) {

    private var jobSkillsAdapter: JobSkillsAdapter? = null

    private lateinit var skillsViewModel: SkillsViewModel

    private var jobSkillRecyclerView: RecyclerView? = null

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_jobskills, container, false
        )
        return initialRootView
    }

    /**
     * Fragment life-cycle : Called when the view is created.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(layoutInflater, container)
    }

    /** Fragment life-cycle **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skillsViewModel = getViewModel()
    }


    /** Initialize recycler view    **/
    override fun initializeRecyclerView() {
        jobSkillRecyclerView = activity.findViewById(R.id.recycler_view_jobSkills)
                as RecyclerView?
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        this.skillsViewModel.jobSkills.observe(this, Observer { skills ->
            kotlin.run {
                skills?.let { jobSkillsAdapter?.setJobSkills(it) }
            }
        })
    }

    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {
        jobSkillRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }


    override fun setRecyclerViewAdapter() {
        jobSkillsAdapter =
            JobSkillsAdapter(
                activity as Context
            )
        jobSkillRecyclerView?.adapter = jobSkillsAdapter
    }


    companion object : CustomCompanion() {

        @JvmStatic
        override fun newInstance(activity: Activity): JobSkillsRecyclerViewFragment {
            val fragment =
                JobSkillsRecyclerViewFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, JOB_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }

    }
}