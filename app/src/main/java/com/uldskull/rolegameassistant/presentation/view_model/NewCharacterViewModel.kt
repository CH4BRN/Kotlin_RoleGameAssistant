// File NewCharacterViewModel.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.presentation.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

/**
 *   Class "NewCharacterViewModel" :
 *   Designed to store and manage UI-related data in a lifecycle conscious way.
 *   The ViewModel class allows data to survive configuration changes such as
 *   screen rotations.
 **/
class NewCharacterViewModel(application: Application) : AndroidViewModel(application) {


    fun saveName(name: String) {
        //TODO("implements save name")
        Log.i("TEST", "$name")
    }

    fun saveLevel(level: String) {
        //TODO("implements save level")
        Log.i("TEST", "$level")
    }

    fun saveExperience(experience: String) {
        //TODO("implements save experience")
        Log.i("TEST", "$experience")
    }

    fun saveRace(race: String) {
        //TODO("implement save race")
        Log.i("TEST", "$race")
    }
}