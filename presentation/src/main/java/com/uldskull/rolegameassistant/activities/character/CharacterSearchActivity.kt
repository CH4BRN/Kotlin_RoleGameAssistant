// File CharacterSearchActivity.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.activities.character

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.core.CharacterTransmission
import com.uldskull.rolegameassistant.activities.core.CustomActivity
import com.uldskull.rolegameassistant.activities.core.replaceFragment
import com.uldskull.rolegameassistant.fragments.fragment.characterSearch.CharacterSearchFragment
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import kotlinx.android.synthetic.main.activity_character_search.*

/**
 *   Class "CharacterSearchActivity" :
 *   Handle character searches
 **/
class CharacterSearchActivity : CustomActivity(),
    CharacterTransmission {
    companion object {
        private const val TAG = "CharacterSearchActivity"
    }
    /** Floating action button*/
    private var fabNewCharacter:FloatingActionButton? = null

    /** Activity life-cycle  **/
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_search)
        deserializeWidgets()
        initializeWidgets()
    }

    /**
     * Initialize the activity ViewModels
     */
    override fun initializeViewModels() {
        //  Do nothing
    }

    /**
     * Deserialize widgets
     */
    override fun deserializeWidgets() {
        fabNewCharacter = this.findViewById(R.id.fab_createCharacter)
        if(fabNewCharacter == null){
            throw Exception("Fab is null.")
        }
    }


    /**
     * Initialize the widgets
     */
    override fun initializeWidgets() {
        initializeAddCharacterFab()
        initializeCharacterSearchView()
    }

    /**
     * Start livedata observation
     */
    override fun startObservation() {
        //  Do nothing
    }

    /**
     * Initialize the Character search view.
     */
    private fun initializeCharacterSearchView() {
        Log.d(TAG, "initializeCharacterSearchView")
        replaceFragment(
            R.id.container_searchCharacter,
            CharacterSearchFragment.newInstance(this)
        )
    }


    /**
     * Initializes the floating action button.
     */
    private fun initializeAddCharacterFab() {
        Log.d(TAG, "initializeAddCharacterFab")
        if (fabNewCharacter != null) {
            fabNewCharacter!!.setOnClickListener {
                val intent = Intent(this, CharacterActivity::class.java)
                startActivity(intent)
            }
        }

    }

    /**
     * Transmit character into GSon format
     */
    override fun transmitCharacter(domainCharacter: DomainCharacter?) {
        Log.d("DEBUG $TAG", "$domainCharacter")
        val intent = Intent(this, CharacterActivity::class.java)
        intent.putExtra("selectedCharacter", Gson().toJson(domainCharacter))
        this.startActivity(intent)
    }
}