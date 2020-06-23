// NewSkillActivity.kt created by UldSkull - 11/03/2020

package com.uldskull.rolegameassistant.activities.skills

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.uldskull.rolegameassistant.R

/**
Class "NewSkillActivity"

Activity for skill creation and edition
 */
class EditSkillActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "NewSkillActivity"
    }

    /**
     * activty lifecycle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_edit_skill)
    }
}