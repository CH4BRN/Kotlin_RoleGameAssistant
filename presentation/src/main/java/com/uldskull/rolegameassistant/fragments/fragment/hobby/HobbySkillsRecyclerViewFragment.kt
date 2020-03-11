// HobbySkillsRecyclerViewFragment.kt created by UldSkull - 11/03/2020

package com.uldskull.rolegameassistant.fragments.fragment.hobby

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION

/**
Class "HobbySkillsRecyclerViewFragment"

TODO: Describe class utility.
 */
class HobbySkillsRecyclerViewFragment (activity: Activity) :
    CustomRecyclerViewFragment(activity) {
    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        TODO("Not yet implemented")
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        TODO("Not yet implemented")
    }

    /**
     * Set the recycler view adapter.
     */
    override fun setRecyclerViewAdapter() {
        TODO("Not yet implemented")
    }

    /**
     * Set the RecyclerView's layout manager.
     */
    override fun setRecyclerViewLayoutManager() {
        TODO("Not yet implemented")
    }

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        TODO("Not yet implemented")
    }

    companion object {

        @JvmStatic
        fun newInstance(activity: Activity, position: Int): HobbySkillsRecyclerViewFragment {
            val fragment = HobbySkillsRecyclerViewFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }


    }
}