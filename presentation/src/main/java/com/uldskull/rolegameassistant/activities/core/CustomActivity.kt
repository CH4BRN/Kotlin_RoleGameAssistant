// File CustomActivity.kt
// @Author pierre.antoine - 16/03/2020 - No copyright.

package com.uldskull.rolegameassistant.activities.core

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
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
        Log.d("DEBUg$TAG","onCreate")
        super.onCreate(savedInstanceState)
        initializeViewModels()
        startObservation()
    }



    /**
     * Initialize the activity ViewModels
     */
    abstract fun initializeViewModels()

    /**
     * Deserialize widgets
     */
    abstract fun deserializeWidgets()

    /**
     * Initialize the widgets
     */
    abstract fun initializeWidgets()

    /**
     * Start livedata observation
     */
    abstract fun startObservation()
}