// File CustomActivity.kt
// @Author pierre.antoine - 16/03/2020 - No copyright.

package com.uldskull.rolegameassistant.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 *   Class "CustomActivity" :
 *   TODO: Fill class use.
 **/
abstract class CustomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(this.localClassName, "Start")
    }
}