// File JobSkillsRecyclerViewFragment.kt
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
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewSkillActivity
import com.uldskull.rolegameassistant.fragments.adapter.JOB_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.occupations.OccupationsSkillsAdapter
import com.uldskull.rolegameassistant.viewmodels.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "JobSkillsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class OccupationSkillsRecyclerViewFragment(activity: Activity) : CustomRecyclerViewFragment(activity) {


    private var occupationsSkillsAdapter: OccupationsSkillsAdapter? = null

    private lateinit var skillsViewModel: SkillsViewModel

    private var occupationsSkillsRecyclerView: RecyclerView? = null

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
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
        skillsViewModel = getViewModel()
    }


    /** Initialize recycler view    **/
    override fun initializeRecyclerView() {

        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {

    }

    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {

    }


    override fun setRecyclerViewAdapter() {

    }
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
                OccupationSkillsRecyclerViewFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, JOB_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }

    }
}