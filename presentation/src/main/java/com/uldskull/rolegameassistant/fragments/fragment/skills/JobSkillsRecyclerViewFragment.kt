// File JobSkillsRecyclerViewFragment.kt
// @Author pierre.antoine - 05/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.skills

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
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "JobSkillsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class JobSkillsRecyclerViewFragment(activity: Activity) : CustomFragment(activity) {

    private var jobSkillsAdapter: JobSkillsAdapter? = null

    private lateinit var skillsViewModel: SkillsViewModel

    private var jobSkillRecyclerView: RecyclerView? = null

    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_jobskills, container, false
        )
        return initialRootView
    }

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

    /** Fragment Lifecycle  **/
    override fun onResume() {
        super.onResume()
        NewCharacterActivity.progression.value = 5
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
    }

    /** Initialize recycler view    **/
    private fun initializeRecyclerView() {
        jobSkillRecyclerView = activity.findViewById(R.id.recycler_view_jobSkills)
                as RecyclerView?
        startSkillsObservation()
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /** Set recycler view layout manager    **/
    private fun setRecyclerViewLayoutManager() {
        jobSkillRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }


    private fun setRecyclerViewAdapter() {
        jobSkillsAdapter = JobSkillsAdapter(activity as Context)
        jobSkillRecyclerView?.adapter = jobSkillsAdapter
    }


    private fun startSkillsObservation() {
        this.skillsViewModel.skills.observe(this, Observer { skills ->
            kotlin.run {
                skills?.let { jobSkillsAdapter?.setJobSkills(it) }
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance(activity: Activity, position: Int): JobSkillsRecyclerViewFragment {
            val fragment = JobSkillsRecyclerViewFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }

        private const val KEY_POSITION = "position"


    }
}