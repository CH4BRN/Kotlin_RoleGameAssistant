// File NewCharacterActivity.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.ui.new_character.fragments.*
import com.uldskull.rolegameassistant.ui.new_character.fragments.abilities.AbilitiesRecyclerViewFragment
import com.uldskull.rolegameassistant.ui.new_character.fragments.skills.SkillsRecyclerViewFragment
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
        loadSkillsFragment()
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
        val basicInfoTransaction = fragmentManager.beginTransaction()
        basicInfoTransaction.replace(
            R.id.container_basicInfo,
            BasicInfoFragment.newInstance(this)
        )
            .commit()
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

    /** Load background fragment into background container**/
    private fun loadBackgroundFragment() {
        val backgroundTransaction = fragmentManager.beginTransaction()
        backgroundTransaction.replace(
            R.id.container_background,
            BackgroundFragment.newInstance(this)
        )
            .commit()
    }

    /** Load abilities fragment into abilities container**/
    private fun loadAbilitiesFragment() {
        val abilitiesTransaction = fragmentManager.beginTransaction()
        abilitiesTransaction.replace(
            R.id.container_abilityScores,
            AbilitiesRecyclerViewFragment.newInstance(this)
        )
            .commit()
    }

    /** Load health fragment into health container **/
    private fun loadHealthFragment() {
        val healthTransaction = fragmentManager.beginTransaction()
        healthTransaction.replace(
            R.id.container_health,
            HealthFragment.newInstance(this)

        )
            .commit()
    }

    /** Load skills fragment into skills container  **/
    private fun loadSkillsFragment() {
        val skillsTransaction = fragmentManager.beginTransaction()
        skillsTransaction.replace(
            R.id.container_skills,
            SkillsRecyclerViewFragment.newInstance(this)
        )
            .commit()
    }


}