package com.uldskull.rolegameassistant.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.uldskull.rolegameassistant.R

/**
 * App's main activity
 */
class MainActivity : AppCompatActivity() {


    /** Activity Lifecycle
     * @param  savedInstanceState is the transmitted bundle**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate")
        setContentView(R.layout.activity_main)
        application.deleteDatabase("appdb")

        loadSearchCharacterActivity()

    }

    /** Load the search character activity  **/
    private fun loadSearchCharacterActivity() {
        Log.d(TAG, "loadSearchCharacterActivity")
        startActivity(
            getCharacterSearchIntent()
        )
    }

    /** get character search intent **/
    private fun getCharacterSearchIntent(): Intent {
        Log.d(TAG, "getCaracterSearhIntent")
        return Intent(
            this,
            CHARACTER_SEARCH_ACTIVITY
        )
    }

    /** Load the new character activity **/
    private fun loadNewCharacterActivity() {
        Log.d(TAG, "loadNewCharacterActivity")
        startActivity(
            getNewCharacterIntent()
        )
    }

    /** Get new character intent    **/
    private fun getNewCharacterIntent(): Intent {
        Log.d(TAG, "getNewCharacterIntent")
        return Intent(
            this,
            CHARACTER_ACTIVITY
        )
    }


    companion object {
        private const val TAG = "MainActivity"
    }
}