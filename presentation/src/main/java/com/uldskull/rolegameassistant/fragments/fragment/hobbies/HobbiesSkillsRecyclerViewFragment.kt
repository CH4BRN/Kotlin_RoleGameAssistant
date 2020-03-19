// HobbiesSkillsRecyclerViewFragment.kt created by UldSkull - 09/03/2020

package com.uldskull.rolegameassistant.fragments.fragment.hobbies

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
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.skills.JobsSkillsAdapter
import com.uldskull.rolegameassistant.fragments.fragment.skills.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
Class "HobbiesSkillsRecyclerViewFragment"

Manage hobbies's skill's recyclerview fragmet.
 */
class HobbiesSkillsRecyclerViewFragment(activity: Activity) :
    CustomRecyclerViewFragment(activity) {
    /** ViewModel for skills  **/
    private lateinit var skillsViewModel: SkillsViewModel

    /** Adapter for skills recycler view    **/
    private var skillsAdapter: JobsSkillsAdapter? = null

    /**  RecyclerView for skills  **/
    private var skillsRecyclerView: RecyclerView? = null

    /** Fragment life-cycle **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skillsViewModel = getViewModel()
    }

    override fun initializeRecyclerView() {
        skillsRecyclerView =
            activity.findViewById(R.id.recycler_view_hobbiesSkills) as RecyclerView?

    }

    /** Observe ViewModel's skills  **/
    override fun startObservation() {
        this.skillsViewModel.hobbiesSkills.observe(this, Observer { skills ->
            kotlin.run {
                skills?.let { skillsAdapter?.setSkills(it) }
            }
        })

    }

    override fun setRecyclerViewAdapter() {
        skillsAdapter =
            JobsSkillsAdapter(
                activity as Context
            )
        skillsRecyclerView?.adapter = skillsAdapter
    }

    override fun setRecyclerViewLayoutManager() {
        skillsRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }


    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_hobbiesskills, container, false
        )
        return initialRootView
    }

    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(
            activity: Activity
        ): HobbiesSkillsRecyclerViewFragment {

            return HobbiesSkillsRecyclerViewFragment(
                activity
            )
        }
    }


}