// File NewBreedActivity.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.activities

import android.os.Bundle
import android.util.Log
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.bars.NavigationBarFragment

/**
 *   Class "NewBreedActivity" :
 *   Activity that manages the new breed creation.
 **/
class NewBreedActivity : CustomActivity() {
    companion object {
        private const val TAG = "NewBreedActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_breed)
        loadNavigationBarFragment()
    }


    /**
     * Load the navigation bar fragment.
     */
    private fun loadNavigationBarFragment() {
        Log.d(TAG, "loadNavigationBarFragment")

        this.replaceFragment(
            R.id.container_navigationBar,
            NavigationBarFragment.newInstance(this)
        )
    }
}