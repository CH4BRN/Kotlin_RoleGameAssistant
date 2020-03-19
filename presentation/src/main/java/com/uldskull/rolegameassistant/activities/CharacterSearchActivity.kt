// File CharacterSearchActivity.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.activities

import android.content.Intent
import android.os.Bundle
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.CharacterSearchFragment
import kotlinx.android.synthetic.main.activity_search_character.*

/**
 *   Class "CharacterSearchActivity" :
 *   Handle character searches
 **/
class CharacterSearchActivity : CustomActivity() {

    /** Activity life-cycle  **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_character)

        initializeAddCharacterFab()
        initializeCharacterSearchView()
    }

    /**
     * Initialize the Character search view.
     */
    private fun initializeCharacterSearchView() {
        replaceFragment(
            R.id.container_searchCharacter,
            CharacterSearchFragment.newInstance(this)
        )
    }


    /**
     * Initializes the floating action button.
     */
    private fun initializeAddCharacterFab() {
        if (fab_createCharacter != null) {
            fab_createCharacter.setOnClickListener {
                val intent = Intent(this, NewCharacterActivity::class.java)
                startActivity(intent)
            }
        }

    }
}