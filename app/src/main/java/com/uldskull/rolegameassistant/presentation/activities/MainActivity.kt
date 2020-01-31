package com.uldskull.rolegameassistant.presentation.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uldskull.rolegameassistant.R


class MainActivity : AppCompatActivity() {


    /** Activity Lifecycle
     * @param  savedInstanceState is the transmitted bundle**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadSearchCharacterActivity()
        //loadNewCharacterActivity()
    }

    /** Load the search character activity  **/
    private fun loadSearchCharacterActivity() {
        startActivity(
            getCharacterSearchIntent()
        )
    }

    /** get character search intent **/
    private fun getCharacterSearchIntent(): Intent {
        return Intent(
            this,
            CHARACTER_SEARCH_ACTIVITY
        )
    }

    /** Load the new character activity **/
    private fun loadNewCharacterActivity() {
        startActivity(
            getNewCharacterIntent()
        )
    }

    /** Get new character intent    **/
    private fun getNewCharacterIntent(): Intent {
        return Intent(
            this,
            NEW_CHARACTER_ACTIVITY
        )
    }

    companion object {
        /** Character search activity java class    **/
        private val CHARACTER_SEARCH_ACTIVITY: Class<CharacterSearchActivity> =
            CharacterSearchActivity::class.java
        /** New character activity java class   **/
        private val NEW_CHARACTER_ACTIVITY: Class<NewCharacterActivity> =
            NewCharacterActivity::class.java
    }
}