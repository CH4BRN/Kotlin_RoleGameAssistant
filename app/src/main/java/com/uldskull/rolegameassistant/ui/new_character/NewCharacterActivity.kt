// File NewCharacterActivity.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.ui.new_character.fragments.BasicInfoFragment
import com.uldskull.rolegameassistant.ui.new_character.fragments.CharacteristicsFragment
import com.uldskull.rolegameassistant.ui.new_character.fragments.PictureFragment
import com.uldskull.rolegameassistant.ui.new_character.fragments.ProgressBarFragment
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
        loadProgresBarFragment(10)
        loadPictureFragment()
    }

    private fun loadPictureFragment(){
        val pictureTransaction = fragmentManager.beginTransaction()

        pictureTransaction.replace(
            R.id.container_picture,
            PictureFragment?.newInstance(this)
        )
            .commit()
    }

    /** Load the characteristics fragment   **/
    private fun loadCharacteristicsFragment() {
        val characteristicsTransaction = fragmentManager.beginTransaction()

        characteristicsTransaction.replace(
            R.id.container_characteristics,
            CharacteristicsFragment?.newInstance(this)
        )
            .commit()


    }

    /** Load the BasicInfo fragment **/
    private fun loadBasicInfoFragment() {
        var basicInfoTransaction = fragmentManager.beginTransaction()
        basicInfoTransaction.replace(
            R.id.container_basicInfo,
            BasicInfoFragment?.newInstance(this)
        )
            .commit()
    }


    /** Load the progress bar fragment  **/
    private fun loadProgresBarFragment(progression: Int) {
        var progressBarTransaction = fragmentManager.beginTransaction()
        progressBarTransaction.replace(
            R.id.container_progressBar,
            ProgressBarFragment?.newInstance(this, progression)
        )
            .commit()


    }


}