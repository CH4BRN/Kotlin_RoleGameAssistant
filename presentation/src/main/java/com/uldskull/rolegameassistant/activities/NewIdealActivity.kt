// File NewIdealActivity.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.activities

import android.os.Bundle
import android.util.Log
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.NavigationBarFragment

/**
 *   Class "NewIdealActivity" :
 *   Activity for a new ideal creation
 **/
class NewIdealActivity : CustomActivity() {

    /**
     * Activity life-cycle, called when the activity is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(NEW_IDEAL_ACTIVITY.name, "Start")
        setContentView(R.layout.activity_new_ideal)
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