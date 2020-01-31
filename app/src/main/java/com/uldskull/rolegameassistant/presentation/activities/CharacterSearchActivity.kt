// File CharacterSearchActivity.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.presentation.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uldskull.rolegameassistant.R
import kotlinx.android.synthetic.main.activity_search_character.*

/**
 *   Class "CharacterSearchActivity" :
 *   Handle character searches
 **/
class CharacterSearchActivity : AppCompatActivity() {

    /** Activity life-cycle  **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_character)
        initializeFab()
    }


    fun initializeFab() {
        if (fab_createCharacter != null) {
            fab_createCharacter.setOnClickListener {
                var intent = Intent(this, NewCharacterActivity::class.java)
                startActivity(intent)
            }
        }

    }
}