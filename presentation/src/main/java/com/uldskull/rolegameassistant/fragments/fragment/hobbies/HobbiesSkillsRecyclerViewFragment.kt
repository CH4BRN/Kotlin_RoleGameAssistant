// HobbiesSkillsRecyclerViewFragment.kt created by UldSkull - 09/03/2020

package com.uldskull.rolegameassistant.fragments.fragment.hobbies

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToCheck
import com.uldskull.rolegameassistant.viewmodels.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
Class "HobbiesSkillsRecyclerViewFragment"

Manage hobbies's skill's recyclerview fragmet.
 */
class HobbiesSkillsRecyclerViewFragment :
    CustomRecyclerViewFragment() {
    /** ViewModel for skills  **/
    private lateinit var skillsViewModel: SkillsViewModel

    /** Adapter for skills recycler view    **/
    private var hobbiesSkillAdapter: HobbiesSkillAdapter? = null

    /**  RecyclerView for skills  **/
    private var skillsRecyclerView: RecyclerView? = null

    /** Fragment life-cycle **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skillsViewModel = getViewModel()
    }

    override fun initializeRecyclerView() {
        if (activity != null) {
            skillsRecyclerView =
                activity!!.findViewById(R.id.recycler_view_hobbiesSkills) as RecyclerView?
        }
    }

    /**
     * Fragment life-cycle : Called once the view is created.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeHobbiesSkillsAdapter()
    }

    private fun initializeHobbiesSkillsAdapter() {
        if (activity != null) {
            hobbiesSkillAdapter = HobbiesSkillAdapter(
                activity!! as Context
            )
        }
    }

    /** Observe ViewModel's skills  **/
    override fun startObservation() {

        skillsViewModel?.repositorySkillsToCheck?.observe(this, Observer {
            it.forEach { Log.d("DEBUG$TAG", "Hobbies : $it") }

            var list: MutableList<DomainSkillToCheck> = it.map { s ->
                DomainSkillToCheck(
                    skillBase = s?.skillBase,
                    skillDescription = s?.skillDescription,
                    skillId = s?.skillId,
                    skillIsChecked = s?.skillIsChecked!!,
                    skillMax = s.skillMax,
                    skillName = s.skillName
                )
            }.toMutableList()


            Log.d("DEBUG$TAG", "Hobbies list : ${list}")
            hobbiesSkillAdapter?.setHobbiesSkills(list)
            Log.d(
                "DEBUG$TAG",
                "  hobbiesSkillAdapter?.hobbiesSkills : ${hobbiesSkillAdapter?.hobbiesSkills?.size}"
            )
            skillsRecyclerView?.adapter = hobbiesSkillAdapter
        })


        skillsViewModel?.hobbySkills.observe(this, Observer {
            it.forEach { Log.d("DEBUG$TAG", "Hobby : $it") }
        })

    }

    override fun setRecyclerViewAdapter() {
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

        private const val TAG = "HobbiesSkillsRecyclerViewFragment"

        @JvmStatic
        override fun newInstance(
            activity: Activity
        ): HobbiesSkillsRecyclerViewFragment {

            val fragment = HobbiesSkillsRecyclerViewFragment()
            fragment.activity = activity
            return fragment
        }
    }


}