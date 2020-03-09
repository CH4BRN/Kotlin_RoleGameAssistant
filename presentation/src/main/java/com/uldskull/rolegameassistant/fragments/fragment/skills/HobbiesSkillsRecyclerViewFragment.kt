// HobbiesSkillsRecyclerViewFragment.kt created by UldSkull - 09/03/2020

package com.uldskull.rolegameassistant.fragments.fragment.skills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
Class "HobbiesSkillsRecyclerViewFragment"

Manage hobbies's skill's recyclerview fragmet.
 */
class HobbiesSkillsRecyclerViewFragment(activity: NewCharacterActivity) :
    CustomRecyclerViewFragment(activity) {
    /** ViewModel for skills  **/
    private lateinit var skillsViewModel: SkillsViewModel

    /** Adapter for skills recycler view    **/
    private var skillsAdapter: SkillsAdapter? = null

    /**  RecyclerView for skills  **/
    private var skillsRecyclerView: RecyclerView? = null

    /** Fragment life-cycle **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skillsViewModel = getViewModel()
    }

    override fun startObservation() {
        TODO("Not yet implemented")
    }

    override fun setAdapter() {
        TODO("Not yet implemented")
    }

    override fun setRecyclerViewLayoutManager() {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_skills, container, false
        )
        return initialRootView
    }



}