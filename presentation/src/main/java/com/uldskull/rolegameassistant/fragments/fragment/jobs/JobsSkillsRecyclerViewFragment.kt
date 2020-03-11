// File SkillsRecyclerViewFragment.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.jobs

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.skills.JobsSkillsAdapter
import com.uldskull.rolegameassistant.fragments.fragment.skills.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "SkillsRecyclerViewFragment" :
 *   Manage skill's RecyclerView fragment.
 **/
class JobsSkillsRecyclerViewFragment : Fragment() {

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
    private fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_jobsskills, container, false
        )
        return initialRootView
    }

    /** Fragment life-cycle **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skillsRecyclerView = activity?.findViewById(R.id.recycleView_jobsSkills)
                as RecyclerView?
        startSkillsObservation()
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /** Set recycler view layout manager    **/
    private fun setRecyclerViewLayoutManager() {
        skillsRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    /** Set recycler view adapter   **/
    private fun setRecyclerViewAdapter() {
        skillsAdapter =
            JobsSkillsAdapter(
                activity as Context
            )
        skillsRecyclerView?.adapter = skillsAdapter
    }

    /** Observe ViewModel's skills  **/
    private fun startSkillsObservation() {
        this.skillsViewModel.jobSkills.observe(this, Observer { skills ->
            kotlin.run {
                skills?.let { skillsAdapter?.setSkills(it) }
            }
        })
    }
    private lateinit var initialRootView: View
    companion object {

        @JvmStatic
        fun newInstance(activity: Activity, position: Int): JobsSkillsRecyclerViewFragment {
            val fragment =
                JobsSkillsRecyclerViewFragment()
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }


    }
}