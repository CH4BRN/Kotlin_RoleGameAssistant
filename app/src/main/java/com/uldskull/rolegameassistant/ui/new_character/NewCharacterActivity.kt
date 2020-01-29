// File NewCharacterActivity.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.ui.new_character.fragments.*
import com.uldskull.rolegameassistant.ui.new_character.fragments.abilities.AbilitiesRecyclerViewFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "NewCharacterActivity" :
 *   Used to handle new character creation
 **/
class NewCharacterActivity : AppCompatActivity() {

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

        loadFragment()
    }

    /** Call the methods that load the fragments    **/
    private fun loadFragment() {
        loadBasicInfoFragment()
        loadCharacteristicsFragment()
        loadProgressBarFragment(10)
        loadPictureFragment()
        loadBackgroundFragment()
        loadAbilitiesFragment()
        loadHealthFragment()
    }

    /** Loads the picture fragment  **/
    private fun loadPictureFragment() {
        val pictureTransaction = fragmentManager.beginTransaction()

        pictureTransaction.replace(
            R.id.container_picture,
            PictureFragment.newInstance(this)
        )
            .commit()
    }

    /** Load the characteristics fragment   **/
    private fun loadCharacteristicsFragment() {
        val characteristicsTransaction = fragmentManager.beginTransaction()

        characteristicsTransaction.replace(
            R.id.container_characteristics,
            CharacteristicsFragment.newInstance(this)
        )
            .commit()
    }

    /** Load the BasicInfo fragment **/
    private fun loadBasicInfoFragment() {
        var basicInfoTransaction = fragmentManager.beginTransaction()
        basicInfoTransaction.replace(
            R.id.container_basicInfo,
            BasicInfoFragment.newInstance(this)
        )
            .commit()
    }


    /** Load the progress bar fragment  **/
    private fun loadProgressBarFragment(progression: Int) {
        var progressBarTransaction = fragmentManager.beginTransaction()
        progressBarTransaction.replace(
            R.id.container_progressBar,
            ProgressBarFragment.newInstance(this, progression)
        )
            .commit()
    }

    /** Load background fragment    **/
    private fun loadBackgroundFragment() {
        var backgroundTransaction = fragmentManager.beginTransaction()
        backgroundTransaction.replace(
            R.id.container_background,
            BackgroundFragment.newInstance(this)
        )
            .commit()
    }

    /** Load abilities fragment **/
    private fun loadAbilitiesFragment() {
        var abilitiesTransaction = fragmentManager.beginTransaction()
        abilitiesTransaction.replace(
            R.id.container_abilityScores,
            AbilitiesRecyclerViewFragment.newInstance(this)
        )
            .commit()
    }

    /** Load health fragment into container **/
    private fun loadHealthFragment() {
        var healthTransaction = fragmentManager.beginTransaction()
        healthTransaction.replace(
            R.id.container_health,
            HealthFragment.newInstance(this)

        )
            .commit()
    }


}