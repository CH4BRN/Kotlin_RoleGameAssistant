// File CharacterPagerAdapter.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.presentation.fragments.adapter

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.uldskull.rolegameassistant.presentation.fragments.fragment.BackgroundFragment
import com.uldskull.rolegameassistant.presentation.fragments.fragment.BasicInfoFragment
import com.uldskull.rolegameassistant.presentation.fragments.fragment.CharacteristicsFragment
import com.uldskull.rolegameassistant.presentation.fragments.fragment.HealthFragment
import com.uldskull.rolegameassistant.presentation.fragments.fragment.abilities.AbilitiesRecyclerViewFragment
import com.uldskull.rolegameassistant.presentation.fragments.fragment.skills.SkillsRecyclerViewFragment

/**
 *   Class "CharacterPagerAdapter" :
 *   Adapter for ViewPager
 **/
class CharacterPagerAdapter(fm: FragmentManager, val activity: Activity) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    /** Number of view  **/
    override fun getCount(): Int = 6

    /** Instantiate the view    **/
    override fun getItem(position: Int): Fragment {

        var fragment: Fragment? = null
        return when (position) {
            0 -> BasicInfoFragment.newInstance(activity, position)
            1 -> CharacteristicsFragment.newInstance(activity, position)
            2 -> BackgroundFragment.newInstance(activity, position)
            3 -> AbilitiesRecyclerViewFragment.newInstance(activity, position)
            4 -> HealthFragment.newInstance(activity, position)
            5 -> SkillsRecyclerViewFragment.newInstance(activity, position)
            else -> BasicInfoFragment.newInstance(activity, position)
        }
    }
}