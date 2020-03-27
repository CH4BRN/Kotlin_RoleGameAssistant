// File CharacterPagerAdapter.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.adapter

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.uldskull.rolegameassistant.fragments.fragment.basicinfo.BasicInfoFragment
import com.uldskull.rolegameassistant.fragments.fragment.bonds.BondsFragment
import com.uldskull.rolegameassistant.fragments.fragment.characteristics.CharacteristicsFragment
import com.uldskull.rolegameassistant.fragments.fragment.derivedValues.DerivedValues1Fragment
import com.uldskull.rolegameassistant.fragments.fragment.derivedValues.DerivedValues2Fragment
import com.uldskull.rolegameassistant.fragments.fragment.hobbies.HobbiesFragment
import com.uldskull.rolegameassistant.fragments.fragment.hobby.HobbyFragment
import com.uldskull.rolegameassistant.fragments.fragment.ideals.IdealsFragment
import com.uldskull.rolegameassistant.fragments.fragment.job.JobFragment
import com.uldskull.rolegameassistant.fragments.fragment.jobs.JobsFragment

/**
 *   Class "CharacterPagerAdapter" :
 *   Adapter for ViewPager
 **/
class CharacterPagerAdapter(fm: FragmentManager, val activity: Activity) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    /** Number of view  **/
    override fun getCount(): Int = 10

    /** Instantiate the view    **/
    override fun getItem(position: Int): Fragment {

        return when (position) {
            BASIC_INFO_FRAGMENT_POSITION -> BasicInfoFragment.newInstance(
                activity
            )
            BONDS_FRAGMENT_POSITION -> BondsFragment.newInstance(
                activity
            )
            IDEALS_FRAGMENT_POSITION -> IdealsFragment.newInstance(
                activity
            )
            ABILITIES_FRAGMENT_POSITION -> CharacteristicsFragment.newInstance(
                activity
            )
            DERIVED_VALUES_1_FRAGMENT_POSITION -> DerivedValues1Fragment.newInstance(
                activity
            )
            DERIVED_VALUES_2_FRAGMENT_POSITION -> DerivedValues2Fragment.newInstance(
                activity
            )
            JOBS_FRAGMENT_POSITION -> JobsFragment.newInstance(
                activity
            )
            JOB_FRAGMENT_POSITION -> JobFragment.newInstance(
                activity
            )
            HOBBIES_FRAGMENT_POSITION -> HobbiesFragment.newInstance(
                activity
            )
            HOBBY_FRAGMENT_POSITION -> HobbyFragment.newInstance(
                activity
            )
            else -> BasicInfoFragment.newInstance(activity)
        }
    }
}