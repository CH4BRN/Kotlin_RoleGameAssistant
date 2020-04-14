// File CustomActivity.kt
// @Author pierre.antoine - 16/03/2020 - No copyright.

package com.uldskull.rolegameassistant.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 *   Class "CustomActivity" :
 *   Abstract base class for activity.
 *   onCreate logs "Start"
 **/
abstract class CustomActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "CustomActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
    }
}