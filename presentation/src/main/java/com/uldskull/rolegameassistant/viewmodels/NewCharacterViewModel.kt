// File NewCharacterViewModel.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.DomainRace
import com.uldskull.rolegameassistant.repository.character.CharacterRepository
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
    private val diceService: DiceService,
    private val characterRepository: CharacterRepository<LiveData<DomainCharacter>>
) : AndroidViewModel(application) {


    /**
     * Character height
     */
    private var characterHeight: Int? = 0
        set(value) {
            Log.d("NewCharacterViewModel characterHeight", value.toString())
            field = value
        }
    /**
     * Character name.
     */
    var characterName: String? = ""
        set(value) {
            Log.d("NewCharacterViewModel CharacterName", value)
            field = value
        }
    /**
     * Character age.
     */
    private var characterAge: Int? = 0
        set(value) {
            Log.d("NewCharacterViewModel characterAge", value.toString())
            field = value
        }
    /**
     * Character gender.
     */
    var characterGender: String? = ""
        set(value) {
            Log.d("NewCharacterViewModel characterGender", value)
            field = value
        }
    /**
     * Character biography.
     */
    var characterBiography: String? = ""
        set(value) {
            Log.d("NewCharacterViewModel characterBiography", value.toString())
            field = value
        }
    /**
     * Character race.
     */
    var characterRace: DomainRace? = DomainRace(
        raceId = null,
        raceName = "Warrior"
        /*,
        raceCharacteristics = listOf(
            DomainBonusCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.STRENGTH.characteristicName,
                characteristicBonus = 2,
                characteristicMax = 8
            )
        )*/
        ,

        raceDescription = "Strength bonus"
    )
        set(value) {
            Log.d("NewCharacterViewModel characterRace", value?.raceName.toString())
            field = value
        }

    fun displayDices() {
        viewModelScope.launch {

            Log.d("DICES", diceService.getOneDiceRollWithANumberOfFace(6).toString())

            val list = arrayListOf(6, 12, 20)
            diceService.getMultipleDiceRollWithANumberOfFace(list).forEach(System.out::println)
        }
    }

    /**
     * Save age into view model
     */
    fun saveAge(characterAge: String) {
        Log.d("NewCharacterViewModel", "age = $characterAge")
        if (characterAge != null && characterAge.isNotEmpty()) {
            try {
                this.characterAge = characterAge.toInt()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun saveCharacter() {
        Log.d("NewCharacterViewModel _ save", characterName)
        Log.d("NewCharacterViewModel _ save", characterAge.toString())
        Log.d("NewCharacterViewModel _ save", characterGender)
        Log.d("NewCharacterViewModel _ save", characterBiography)
        Log.d("NewCharacterViewModel _ save", characterHeight.toString())
        Log.d("NewCharacterViewModel _ save", characterRace?.raceName)

        var character = DomainCharacter(
            characterId = null,
            characterName = characterName,
            characterAge = characterAge,
            characterGender = characterGender,
            characterBiography = characterBiography,
            characterHeight = characterHeight,
            characterRace = characterRace,
            characterIdeaPoints = null,
            //characterSkills = null,
            //characterJob = null,
            //characterIdeals = null,
            //characterHobby = null,
            characterHealthPoints = null,
            characterEnergyPoints = null,
            characterAlignment = null
            //characterCharacteristics = null,
            //characterBonds = null
        )

        val result = characterRepository.insertOne(character)

        Log.d("RESULT", result?.toString())

    }

    fun saveHeight(characterHeight: String) {
        Log.d("NewCharacterViewModel", "height = $characterHeight")
        if (characterHeight != null && characterHeight.isNotEmpty()) {
            try {
                this.characterHeight = characterHeight.toInt()
            } catch (e: Exception) {
                throw e
            }
        }
    }


}