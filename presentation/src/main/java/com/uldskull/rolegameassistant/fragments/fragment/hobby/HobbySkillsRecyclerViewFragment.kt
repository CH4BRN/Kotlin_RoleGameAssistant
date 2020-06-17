// HobbySkillsRecyclerViewFragment.kt created by UldSkull - 11/03/2020

package com.uldskull.rolegameassistant.fragments.fragment.hobby

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
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.HOBBIES_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToCheck
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.hobbies.HobbySkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
Class "HobbySkillsRecyclerViewFragment"

TODO: Describe class utility.
 */
class HobbySkillsRecyclerViewFragment :
    CustomRecyclerViewFragment(), AdapterButtonListener<DomainSkillToFill> {

    private val hobbySkillViewModel: HobbySkillsViewModel by sharedViewModel()

    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    private var hobbySkillRecyclerView: RecyclerView? = null

    private var hobbySkillAdapter: HobbySkillAdapter? = null


    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        hobbySkillRecyclerView = activity?.findViewById(R.id.recycler_view_hobbySkills)
                as RecyclerView?
        startObservation()
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()

    }

    private val skillsViewModel: SkillsViewModel by sharedViewModel()

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {


        this.skillsViewModel.hobbySkills.observe(this, Observer { skills: List<DomainSkillToFill> ->
            kotlin.run {
                Log.d("DEBUG$TAG", "hobbySkills : ${skills.size}")

                var newList = mutableListOf<DomainSkillToFill>()

                skills?.forEach {
                    var skill =
                        hobbySkillViewModel?.checkedHobbySkills?.value?.find { s -> s.skillId == it.skillId }
                    if (skill != null) {
                        Log.d("DEBUG$TAG", "Skill null Add : $skill")
                        newList?.add(skill)
                    } else {
                        Log.d("DEBUG$TAG", "Skill not null Add : $it")
                        newList?.add(it)
                    }
                }

                if (newList.toString() != skillsViewModel.hobbySkills.value.toString()) {
                    Log.d("DEBUG$TAG", "Different list")
                    skillsViewModel.hobbySkills.value = newList
                } else {
                    Log.d("DEBUG$TAG", "Same list")
                }

                skills?.let { hobbySkillAdapter?.setHobbySkills(skills) }
            }
        })

        this.skillsViewModel.hobbiesSkills.observe(
            this,
            Observer { skills: List<DomainSkillToCheck?> ->
                kotlin.run {
                    var newList: MutableList<DomainSkillToFill> = mutableListOf()
                    //  Get the checked skills
                    var hobbiesChecked = skills?.filter { s -> s?.skillIsChecked!! }

                    hobbiesChecked?.forEach {
                        Log.d("DEBUG$TAG", "hobbiesChecked : ${it?.skillName}")
                    }
                    //  Map the checked skills
                    var hobbyChecked: List<DomainSkillToFill> =
                        mapSkillToCheckToSkillToFill(hobbiesChecked)
                    hobbyChecked?.forEach {
                        Log.d(
                            "DEBUG$TAG",
                            "hobbyChecked : ${it.skillName} - ${it.filledSkillTensValue}${it.filledSkillUnitsValue}"
                        )
                    }
                    // Gets character skills
                    var characterSkills: List<DomainSkillToFill>? = null
                    if (skillsViewModel?.characterHobbySkills != null) characterSkills =
                        skillsViewModel?.characterHobbySkills

                    if (characterSkills == null) {
                        // If there is not character skills, the new list is the previously checked skills
                        newList = hobbyChecked.toMutableList()
                    } else {
                        //  else foreach checked
                        hobbyChecked?.forEach { checkedSkill: DomainSkillToFill ->
                            kotlin.run {
                                var found: DomainSkillToFill?
                                found = characterSkills?.find { characterSkill ->
                                    ((characterSkill?.skillName == checkedSkill?.skillName) && (characterSkill.skillDescription == checkedSkill?.skillDescription))
                                }

                                if (found == null) {
                                    newList?.add(checkedSkill)
                                } else {
                                    newList?.add(found)
                                }
                            }

                        }
                    }
                    Log.d("DEBUG$TAG", "newList skills : ${newList}")
                    skillsViewModel?.hobbySkills.value = newList
                }
            })
    }

    private fun mapSkillToCheckToSkillToFill(hobbiesChecked: List<DomainSkillToCheck?>): List<DomainSkillToFill> {
        var hobbyChecked: List<DomainSkillToFill> =
            hobbiesChecked?.map { hobbiesSkill: DomainSkillToCheck? ->
                DomainSkillToFill(
                    filledSkillType = 1,
                    filledSkillTensValue = 0,
                    filledSkillName = hobbiesSkill?.skillName,
                    filledSkillMax = hobbiesSkill?.skillMax,
                    filledSkillBase = hobbiesSkill?.skillBase,
                    filledSkillTotal = 0,
                    filledSkillUnitsValue = 0,
                    filledSkillId = null,
                    filledSkillCharacterId = newCharacterViewModel.currentCharacter?.characterId
                )
            }
        return hobbyChecked
    }

    /**
     * Set the recycler view adapter.
     */
    override fun setRecyclerViewAdapter() {
        if (activity != null) {
            hobbySkillAdapter =
                HobbySkillAdapter(
                    context = activity as Context,
                    hobbySkillsRecyclerViewFragment_buttonListener = this
                )
            hobbySkillRecyclerView?.adapter = hobbySkillAdapter
        }

    }

    /**
     * Set the RecyclerView's layout manager.
     */
    override fun setRecyclerViewLayoutManager() {
        hobbySkillRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_hobbyskills, container, false
        )
        return initialRootView
    }

    companion object : CustomCompanion() {
        private const val TAG = "HobbySkillsRecyclerViewFragment"

        @JvmStatic
        override fun newInstance(
            activity: Activity
        ): HobbySkillsRecyclerViewFragment {
            val fragment = HobbySkillsRecyclerViewFragment()
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, HOBBIES_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }


    }

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainSkillToFill?, position: Int?) {
        hobbySkillViewModel.currentHobbySkill.value = domainModel
    }
}