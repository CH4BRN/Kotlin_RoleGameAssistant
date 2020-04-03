// File NewCharacterViewModel.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.DomainBond
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedCharacteristic
import com.uldskull.rolegameassistant.repository.breed.BreedsRepository
import com.uldskull.rolegameassistant.repository.character.CharacterRepository
import com.uldskull.rolegameassistant.repository.characteristic.BreedCharacteristicRepository
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
    private val characterRepository: CharacterRepository<LiveData<List<DomainCharacter>>>,
    private val breedsRepository: BreedsRepository<LiveData<List<DomainBreed>>>,
    private val characteristicRepository: BreedCharacteristicRepository<LiveData<List<DomainBreedCharacteristic>>>
) : AndroidViewModel(application) {

    /**
     * Character's bonds
     */
    var characterBonds: MutableList<DomainBond>? = mutableListOf()
    /**
     * Charactere picture.
     */
    var characterPictureUri: Uri? = null
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
     * Character breed.
     */
    var characterBreed: DomainBreed? = null
        set(value) {
            Log.d("NewCharacterViewModel characterBreed", value?.breedName.toString())
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

    fun tryToInsertAndGetACharacteristic(domainBreedCharacteristic: DomainBreedCharacteristic): DomainBreedCharacteristic? {
        var characteristicInsertResult: Long? = -1
        try {
            characteristicInsertResult =
                characteristicRepository.insertOne(domainBreedCharacteristic)

        } catch (e: Exception) {
            throw e
        }
        var characteristicSearchResult: DomainBreedCharacteristic?
        try {
            characteristicSearchResult =
                characteristicRepository.findOneById(characteristicInsertResult)
            if (characteristicSearchResult != null) {
                Log.d("test", "INSERTED")
            }
        } catch (e: Exception) {
            throw e
        }
        return characteristicSearchResult
    }


    fun displayBreed() {
        var breedInsertResult: Long? = tryToInsertAndGetABreed()

        var characteristic = DomainBreedCharacteristic(
            characteristicId = null,
            characteristicBonus = 1,
            characteristicName = "DomainBreedCharacteristic",
            characteristicBreedId = breedInsertResult
        )
        var characteristicInsertResult: DomainBreedCharacteristic? =
            tryToInsertAndGetACharacteristic(characteristic)
        Log.d("test", "FIRST CHARACTERISTIC : $characteristicInsertResult")

        var secondCharacteristic = DomainBreedCharacteristic(
            characteristicId = null,
            characteristicBonus = 2,
            characteristicName = "Second characteristic",
            characteristicBreedId = breedInsertResult
        )

        var secondCharacteristicInsertResult: DomainBreedCharacteristic? =
            tryToInsertAndGetACharacteristic(secondCharacteristic)
        Log.d("test", "SECOND CHARACTERISTIC : $secondCharacteristicInsertResult")

        breedsRepository.findOneWithChildren(breedInsertResult)

    }


    private fun tryToInsertAndGetABreed(): Long? {
        // INSERT A BREED
        Log.d("test", "INSERT A BREED")
        var breedInsertResult: Long? = -1
        try {
            //  Creates a breed
            var breed =
                DomainBreed(
                    breedId = null,
                    breedName = "test breed",
                    breedDescription = "test description"
                )
            Log.d("test", "Created breed : $breed")

            //  Inserts it
            breedInsertResult = breedsRepository.insertOne(
                breed
            )
            Log.d("test", "breedInsertResult : $breedInsertResult")

            //  Tries to get it.
            Log.d("test", "Tries to get breed corresponding to $breedInsertResult")
            var breedSearchResult = breedsRepository.findOneById(
                id = breedInsertResult
            )
            Log.d("test", "breedSearchResult  : $breedSearchResult")

        } catch (e: Exception) {
            throw e
        }
        return breedInsertResult
    }

    fun saveCharacter() {
        if (!characterName.isNullOrEmpty()) {
            Log.d("NewCharacterViewModel _ savecharacterName", characterName)
        }
        if (!characterAge?.toString().isNullOrEmpty()) {
            Log.d("NewCharacterViewModel _ savecharacterAge", characterAge?.toString())
        }
        if (!characterGender.isNullOrEmpty()) {
            Log.d("NewCharacterViewModel _ savecharacterGender", characterGender)
        }
        if (!characterBiography.isNullOrEmpty()) {
            Log.d("NewCharacterViewModel _ savecharacterBiography", characterBiography)
        }
        if (!characterHeight?.toString().isNullOrEmpty()) {
            Log.d("NewCharacterViewModel _ savecharacterHeight", characterHeight?.toString())
        }
        if (characterBreed != null) {
            var breed = breedsRepository.findOneWithChildren(characterBreed?.breedId)
            Log.d("NewCharacterViewModel _ savecharacterBreed", breed?.breed?.breedName)

            breed?.characteristics?.forEach {
                Log.d(
                    "NewCharacterViewModel",
                    "characteristic : ${it.characteristicName} bonus ${it.characteristicBonus}"
                )
            }
        }
        if (!characterPictureUri?.toString().isNullOrEmpty()) {
            Log.d(
                "NewCharacterViewModel _ savecharacterPictureUri",
                characterPictureUri?.toString()
            )
        }

        if (!characterBonds.isNullOrEmpty()) {
            characterBonds?.forEach {
                Log.d(
                    "NewCharacterViewModel _ savecharacterBonds",
                    it.bondTitle
                )
            }
        }


        var character = DomainCharacter(
            characterId = null,
            characterName = this.characterName,
            characterAge = this.characterAge,
            characterGender = this.characterGender,
            characterBiography = this.characterBiography,
            characterHeight = this.characterHeight,
            characterBreed = this.characterBreed,
            characterIdeaPoints = null,
            characterPictureUri = this.characterPictureUri.toString(),
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