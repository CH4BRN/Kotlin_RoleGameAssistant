// File NewCharacterActivity.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.ui.new_character.adapter.CharacterPagerAdapter
import com.uldskull.rolegameassistant.ui.new_character.fragments.ProgressBarFragment
import com.uldskull.rolegameassistant.ui.new_character.view_model.NewCharacterViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "NewCharacterActivity" :
 *   Used to handle new character creation
 **/
class NewCharacterActivity :
    AppCompatActivity() {

    /** ViewModel for new character activity    **/
    private lateinit var newCharacterViewModel: NewCharacterViewModel

    /** SupportFragmentManager  **/
    private val fragmentManager = supportFragmentManager

    /** Activity life cycle **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_character)

        //  Get the ViewModels by DI
        newCharacterViewModel = getViewModel()


        progression.observe(
            this, Observer { prog ->
                kotlin.run {
                    loadProgressBarFragment(prog)
                }
            })

        viewPager = findViewById(R.id.viewPager)

        characterPagerAdapter = CharacterPagerAdapter(supportFragmentManager, this)

        viewPager.adapter = characterPagerAdapter


        loadFragment()
    }

    /** View pager  **/
    private lateinit var viewPager: ViewPager

    private lateinit var characterPagerAdapter: CharacterPagerAdapter

    /** Call the methods that load the fragments    **/
    private fun loadFragment() {
        loadProgressBarFragment(0)

    }

    /** Load the progress bar fragment  **/
    private fun loadProgressBarFragment(progression: Int) {
        val progressBarTransaction = fragmentManager.beginTransaction()
        progressBarTransaction.replace(
            R.id.container_progressBar,
            ProgressBarFragment.newInstance(this, progression)
        )
            .commit()
    }


    companion object {
        var progression = MutableLiveData<Int>()

    }
}