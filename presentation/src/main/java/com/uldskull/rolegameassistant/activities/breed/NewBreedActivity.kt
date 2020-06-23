// File NewBreedActivity.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.activities.breed

import android.os.Bundle
import android.util.Log
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.CustomActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.fragment.bars.NavigationBarFragment

/**
 *   Class "NewBreedActivity" :
 *   Activity that manages the new breed creation.
 **/
class NewBreedActivity : CustomActivity() {
    companion object {
        private const val TAG = "NewBreedActivity"
    }


    /**
     * Activity lifecycle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breed_edit)
        loadNavigationBarFragment()
    }


    /**
     * Load the navigation bar fragment.
     */
    private fun loadNavigationBarFragment() {
        Log.d(TAG, "loadNavigationBarFragment")

        this.replaceFragment(
            R.id.activityEditBreed_layout_navigationBar,
            NavigationBarFragment.newInstance(this)
        )
    }
}