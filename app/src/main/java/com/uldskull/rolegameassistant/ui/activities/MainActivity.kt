package com.uldskull.rolegameassistant.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uldskull.rolegameassistant.R

class MainActivity : AppCompatActivity() {

    /** Activity Lifecycle  **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  loadSearchCharacterActivity()
        loadNewCharacterActivity()
    }

    /** Character search activity java class    **/
    private val CHARACTER_SEARCH_ACTIVITY =
        com.uldskull.rolegameassistant.ui.activities.CharacterSearchActivity::class.java
    /** New character activity java class   **/
    private val NEW_CHARACTER_ACTIVITY =
        com.uldskull.rolegameassistant.ui.new_character.NewCharacterActivity::class.java

    /** Load the search character activity  **/
    private fun loadSearchCharacterActivity() {
        var intent = Intent(
            this,
            CHARACTER_SEARCH_ACTIVITY
        )
        startActivity(intent)
    }

    /** Load the new character activity **/
    private fun loadNewCharacterActivity() {

        var intent = Intent(
            this,
            NEW_CHARACTER_ACTIVITY
        )
        startActivity(intent)
    }
}
