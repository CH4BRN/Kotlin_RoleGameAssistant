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
        this.skillsViewModel.hobbySkills.observe(this, Observer { skills ->
            kotlin.run {
                skills?.let { hobbySkillAdapter?.setHobbySkills(it) }


            }
        })

        this.skillsViewModel.hobbiesSkills.observe(this, Observer { skills: List<DomainSkillToCheck?> ->
            kotlin.run {
                var checked = skills?.filter { s -> s?.skillIsChecked!! }
                Log.d("DEBUG$TAG", "Checked skill : $checked")

                checked?.forEach {
                    Log.d("DEBUG$TAG", "Skill ${it?.skillName} base : ${it?.skillBase}")
                }

                var toFill = checked?.map { s -> DomainSkillToFill(
                    filledSkillCharacterId = null,
                    filledSkillUnitsValue = 0,
                    filledSkillTotal = 0,
                    filledSkillTensValue = 0,
                    filledSkillName = s?.skillName,
                    filledSkillMax = s?.skillMax,
                    filledSkillBase = s?.skillBase,
                    filledSkillId = s?.skillId,
                    filledSkillType = 1
                ) }

                skillsViewModel?.hobbySkills?.value = toFill
            }
        })
    }

    /**
     * Set the recycler view adapter.
     */
    override fun setRecyclerViewAdapter() {
        if(activity != null){
            hobbySkillAdapter =
                HobbySkillAdapter(
                    context =  activity as Context,
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