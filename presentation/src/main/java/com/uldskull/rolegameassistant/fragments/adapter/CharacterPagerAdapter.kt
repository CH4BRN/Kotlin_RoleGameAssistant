// File CharacterPagerAdapter.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.adapter

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.uldskull.rolegameassistant.fragments.fragment.BasicInfoFragment
import com.uldskull.rolegameassistant.fragments.fragment.HealthFragment
import com.uldskull.rolegameassistant.fragments.fragment.abilities.AbilitiesFragment
import com.uldskull.rolegameassistant.fragments.fragment.abilities.AbilitiesRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.bonds.BondsFragment
import com.uldskull.rolegameassistant.fragments.fragment.derivedValues.DerivedValues1Fragment
import com.uldskull.rolegameassistant.fragments.fragment.derivedValues.DerivedValues2Fragment
import com.uldskull.rolegameassistant.fragments.fragment.ideals.IdealsFragment
import com.uldskull.rolegameassistant.fragments.fragment.job.JobFragment
import com.uldskull.rolegameassistant.fragments.fragment.jobs.JobsFragment
import com.uldskull.rolegameassistant.fragments.fragment.skills.SkillsRecyclerViewFragment

/**
 *   Class "CharacterPagerAdapter" :
 *   Adapter for ViewPager
 **/
class CharacterPagerAdapter(fm: FragmentManager, val activity: Activity) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    /** Number of view  **/
    override fun getCount(): Int = 8

    /** Instantiate the view    **/
    override fun getItem(position: Int): Fragment {

        return when (position) {
            BASIC_INFO_FRAGMENT_POSITION -> BasicInfoFragment.newInstance(
                activity,
                position
            )
            BONDS_FRAGMENT_POSITION -> BondsFragment.newInstance(
                activity,
                position
            )
            IDEALS_FRAGMENT_POSITION -> IdealsFragment.newInstance(
                activity,
                position
            )
            ABILITIES_FRAGMENT_POSITION -> AbilitiesFragment.newInstance(
                activity,
                position
            )
            DERIVED_VALUES_1_FRAGMENT_POSITION -> DerivedValues1Fragment.newInstance(
                activity,
                position
            )
            DERIVED_VALUES_2_FRAGMENT_POSITION -> DerivedValues2Fragment.newInstance(
                activity,
                position
            )
            JOBS_FRAGMENT_POSITION -> JobsFragment.newInstance(
                activity,
                position
            )
            JOB_FRAGMENT_POSITION -> JobFragment.newInstance(
                activity,
                position
            )

            // 1 -> CharacteristicsFragment.newInstance(activity, position)
            // 2 -> BackgroundFragment.newInstance(activity, position)
            3 -> AbilitiesRecyclerViewFragment.newInstance(activity, position)
            4 -> HealthFragment.newInstance(activity, position)
            5 -> SkillsRecyclerViewFragment.newInstance(activity, position)
            else -> BasicInfoFragment.newInstance(activity, position)
        }
    }
}