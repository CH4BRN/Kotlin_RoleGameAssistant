// File CharacterPagerAdapter.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.adapter

import android.app.Activity
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
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
    FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = 6

    override fun startUpdate(container: ViewGroup) {

        Toast.makeText(activity, "update", Toast.LENGTH_SHORT).show()
        super.startUpdate(container)
    }

    override fun getItem(position: Int): Fragment {
        Toast.makeText(activity, position.toString(), Toast.LENGTH_SHORT).show()

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