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
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomFragment
import com.uldskull.rolegameassistant.fragments.core.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.*
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.OCCUPATIONS_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.models.skill.DomainSkillToCheck
import com.uldskull.rolegameassistant.viewmodels.character.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "OccupationsSkillsRecyclerView" :
 *   TODO: Fill class use.
 **/
class OccupationsSkillsRecyclerViewFragment :
    CustomRecyclerViewFragment(),
    CustomAdapterButtonListener<DomainSkillToCheck> {

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
    /**
     * Occupations view model
     */
    private val occupationsViewModel: OccupationsViewModel by sharedViewModel()

    /**
     * New character view model
     */
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /**
     * occupations skills recycler view
     */
    private var occupationsSkillsRecyclerView: RecyclerView? = null


    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        Log.d(TAG, "initializeRecyclerView")
        occupationsSkillsRecyclerView =
            activity?.findViewById(R.id.recyclerView_occupationsSkills)

        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Occupations skills adapter
     */
    private var occupationsSkillsAdapter: OccupationsSkillsDescriptionAdapter? = null

    /**
     * Initialize occupations skills adapter
     */
    private fun initializeOccupationsSkillsAdapter() {
        if (activity != null) {
            occupationsSkillsAdapter = OccupationsSkillsDescriptionAdapter(
                activity!! as Context,
                this
            )
        }
    }

    /**
     * Fragment life-cycle
     */
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


    /**
     * Observe selected occupation
     */
    private fun observeSelectedOccupation() {
        occupationsViewModel.selectedOccupation?.observe(
            this,
            Observer { domainOccupation: DomainOccupation ->
                kotlin.run {
                    Log.d(TAG, "observeSelectedOccupation $domainOccupation")

                    val index =
                        this.occupationsViewModel.repositoryOccupations?.value?.indexOfFirst { o ->
                            o.occupationId == domainOccupation.occupationId
                        }

                    if (index == occupationsViewModel.selectedOccupationIndex?.value) {
                        Log.d(TAG, "do nothing")
                        //  Do nothing
                    } else {
                        val occupationWithSkills: DomainOccupationWithSkills? =
                            occupationsViewModel.findOneWithChildren(domainOccupation.occupationId)
                        Log.d(TAG, "occupation with skills : \n $occupationWithSkills")

                        occupationsViewModel.observedOccupationsSkills?.value =
                            occupationWithSkills?.skills
                    }
                }
            })
    }

    /**
     * Observe occupation skills
     */
    private fun observeOccupationsSkills() {
        occupationsViewModel.observedOccupationsSkills?.observe(
            this, Observer { domainOccupationsSkills: List<DomainSkillToCheck?> ->

                Log.d(
                    "DEBUG$TAG",
                    "Skills checked : ${domainOccupationsSkills.count { s -> s?.skillIsChecked!! }}"
                )

                kotlin.run {

                    occupationsSkillsAdapter?.setOccupationsSkills(domainOccupationsSkills)


                    val character = newCharacterViewModel.currentCharacter

                    val checkedSkills = mutableListOf<DomainSkillToCheck>()
                    domainOccupationsSkills.forEach { s ->
                        kotlin.run {
                            if (s != null) {
                                if (s.skillIsChecked) {
                                    checkedSkills.add(s)
                                }
                            }
                        }
                    }
                    if (character != null) {
                        character.characterSelectedOccupationSkill =
                            checkedSkills.map { s -> s.skillId }.toMutableList()
                    }
                    newCharacterViewModel.currentCharacter = character

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
                OccupationsSkillsDescriptionAdapter(
                    activity!! as Context,
                    this
                )
            occupationsSkillsAdapter?.setOccupationsSkills(occupationsViewModel.observedOccupationsSkills?.value)
            occupationsSkillsRecyclerView?.adapter = occupationsSkillsAdapter
        }
    }

    /**
     * Layout manager
     */
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
            R.layout.fragment_occupationsskills_recyclerview, container, false
        )
        return initialRootView
    }

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainSkillToCheck?, position: Int?) {
        Log.d(TAG, "itemPressed")
        if (domainModel != null) {

            if (domainModel.skillIsChecked == null) {
                domainModel.skillIsChecked = false
            } else domainModel.skillIsChecked = !domainModel.skillIsChecked

            val temp = occupationsViewModel.observedOccupationsSkills?.value?.toMutableList()
            val index = temp?.indexOfFirst { s -> s.skillId == domainModel.skillId }
            if (index != null) {
                temp[index] = domainModel
            }
            occupationsViewModel.observedOccupationsSkills?.value = temp
        }
    }
}