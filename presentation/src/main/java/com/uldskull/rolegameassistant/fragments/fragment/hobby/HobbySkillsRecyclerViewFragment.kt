// HobbySkillsRecyclerViewFragment.kt created by UldSkull - 11/03/2020

package com.uldskull.rolegameassistant.fragments.fragment.hobby

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.HOBBIES_SKILLS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.viewmodels.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
Class "HobbySkillsRecyclerViewFragment"

TODO: Describe class utility.
 */
class HobbySkillsRecyclerViewFragment() :
    CustomRecyclerViewFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skillsViewModel = getViewModel()
    }

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

    private lateinit var skillsViewModel: SkillsViewModel

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        this.skillsViewModel.hobbySkills.observe(this, Observer { skills ->
            kotlin.run {
                skills?.let { hobbySkillAdapter?.setHobbySkills(it) }

            }
        })
    }

    /**
     * Set the recycler view adapter.
     */
    override fun setRecyclerViewAdapter() {
        hobbySkillAdapter =
            HobbySkillAdapter(
                activity as Context
            )
        hobbySkillRecyclerView?.adapter = hobbySkillAdapter
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
}