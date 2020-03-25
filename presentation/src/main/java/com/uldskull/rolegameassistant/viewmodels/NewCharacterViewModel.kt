// File NewCharacterViewModel.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.useCases.diceRoll.DiceService
import kotlinx.coroutines.launch

/**
 *   Class "NewCharacterViewModel" :
 *   Designed to store and manage UI-related data in a lifecycle conscious way.
 *   The ViewModel class allows data to survive configuration changes such as
 *   screen rotations.
 **/
class NewCharacterViewModel(
    application: Application,
    private val diceService: DiceService
) : AndroidViewModel(application) {

    fun displayDices() {
        viewModelScope.launch {

            Log.d("DICES", diceService.getOneDiceRollWithANumberOfFace(6).toString())

            var list = arrayListOf(6, 12, 20)
            diceService.getMultipleDiceRollWithANumberOfFace(list).forEach(System.out::println)
        }
    }


    fun saveName(name: String) {
        Log.d("NewCharacterViewModel", "name = $name")
        this.characterName = name
    }

    fun saveLevel(level: String) {
        //TODO("implements save level")
        Log.d("NewCharacterViewModel", level)
    }

    fun saveExperience(experience: String) {
        //TODO("implements save experience")
        Log.d("TEST", experience)
    }

    fun saveRace(race: String) {
        //TODO("implement save race")
        Log.d("TEST", race)
    }

    /**
     * Save age into view model
     */
    fun saveAge(characterAge: String) {
        Log.d("NewCharacterViewModel", "age = $characterAge")
        try {
            this.characterAge = characterAge.toInt()
        } catch (e: Exception) {
            throw e
        }

    }

    /**
     * Save gender into view model
     */
    fun saveGender(characterGender: String) {
        Log.d("NewCharacterViewModel", "gender = $characterGender")
        try {
            this.characterGender = characterGender
        } catch (e: Exception) {
            throw e
        }
    }

    private var characterGender: String = "gender"
    private var characterName: String = "name"
    private var characterAge: Int = 42


}