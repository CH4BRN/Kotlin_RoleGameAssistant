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
import com.uldskull.rolegameassistant.activities.skills.EditSkillActivity
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.JOB_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.viewmodels.PointsToSpendViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationSkillsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "OccupationSkills recyclerView fragment" :
 *   Fragment to display occupation skills.
 **/
class OccupationSkillsRecyclerViewFragment :
    CustomRecyclerViewFragment(),
    CustomAdapterButtonListener<DomainSkillToFill> {

    /**
     * Occupation skills adapter.
     */
    private var occupationSkillsAdapter: OccupationSkillsAdapter? = null
    /**
     * Occupation skills viewModel.
     */
    private val occupationSkillsViewModel: OccupationSkillsViewModel by sharedViewModel()


    /**
     * Points to spend view model
     */
    private val pointsToSpendViewModel:PointsToSpendViewModel by sharedViewModel()
    /**
     * Occupation skills recyclerView
     */
    private var occupationSkillsRecyclerView: RecyclerView? = null

    /**
     * Fragment lifecycle
     */
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        occupationSkillsRecyclerView?.adapter = occupationSkillsAdapter
    }

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_occupationskills_recyclerview, container, false
        )
        return initialRootView
    }

    /**
     * Fragment lifecycle
     */
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


    /** Initialize recycler view    **/
    override fun initializeRecyclerView() {
        Log.d(TAG, "initializeRecyclerView")
        occupationSkillsRecyclerView =
            activity?.findViewById(R.id.recycler_view_occupationSkills)

        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        observeCheckedSkills()

    }

    /**
     * Observe checked skills
     */
    private fun observeCheckedSkills() {
        occupationSkillsViewModel.checkedOccupationSkills.observe(
            this,
            Observer { occupationSkills: List<DomainSkillToFill> ->
                kotlin.run {
                    fillOccupationSkillsRecyclerViewWithSkillsToFill(occupationSkills)
                }
            })
    }

    /**
     * Fil occupation skills recycler view with skills to fill
     */
    private fun fillOccupationSkillsRecyclerViewWithSkillsToFill(occupationSkills: List<DomainSkillToFill>) {
        Log.d("DEBUG$TAG", "checkedOccupationSkills size : ${occupationSkills.size}")

        occupationSkills.forEach { filledSkill ->
            kotlin.run {
                Log.d("DEBUG$TAG", "filledSkill : $filledSkill")
            }
        }

        val skillsToFill =
            occupationSkills.map { domainOccupationSkill ->
                DomainSkillToFill(
                    filledSkillMax = domainOccupationSkill.filledSkillMax,
                    filledSkillBase = domainOccupationSkill.filledSkillBase,
                    filledSkillName = domainOccupationSkill.skillName,
                    filledSkillId = domainOccupationSkill.skillId,
                    filledSkillUnitsValue = domainOccupationSkill.filledSkillUnitsValue,
                    filledSkillTensValue = domainOccupationSkill.filledSkillTensValue,
                    filledSkillTotal = domainOccupationSkill.filledSkillTotal,
                    filledSkillCharacterId = domainOccupationSkill.filledSkillCharacterId,
                    filledSkillType = 0
                )
            }
        val size = skillsToFill.size
        if (size != null) {
            pointsToSpendViewModel.observableOccupationSpentTensPointsArray.value = arrayOfNulls(
                skillsToFill.size
            )
        }

        skillsToFill.forEach { filledSkill ->
            kotlin.run {
                Log.d(TAG + "valid", "skillsToFill : $filledSkill")
            }
        }

        occupationSkillsAdapter?.setOccupationFilledSkills(skillsToFill)
        Log.d(
            TAG,
            "occupationSkillAdapter size : ${occupationSkillsAdapter?.occupationSkills?.size}"
        )
        occupationSkillsRecyclerView?.adapter = occupationSkillsAdapter
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

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainSkillToFill?, position: Int?) {
        Log.d("DEBUG", "item pressed for $domainModel \n\tat position $position")
        pointsToSpendViewModel.observableCurrentOccupationSkillPosition.value = position
        occupationSkillsViewModel.currentOccupationSkill.value = domainModel
    }

    /**
     * Set recycler view adapter
     */
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


    /**
     * layout manager
     */
    private var layoutManager: LinearLayoutManager? = null

    /**
     * set button add skill on click listener
     */
    private fun setBtnAddSkillOnClickListener(view: View) {
        val btnAddSkills = view.findViewById<ImageButton>(R.id.btn_occupation_addSkill)
        btnAddSkills.setOnClickListener {
            val intent = Intent(activity, EditSkillActivity::class.java)
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
}