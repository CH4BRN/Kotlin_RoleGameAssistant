// File SkillsRecyclerViewFragment.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.occupations

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewSkillActivity
import com.uldskull.rolegameassistant.fragments.adapter.JOBS_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill
import com.uldskull.rolegameassistant.viewmodels.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "SkillsRecyclerViewFragment" :
 *   Manage skill's RecyclerView fragment.
 **/
class OccupationsSkillsRecyclerViewFragment(activity: Activity) : CustomRecyclerViewFragment(activity) {
    /** Recycler view for skills   **/
    private var occupationsSkillsRecyclerView: RecyclerView? = null
    set(value){
        Log.d(TAG, "set occupationsSkillsRecyclerView")
        field = value
    }
    /** ViewModel for skills    **/
    private val skillsViewModel: SkillsViewModel by sharedViewModel()
    /** Adapter for skills recycler view    **/
    private var skillsAdapter: OccupationSkillsAdapter? = null
    set(value){
        Log.d(TAG, "set skillsAdapter ${value?.skills?.size}")
        field = value
    }

    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        Log.d(TAG, "initializeRecyclerView")
        occupationsSkillsRecyclerView = activity.findViewById(R.id.recyclerView_occupationsSkills)
                as RecyclerView?
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        Log.d(TAG, "startObservation")
        observeOccupationsSkills()
    }

    private fun observeOccupationsSkills() {
        skillsViewModel?.observedOccupationsSkills.observe(
            this,
            Observer { domainOccupationSkills ->
                Log.d(TAG, "Observed skills has changed. ${domainOccupationSkills.size}")
                Log.d(TAG, "Set adapter skills")
                if(skillsAdapter == null){
                    Log.d(TAG, "Adapter is null")
                    skillsAdapter = OccupationSkillsAdapter(
                        activity as Context
                    )
                }
                Log.d(TAG, "setSkills = ${domainOccupationSkills?.size}")
                skillsAdapter?.setSkills(domainOccupationSkills)
                Log.d(TAG, "skillsAdapter skills size : ${skillsAdapter?.skills?.size}")
                if(occupationsSkillsRecyclerView == null){
                    Log.d(TAG, "occupationsSkillsRecyclerView is null")
                    occupationsSkillsRecyclerView = activity?.findViewById(R.id.recyclerView_occupationsSkills)
                }
                occupationsSkillsRecyclerView?.adapter = skillsAdapter
                Log.d(TAG, "recyclerView adapter list size : ${occupationsSkillsRecyclerView?.adapter?.itemCount}")
                occupationsSkillsRecyclerView?.layoutManager = LinearLayoutManager(
                    activity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            })
    }

    /** Fragment life-cycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return initializeView(inflater, container)
    }

    /** Initialize the view **/
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_occupationsskills, container, false
        )
        return initialRootView
    }


    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {
        Log.d(TAG, "setRecyclerViewLayoutManager")
        occupationsSkillsRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    /**
     * Set the recycler view adapter.
     */
    override fun setRecyclerViewAdapter() {

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        val btnAddSkills = view.findViewById<ImageButton>(R.id.btn_jobs_addSkill)
        btnAddSkills.setOnClickListener {
            val intent = Intent(activity, NewSkillActivity::class.java)
            startActivity(intent)
        }
    }


    companion object : CustomCompanion() {
        private const val TAG = "OccupationsSkillsRecyclerViewFragment"

        @JvmStatic
        override fun newInstance(
            activity: Activity
        ): OccupationsSkillsRecyclerViewFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                OccupationsSkillsRecyclerViewFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, JOBS_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args
            return fragment
        }


    }
}