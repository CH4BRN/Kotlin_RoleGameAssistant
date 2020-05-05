// File JobFragment.kt
// @Author pierre.antoine - 05/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.occupation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.NewSkillActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.OCCUPATION_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.skill.DomainFilledSkill
import com.uldskull.rolegameassistant.viewmodels.OccupationSkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.OccupationsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "JobFragment" :
 *   TODO: Fill class use.
 **/
class OccupationFragment(activity: Activity) : CustomFragment(activity) {

    val occupationsViewModel: OccupationsViewModel by sharedViewModel()
    val occupationSkillsViewModel: OccupationSkillsViewModel by sharedViewModel()

    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_occupation, container, false
        )
        return initialRootView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return initializeView(inflater, container)
    }

    /**
     * Called immediately after [.onCreateView]
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     * @param view The View returned by [.onCreateView].
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAddSkill = view.findViewById<ImageButton>(R.id.btn_job_add_skill)

        btnAddSkill.setOnClickListener {
            val intent = Intent(activity, NewSkillActivity::class.java)
            startActivity(intent)

        }
    }

    /**
     * Called when the focus return on this view
     */
    override fun onResume() {
        super.onResume()
        Log.d("JobFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = OCCUPATION_FRAGMENT_POSITION
        Log.d("JobFragment_2", NewCharacterActivity.progression.value.toString())
        var skills = occupationsViewModel?.observedOccupationsSkills?.value
        if (skills != null) {
            var checkedSkills = skills!!.filter { s ->
                s.skillIsChecked
            }
            Log.d(TAG, "checked skills size : ${checkedSkills.size}")
            var list: MutableList<DomainFilledSkill> = mutableListOf()
            checkedSkills?.forEach() { occupationSkill ->
                kotlin.run {
                    list.add(
                        DomainFilledSkill(
                            filledSkillTensValue = 0,
                            filledSkillUnitsValue = 0,
                            filledSkillName = occupationSkill?.skillName,
                            filledSkillTotal = 0,
                            filledSkillBase = occupationSkill?.skillBase,
                            filledSkillMax = occupationSkill?.skillMax
                        )
                    )
                }

            }

            occupationSkillsViewModel.occupationSkills.value = checkedSkills
        }
    }



    companion object : CustomCompanion() {

        private const val TAG = "OccupationFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): OccupationFragment {
            val fragment =
                OccupationFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, OCCUPATION_FRAGMENT_POSITION)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_jobSkills,
                OccupationSkillsRecyclerViewFragment.newInstance(
                    activity
                )
            )

            return fragment
        }
    }
}