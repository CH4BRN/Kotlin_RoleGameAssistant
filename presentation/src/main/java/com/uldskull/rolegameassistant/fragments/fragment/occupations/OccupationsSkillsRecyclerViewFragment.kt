// File OccupationsSkillsRecyclerView.kt
// @Author pierre.antoine - 02/05/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.occupations

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
import com.uldskull.rolegameassistant.fragments.fragment.*
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.OCCUPATIONS_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToCheck
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "OccupationsSkillsRecyclerView" :
 *   TODO: Fill class use.
 **/
class OccupationsSkillsRecyclerViewFragment :
    CustomRecyclerViewFragment(),
    AdapterButtonListener<DomainSkillToCheck> {

    companion object : CustomCompanion() {
        private const val TAG = "OccupationsSkillsRecyclerView"
        override fun newInstance(activity: Activity): CustomFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                OccupationsSkillsRecyclerViewFragment()
            fragment.activity = activity
            val args = Bundle()
            args.putInt(KEY_POSITION, OCCUPATIONS_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args
            return fragment
        }
    }

    //  VIEWMODELS
    private val occupationsViewModel: OccupationsViewModel by sharedViewModel()
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()
    private var occupationsSkillsRecyclerView: RecyclerView? = null


    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        Log.d(TAG, "initializeRecyclerView")
        occupationsSkillsRecyclerView =
            activity?.findViewById<RecyclerView>(R.id.recyclerView_occupationsSkills)

        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    var occupationsSkillsAdapter: OccupationsSkillsAdapter? = null

    private fun initializeOccupationsSkillsAdapter() {
        if (activity != null) {
            occupationsSkillsAdapter = OccupationsSkillsAdapter(
                activity!! as Context,
                this
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeOccupationsSkillsAdapter()
    }


    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        observeSelectedOccupation()
        observeOccupationsSkills()
    }


    private fun observeSelectedOccupation() {
        occupationsViewModel.selectedOccupation?.observe(
            this,
            Observer { domainOccupation: DomainOccupation ->
                kotlin.run {
                    Log.d(TAG, "observeSelectedOccupation $domainOccupation")

                    var index =
                        this.occupationsViewModel.repositoryOccupations?.value?.indexOfFirst { o ->
                            o?.occupationId == domainOccupation.occupationId
                        }

                    if (index == occupationsViewModel.selectedOccupationIndex?.value) {
                        Log.d(TAG, "do nothing")
                        //  Do nothing
                    } else {
                        var occupationWithSkills: DomainOccupationWithSkills? =
                            occupationsViewModel.findOneWithChildren(domainOccupation.occupationId)
                        Log.d(TAG, "occupation with skills : \n $occupationWithSkills")

                        occupationsViewModel.observedOccupationsSkills?.value =
                            occupationWithSkills?.skills
                    }
                }
            })
    }

    private fun observeOccupationsSkills() {

        occupationsViewModel.observedOccupationsSkills?.observe(
            this, Observer { domainOccupationsSkills: List<DomainSkillToCheck?> ->

                Log.d(
                    "DEBUG$TAG",
                    "Skills checked : ${domainOccupationsSkills?.count { s -> s?.skillIsChecked!! }}"
                )

                kotlin.run {

                    occupationsSkillsAdapter?.setOccupationsSkills(domainOccupationsSkills)


                    var character = newCharacterViewModel?.currentCharacter

                    var checkedSkills = mutableListOf<DomainSkillToCheck>()
                    domainOccupationsSkills?.forEach { s ->
                        kotlin.run {
                            if (s != null) {
                                if (s.skillIsChecked) {
                                    checkedSkills?.add(s)
                                }
                            }
                        }
                    }
                    if (character != null) {
                        character?.characterSelectedOccupationSkill =
                            checkedSkills?.map { s -> s.skillId }.toMutableList()
                    }
                    newCharacterViewModel?.currentCharacter = character

                    occupationsSkillsRecyclerView?.adapter = occupationsSkillsAdapter

                }
            }
        )
    }


    /**
     * Set the recycler view adapter.
     */
    override fun setRecyclerViewAdapter() {
        Log.d(TAG, "setRecyclerViewAdapter")
        if (activity != null) {
            occupationsSkillsAdapter =
                OccupationsSkillsAdapter(
                    activity!! as Context,
                    this
                )
            occupationsSkillsAdapter?.setOccupationsSkills(occupationsViewModel.observedOccupationsSkills?.value)
            occupationsSkillsRecyclerView?.adapter = occupationsSkillsAdapter
        }
    }

    private var layoutManager: LinearLayoutManager? = null

    /**
     * Set the RecyclerView's layout manager.
     */
    override fun setRecyclerViewLayoutManager() {
        Log.d(TAG, "setRecyclerViewLayoutManager")
        layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        occupationsSkillsRecyclerView?.layoutManager = layoutManager
    }

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_occupationsskills, container, false
        )
        return initialRootView
    }

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainSkillToCheck?, position: Int?) {
        Log.d(TAG, "itemPressed")
        if (domainModel != null) {

            if (domainModel?.skillIsChecked == null) {
                domainModel?.skillIsChecked = false
            } else domainModel?.skillIsChecked = !domainModel?.skillIsChecked!!

            var temp = occupationsViewModel.observedOccupationsSkills?.value?.toMutableList()
            var index = temp?.indexOfFirst { s -> s.skillId == domainModel.skillId }
            if (index != null) {
                temp?.set(index, domainModel)
            }
            occupationsViewModel.observedOccupationsSkills?.value = temp
        }
    }
}