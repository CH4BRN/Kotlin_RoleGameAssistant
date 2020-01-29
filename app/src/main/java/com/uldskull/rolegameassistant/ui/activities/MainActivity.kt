package com.uldskull.rolegameassistant.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.ui.new_character.activities.NewCharacterActivity

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
        NewCharacterActivity::class.java

    /** Load the search character activity  **/
    private fun loadSearchCharacterActivity() {
        startActivity(
            Intent(
                this,
                CHARACTER_SEARCH_ACTIVITY
            )
        )
    }

    /** Load the new character activity **/
    private fun loadNewCharacterActivity() {
        startActivity(
            Intent(
                this,
                NEW_CHARACTER_ACTIVITY
            )
        )
    }
}
