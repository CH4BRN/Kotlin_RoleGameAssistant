// File NewJobActivity.kt
// @Author pierre.antoine - 16/03/2020 - No copyright.

package com.uldskull.rolegameassistant.activities

import android.os.Bundle
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.NavigationBarFragment

/**
 *   Class "NewJobActivity" :
 *   Handle job creation.
 **/
class NewJobActivity : CustomActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_job)
        this.loadNavigationBarFragment()
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