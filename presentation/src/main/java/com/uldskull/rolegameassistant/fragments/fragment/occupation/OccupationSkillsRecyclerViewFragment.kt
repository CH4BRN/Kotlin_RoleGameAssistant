// File JobSkillsRecyclerViewFragment.kt
// @Author pierre.antoine - 05/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.occupation

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
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.JOB_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.skill.DomainFilledSkill
import com.uldskull.rolegameassistant.viewmodels.OccupationViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationSkillsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "OccupationSkills recyclerView fragment" :
 *   Fragment to display occupation skills.
 **/
class OccupationSkillsRecyclerViewFragment() :
    CustomRecyclerViewFragment(), AdapterButtonListener<DomainFilledSkill> {
    /**
     * Occupation skills adapter.
     */
    private var occupationSkillsAdapter: OccupationSkillsAdapter? = null
    /**
     * Occupation skills viewModel.
     */
    private val occupationSkillsViewModel: OccupationSkillsViewModel by sharedViewModel()

    private val occupationViewModel:OccupationViewModel by sharedViewModel()
    /**
     * Occupation skills recyclerView
     */
    private var occupationSkillsRecyclerView: RecyclerView? = null


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        var skillsToFill =
            occupationSkillsViewModel?.checkedOccupationSkills?.value?.mapNotNull { domainOccupationSkill ->
                if (domainOccupationSkill == null) {
                    return@mapNotNull null
                } else {
                    DomainFilledSkill(
                        filledSkillMax = domainOccupationSkill?.filledSkillMax,
                        filledSkillBase = domainOccupationSkill?.filledSkillBase,
                        filledSkillName = domainOccupationSkill?.skillName,
                        filledSkillTensValue = domainOccupationSkill?.filledSkillTensValue,
                        filledSkillTotal = domainOccupationSkill?.filledSkillTotal,
                        filledSkillUnitsValue = domainOccupationSkill?.filledSkillUnitsValue,
                        filledSkillId = domainOccupationSkill?.skillId
                    )
                }

            }

        occupationSkillsAdapter?.setOccupationFilledSkills(skillsToFill)
        Log.d(
            TAG,
            "occupationSkillAdapter size : ${occupationSkillsAdapter?.occupationSkills?.size}"
        )
        occupationSkillsRecyclerView?.adapter = occupationSkillsAdapter
    }

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_occupationskills, container, false
        )
        return initialRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBtnAddSkillOnClickListener(view)
    }

    /**
     * Fragment life-cycle : Called when the view is created.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(layoutInflater, container)
    }

    /** Fragment life-cycle **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    /** Initialize recycler view    **/
    override fun initializeRecyclerView() {
        Log.d(TAG, "initializeRecyclerView")
        occupationSkillsRecyclerView =
            activity?.findViewById<RecyclerView>(R.id.recycler_view_occupationSkills)

        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        observeCheckedSkills()

    }

    private fun observeCheckedSkills() {
        occupationSkillsViewModel?.checkedOccupationSkills?.observe(
            this,
            Observer { occupationSkills: List<DomainFilledSkill> ->
                kotlin.run {
                    Log.d(TAG, "occupationSkills size : ${occupationSkills?.size}")

                    occupationSkills?.forEach { filledSkill ->
                        kotlin.run {
                            Log.d(TAG + "valid", "filledSkill : ${filledSkill}")
                        }
                    }

                    var skillsToFill =
                        occupationSkills.map { domainOccupationSkill ->
                            DomainFilledSkill(
                                filledSkillMax = domainOccupationSkill?.filledSkillMax,
                                filledSkillBase = domainOccupationSkill?.filledSkillBase,
                                filledSkillName = domainOccupationSkill?.skillName,
                                filledSkillId = domainOccupationSkill?.skillId,
                                filledSkillUnitsValue = domainOccupationSkill?.filledSkillUnitsValue,
                                filledSkillTensValue = domainOccupationSkill?.filledSkillTensValue,
                                filledSkillTotal = domainOccupationSkill?.filledSkillTotal
                            )
                        }
                    skillsToFill?.forEach { filledSkill ->
                        kotlin.run {
                            Log.d(TAG + "valid", "skillsToFill : ${filledSkill}")
                        }
                    }

                    occupationSkillsAdapter?.setOccupationFilledSkills(skillsToFill)
                    Log.d(
                        TAG,
                        "occupationSkillAdapter size : ${occupationSkillsAdapter?.occupationSkills?.size}"
                    )
                    occupationSkillsRecyclerView?.adapter = occupationSkillsAdapter
                }
            })
    }

    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {
        Log.d(TAG, "setRecyclerViewLayoutManager")
        layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        occupationSkillsRecyclerView?.layoutManager = layoutManager

    }


    override fun setRecyclerViewAdapter() {
        Log.d(TAG, "setRecyclerViewAdapter")
        if(activity != null){
            occupationSkillsAdapter =
                OccupationSkillsAdapter(
                    activity!! as Context,
                    this
                )

            occupationSkillsRecyclerView?.adapter = occupationSkillsAdapter
        }

    }


    private var layoutManager: LinearLayoutManager? = null
    private fun setBtnAddSkillOnClickListener(view: View) {
        val btnAddSkills = view.findViewById<ImageButton>(R.id.btn_occupation_addSkill)
        btnAddSkills.setOnClickListener {
            val intent = Intent(activity, NewSkillActivity::class.java)
            startActivity(intent)
        }
    }

    companion object : CustomCompanion() {
        private const val TAG = "OccupationSkillsRecyclerViewFragment"
        @JvmStatic
        override fun newInstance(activity: Activity): OccupationSkillsRecyclerViewFragment {
            val fragment =
                OccupationSkillsRecyclerViewFragment()
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, JOB_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }

    }



    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainFilledSkill?, position: Int?) {
        Log.d("DEBUG", "item pressed for $domainModel")
        TODO("Implements spent points with list")
        var spent = occupationViewModel?.observableSpentOccupationPoints?.value
        if(position != null && spent!= null){
            occupationViewModel?.observableSpentOccupationPointsArray?.value?.set(position, spent)
        }

        if(domainModel?.skillIsSelected != null){
            occupationSkillsViewModel?.currentOccupationSkill.value = domainModel
        }else{
            occupationSkillsViewModel?.currentOccupationSkill.value = null
        }
        var totalSpent:Int? = 0
        occupationViewModel?.observableSpentOccupationPointsArray?.value?.forEach{
            value -> kotlin.run { totalSpent?.plus(value) }
        }

        occupationViewModel?.observableSpentOccupationPoints.value = totalSpent

        occupationViewModel?.observableSpentOccupationPoints?.value = spent    }
}