// NewSkillActivity.kt created by UldSkull - 11/03/2020

package com.uldskull.rolegameassistant.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.uldskull.rolegameassistant.R

/**
Class "NewSkillActivity"

Activity for a new skill creation.
 Logs "start" when it is created.
 */
class NewSkillActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "NewSkillActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_new_skill)
    }
}