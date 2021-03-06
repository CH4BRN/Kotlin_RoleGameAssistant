// File FragmentAdapter.kt
// @Author pierre.antoine - 10/05/2020 - No copyright.

package com.uldskull.rolegameassistant.activities.newCharacter

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.uldskull.rolegameassistant.fragments.fragment.basicinfo.BasicInfoFragment
import com.uldskull.rolegameassistant.fragments.fragment.bonds.BondsFragment
import com.uldskull.rolegameassistant.fragments.fragment.characteristics.CharacteristicsFragment
import com.uldskull.rolegameassistant.fragments.fragment.derivedValues.DerivedValues1Fragment
import com.uldskull.rolegameassistant.fragments.fragment.derivedValues.DerivedValues2Fragment
import com.uldskull.rolegameassistant.fragments.fragment.hobbies.HobbiesFragment
import com.uldskull.rolegameassistant.fragments.fragment.hobby.HobbyFragment
import com.uldskull.rolegameassistant.fragments.fragment.ideals.IdealsFragment
import com.uldskull.rolegameassistant.fragments.fragment.occupation.OccupationFragment
import com.uldskull.rolegameassistant.fragments.fragment.occupations.OccupationsFragment
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.*

/**
 *   Class "FragmentAdapter" :
 *   TODO: Fill class use.
 **/
class FragmentAdapter(val activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    companion object{
        private const val TAG = "FragmentAdapter"
    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        Log.d(TAG, "getCount")
        return 10
    }

    /**
     * Provide a new Fragment associated with the specified position.
     *
     *
     * The adapter will be responsible for the Fragment lifecycle:
     *
     *  * The Fragment will be used to display an item.
     *  * The Fragment will be destroyed when it gets too far from the viewport, and its state
     * will be saved. When the item is close to the viewport again, a new Fragment will be
     * requested, and a previously saved state will be used to initialize it.
     *
     * @see ViewPager2.setOffscreenPageLimit
     */
    override fun createFragment(position: Int): Fragment {
        Log.d(TAG, "getItem")
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
            OCCUPATIONS_FRAGMENT_POSITION -> OccupationsFragment.newInstance(
                activity
            )
            OCCUPATION_FRAGMENT_POSITION -> OccupationFragment.newInstance(
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
// TODO : Fill class.
}