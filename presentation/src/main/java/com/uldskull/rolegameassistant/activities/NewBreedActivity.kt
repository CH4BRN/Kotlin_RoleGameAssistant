// File NewBreedActivity.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.activities

import android.os.Bundle
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.NavigationBarFragment

/**
 *   Class "NewBreedActivity" :
 *   Activity that manages the new breed creation.
 **/
class NewBreedActivity : CustomActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_breed)
        loadNavigationBarFragment()
    }


    /**
     * Load the navigation bar fragment.
     */
    private fun loadNavigationBarFragment() {

        this.replaceFragment(
            R.id.container_navigationBar,
            NavigationBarFragment.newInstance(this)
        )
    }
}