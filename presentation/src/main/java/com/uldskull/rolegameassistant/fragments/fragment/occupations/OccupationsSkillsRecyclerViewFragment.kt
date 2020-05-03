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
import com.uldskull.rolegameassistant.fragments.adapter.OCCUPATIONS_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.*
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill
import com.uldskull.rolegameassistant.viewmodels.OccupationsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "OccupationsSkillsRecyclerView" :
 *   TODO: Fill class use.
 **/
class OccupationsSkillsRecyclerViewFragment(activity: Activity) :
    CustomRecyclerViewFragment(activity),
    AdapterButtonListener<DomainOccupationSkill> {

    companion object : CustomCompanion() {
        private const val TAG = "OccupationsSkillsRecyclerView"
        override fun newInstance(activity: Activity): CustomFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                OccupationsSkillsRecyclerViewFragment(
                    activity
                )
            val args = Bundle()
            args.putInt(KEY_POSITION, OCCUPATIONS_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args
            return fragment
        }
    }

    //  VIEWMODELS
    private val occupationsViewModel: OccupationsViewModel by sharedViewModel()
    private var occupationsSkillsRecyclerView: RecyclerView? = null
    /*    get() {

            field?.adapter = occupationsViewModel.occupationsSkillsAdapter
            return field
        }*/

    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        Log.d(TAG, "initializeRecyclerView")
        layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        occupationsSkillsRecyclerView =
            activity?.findViewById<RecyclerView>(R.id.recyclerView_occupationsSkills)

    }

    var occupationsSkillsAdapter: OccupationsSkillsAdapter? =
        OccupationsSkillsAdapter(
            activity as Context,
            this
        )


    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        observeSelectedOccupation()
        observeOccupationsSkills()
    }

    private fun observeSelectedOccupation() {
        occupationsViewModel?.selectedOccupation?.observe(
            this,
            Observer { domainOccupation: DomainOccupation ->
                kotlin.run {
                    Log.d(TAG, "observeSelectedOccupation $domainOccupation")

                    var index =
                        this.occupationsViewModel?.displayedOccupations?.indexOfFirst { o ->
                            o?.occupationId == domainOccupation?.occupationId
                        }
                    Log.d(TAG, "new selected index : $index\n " +
                            "old selected index : ${occupationsViewModel?.selectedOccupationIndex?.value}")
                    if (index == occupationsViewModel?.selectedOccupationIndex?.value) {
                        Log.d(TAG, "do nothing")
                        //  Do nothing
                    } else {
                        var occupationWithSkills: DomainOccupationWithSkills? =
                            occupationsViewModel.findOneWithChildren(domainOccupation.occupationId)
                        Log.d(TAG, "\n$occupationWithSkills")

                        occupationsViewModel.observedOccupationsSkills?.value =
                            occupationWithSkills?.skills
                    }
                }
            })
    }

    private fun observeOccupationsSkills() {
        occupationsViewModel?.observedOccupationsSkills?.observe(
            this, Observer { domainOccupationsSkills: List<DomainOccupationSkill?> ->
                kotlin.run {
                    Log.d(TAG, "observeOccupationsSkills")
                    occupationsSkillsAdapter?.setOccupationsSkills(domainOccupationsSkills)
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
        occupationsSkillsAdapter =
            OccupationsSkillsAdapter(
                activity as Context,
                this
            )
        occupationsSkillsAdapter?.setOccupationsSkills(emptyList())
        occupationsSkillsRecyclerView?.adapter = occupationsSkillsAdapter
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
    override fun itemPressed(domainModel: DomainOccupationSkill?) {
        Log.d(TAG, "itemPressed")
        if (domainModel != null) {
            var temp = occupationsViewModel.observedOccupationsSkills?.value?.toMutableList()
            Log.d(TAG, "skills size : ${temp?.size}")
            var index = temp?.indexOfFirst { s -> s?.skillId == domainModel?.skillId }
            if (index != null) {
                temp?.removeAt(index)

                Log.d(TAG, "skills size : ${temp?.size}")
                temp?.add(index, domainModel)
            }

            occupationsViewModel.observedOccupationsSkills?.value = temp
        }
    }
}