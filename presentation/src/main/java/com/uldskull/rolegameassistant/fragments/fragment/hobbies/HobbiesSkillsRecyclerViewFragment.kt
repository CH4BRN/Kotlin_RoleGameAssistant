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
import com.uldskull.rolegameassistant.fragments.fragment.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToCheck
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
Class "HobbiesSkillsRecyclerViewFragment"

Manage hobbies's skill's recyclerview fragmet.
 */
class HobbiesSkillsRecyclerViewFragment :
    CustomRecyclerViewFragment(),
    CustomAdapterButtonListener<DomainSkillToCheck> {
    /** ViewModel for skills  **/
    private val skillsViewModel: SkillsViewModel by sharedViewModel()

    /**
     * Viewmodel for character
     */
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /** Adapter for skills recycler view    **/
    private var hobbiesSkillAdapter: HobbiesSkillAdapter? = null

    /**  RecyclerView for skills  **/
    private var skillsRecyclerView: RecyclerView? = null

    /** Fragment life-cycle **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * initialize recycler view
     */
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
        startObservation()
    }

    /**
     * initialize hobbies skill adapter
     */
    private fun initializeHobbiesSkillsAdapter() {
        if (activity != null) {
            hobbiesSkillAdapter = HobbiesSkillAdapter(
                context = activity!! as Context,
                buttonListenerCustom = this
            )
        }
    }

    /** Observe ViewModel's skills  **/
    override fun startObservation() {
        Log.d("DEBUG$TAG", "StartObservation")
        observeHobbiesSkills()
    }

    /**
     * observe hobbies skills
     */
    private fun observeHobbiesSkills() {
        skillsViewModel?.hobbiesSkills?.observe(this, Observer { domainHobbiesSkills ->
            run {
                hobbiesSkillAdapter?.setHobbiesSkills(domainHobbiesSkills)

                var character = newCharacterViewModel?.currentCharacter

                var checkedSkills = mutableListOf<DomainSkillToCheck>()
                domainHobbiesSkills?.forEach { s ->
                    kotlin.run {
                        if (s != null) {
                            if (s.skillIsChecked) {
                                checkedSkills?.add(s)
                            }
                        }
                    }
                    if (character != null) {
                        character?.characterSelectedHobbiesSkill =
                            checkedSkills?.map { s -> s.skillId }.toMutableList()
                    }
                    newCharacterViewModel?.currentCharacter = character
                    skillsRecyclerView?.adapter = hobbiesSkillAdapter
                }
            }
        })
    }

    override fun setRecyclerViewAdapter() {
    }

    /**
     * Set recycler view layout manager
     */
    override fun setRecyclerViewLayoutManager() {
        skillsRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    /**
     * Initialize view
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_hobbiesskills_recyclerview, container, false
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

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainSkillToCheck?, position: Int?) {
        if (domainModel != null) {
            var temp = skillsViewModel.hobbiesSkills?.value?.toMutableList()
            var checked = temp?.count { s -> s?.skillIsChecked!! }
            var index = temp?.indexOfFirst { s -> s?.skillId == domainModel.skillId }
            if (index != null) {
                temp?.removeAt(index)
                temp?.add(index, domainModel)
            }
            checked = temp?.count { s -> s?.skillIsChecked!! }
            skillsViewModel?.hobbiesSkills?.value = temp
        }
    }


}