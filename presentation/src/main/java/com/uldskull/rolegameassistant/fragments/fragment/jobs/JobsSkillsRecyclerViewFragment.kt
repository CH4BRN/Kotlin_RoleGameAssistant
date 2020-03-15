// File SkillsRecyclerViewFragment.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.jobs

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewSkillActivity
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.skills.JobsSkillsAdapter
import com.uldskull.rolegameassistant.fragments.fragment.skills.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "SkillsRecyclerViewFragment" :
 *   Manage skill's RecyclerView fragment.
 **/
class JobsSkillsRecyclerViewFragment(activity: Activity) : CustomRecyclerViewFragment(activity) {

    /** ViewModel for skills    **/
    private lateinit var skillsViewModel: SkillsViewModel

    /** Adapter for skills recycler view    **/
    private var skillsAdapter: JobsSkillsAdapter? = null

    /** Recycler view for skills   **/
    private var skillsRecyclerView: RecyclerView? = null

    /** Fragment life-cycle **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skillsViewModel = getViewModel()
    }


    /** Fragment life-cycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    /** Initialize the view **/
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_jobsskills, container, false
        )
        return initialRootView
    }


    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {
        skillsRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        skillsRecyclerView = activity.findViewById(R.id.recycleView_jobsSkills)
                as RecyclerView?
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        this.skillsViewModel.jobSkills.observe(this, Observer { skills ->
            kotlin.run {
                skills?.let { skillsAdapter?.setSkills(it) }
            }
        })
    }

    /** Set recycler view adapter   **/
    override fun setRecyclerViewAdapter() {
        skillsAdapter =
            JobsSkillsAdapter(
                activity as Context
            )
        skillsRecyclerView?.adapter = skillsAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var btnAddSkills = view.findViewById<ImageButton>(R.id.btn_jobs_addSkill)
        btnAddSkills.setOnClickListener {
            val intent = Intent(activity, NewSkillActivity::class.java)
            startActivity(intent)

        }
    }


    companion object : CustomCompanion() {

        @JvmStatic
        override fun newInstance(
            activity: Activity,
            position: Int
        ): JobsSkillsRecyclerViewFragment {
            val fragment =
                JobsSkillsRecyclerViewFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }


    }
}