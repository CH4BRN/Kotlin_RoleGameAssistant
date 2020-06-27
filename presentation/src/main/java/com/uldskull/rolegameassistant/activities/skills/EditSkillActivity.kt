// NewSkillActivity.kt created by UldSkull - 11/03/2020

package com.uldskull.rolegameassistant.activities.skills

import android.os.Bundle
import android.util.Log
import android.view.View
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.core.CustomActivity

/**
Class "NewSkillActivity"

Activity for skill creation and edition
 */
class EditSkillActivity : CustomActivity() {
    companion object {
        private const val TAG = "NewSkillActivity"
    }

    /**
     * activity lifecycle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_skill_edit)
        deserializeWidgets()
        initializeWidgets()
    }

    /**
     * Initialize the activity ViewModels
     */
    override fun initializeViewModels() {
        // Do nothing
    }

    /**
     * Deserialize widgets
     */
    override fun deserializeWidgets() {
        // Do nothing.
    }

    /**
     * Initialize the widgets
     */
    override fun initializeWidgets() {
        // Do nothing
    }

    /**
     * Start livedata observation
     */
    override fun startObservation() {
        //  Do nothing
    }
}