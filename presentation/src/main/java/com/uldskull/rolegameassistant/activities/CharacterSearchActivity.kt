// File CharacterSearchActivity.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.CharacterListFragment
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
        initializeSearchView()
    }


    private fun initializeFab() {
        if ( fab_createCharacter != null) {
            fab_createCharacter.setOnClickListener {
                val intent = Intent(this, NewCharacterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initializeSearchView(){
        changeFragment(R.id.container_searchCharacter,CharacterListFragment.newInstance(this), "search")

    }
    /**
     * Change the visible fragment
     */
    private fun changeFragment(container: Int, fragment: Fragment, tag: String) {
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(container, fragment, tag)
        fragmentTransaction.addToBackStack(tag)
        fragmentTransaction.commit()
    }

}