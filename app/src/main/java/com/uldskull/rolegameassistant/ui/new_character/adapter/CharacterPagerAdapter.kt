// File CharacterPagerAdapter.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.adapter

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.uldskull.rolegameassistant.ui.new_character.fragments.BackgroundFragment
import com.uldskull.rolegameassistant.ui.new_character.fragments.BasicInfoFragment
import com.uldskull.rolegameassistant.ui.new_character.fragments.CharacteristicsFragment
import com.uldskull.rolegameassistant.ui.new_character.fragments.HealthFragment
import com.uldskull.rolegameassistant.ui.new_character.fragments.abilities.AbilitiesRecyclerViewFragment
import com.uldskull.rolegameassistant.ui.new_character.fragments.skills.SkillsRecyclerViewFragment

/**
 *   Class "CharacterPagerAdapter" :
 *   TODO: Fill class use.
 **/
class CharacterPagerAdapter(fm: FragmentManager, val activity: Activity) :
    FragmentPagerAdapter(fm) {
    override fun getCount(): Int = 6

    override fun getItem(position: Int): Fragment {
        Toast.makeText(activity, position.toString(), Toast.LENGTH_SHORT).show()

        var fragment: Fragment? = null
        return when (position) {
            0 -> BasicInfoFragment.newInstance(activity)
            1 -> CharacteristicsFragment.newInstance(activity)
            2 -> BackgroundFragment.newInstance(activity)
            3 -> AbilitiesRecyclerViewFragment.newInstance(activity)
            4 -> HealthFragment.newInstance(activity)
            5 -> SkillsRecyclerViewFragment.newInstance(activity)
            else -> BasicInfoFragment.newInstance(activity)
        }
    }
}