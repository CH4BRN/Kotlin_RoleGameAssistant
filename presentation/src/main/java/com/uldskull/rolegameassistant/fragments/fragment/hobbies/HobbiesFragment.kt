// HobbiesFragment.kt created by UldSkull - 09/03/2020

package com.uldskull.rolegameassistant.fragments.fragment.hobbies

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.HOBBIES_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION

/**
Class "HobbiesFragment"

TODO: Describe class utility.
 */
class HobbiesFragment : CustomFragment() {
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_hobbies, container, false
        )
        return initialRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadHobbiesSkillRecyclerView()

    }

    private fun loadHobbiesSkillRecyclerView() {
        if(activity != null){
            var transaction = childFragmentManager.beginTransaction()
            transaction.replace(
                R.id.container_hobbiesSkills,
                HobbiesSkillsRecyclerViewFragment.newInstance(
                    activity!!
                )
            ).commit()
        }

    }



    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity): HobbiesFragment {
            val fragment =
                HobbiesFragment()
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, HOBBIES_FRAGMENT_POSITION)
            fragment.arguments = args



            return fragment
        }
    }
}