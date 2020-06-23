// File CharacterPagerAdapter.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.viewPager.adapter

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
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
import com.uldskull.rolegameassistant.fragments.fragment.ideals.ideal_toCheck.IdealsFragment
import com.uldskull.rolegameassistant.fragments.fragment.occupation.OccupationFragment
import com.uldskull.rolegameassistant.fragments.fragment.occupations.OccupationsFragment

/**
 *   Class "CharacterPagerAdapter" :
 *   Adapter for ViewPager
 **/
class CharacterPagerAdapter(fm: FragmentManager, val activity: Activity) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        private const val TAG = "CharacterPageAdapter"
    }
    /** Number of view  **/
    override fun getCount(): Int {
        Log.d(TAG, "getCount")
        return 10
    }

    /**
     * Called when the host view is attempting to determine if an item's position
     * has changed. Returns [.POSITION_UNCHANGED] if the position of the given
     * item has not changed or [.POSITION_NONE] if the item is no longer present
     * in the adapter.
     *
     *
     * The default implementation assumes that items will never
     * change position and always returns [.POSITION_UNCHANGED].
     *
     * @param object Object representing an item, previously returned by a call to
     * [.instantiateItem].
     * @return object's new position index from [0, [.getCount]),
     * [.POSITION_UNCHANGED] if the object's position has not changed,
     * or [.POSITION_NONE] if the item is no longer present.
     */
    override fun getItemPosition(`object`: Any): Int {
        Log.d(TAG, "getItemPosition")
        return super.getItemPosition(`object`)
    }


    /**
     * Instantiate item
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        Log.d(TAG, "instantiateItem")
        return super.instantiateItem(container, position)
    }

    /**
     * Remove a page for the given position.  The adapter is responsible
     * for removing the view from its container, although it only must ensure
     * this is done by the time it returns from [.finishUpdate].
     *
     * @param container The containing View from which the page will be removed.
     * @param position The page position to be removed.
     * @param object The same object that was returned by
     * [.instantiateItem].
     *
     */
    override fun destroyItem(container: View, position: Int, `object`: Any) {
        Log.d(TAG, "destroyItem")
        super.destroyItem(container, position, `object`)
    }

    /** Get the corresponding view   **/
    override fun getItem(position: Int): Fragment {
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
            CHARACTERISTICS_FRAGMENT_POSITION -> CharacteristicsFragment.newInstance(
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
}