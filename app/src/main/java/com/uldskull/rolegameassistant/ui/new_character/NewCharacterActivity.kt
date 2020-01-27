// File NewCharacterActivity.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uldskull.rolegameassistant.R
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "NewCharacterActivity" :
 *   Used to handle new character creation
 **/
class NewCharacterActivity : AppCompatActivity() {

    /** ViewModel for new character activity    **/
    private lateinit var newCharacterViewModel:NewCharacterViewModel

    /** Activity life cycle **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_character)

        //  Get the ViewModel by DI
        newCharacterViewModel = getViewModel()
    }

}