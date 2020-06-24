// File FragmentAdapter.kt
// @Author pierre.antoine - 10/05/2020 - No copyright.

package com.uldskull.rolegameassistant.activities.character

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.uldskull.rolegameassistant.fragments.fragment.basicinfo.BasicInfoFragment
import com.uldskull.rolegameassistant.fragments.fragment.bonds.BondsFragment
import com.uldskull.rolegameassistant.fragments.fragment.characteristics.CharacteristicsFragment
import com.uldskull.rolegameassistant.fragments.fragment.ideals.ideal_toCheck.IdealsFragment

/**
 *   Class "FragmentAdapter" :
 *   TODO: Fill class use.
 **/
class FragmentAdapter(val activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    companion object {
        private const val TAG = "FragmentAdapter"
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        Log.d(TAG, "getCount")
        return fragmentList.size
    }

    /**
     * Fragments to display list
     */
    var fragmentList = mutableListOf<Fragment>(
        BasicInfoFragment.newInstance(
            activity
        ),
        BondsFragment.newInstance(
            activity
        ),
        IdealsFragment.newInstance(
            activity
        ),
        CharacteristicsFragment.newInstance(
            activity
        )
    )

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
        return fragmentList[position]
    }
}