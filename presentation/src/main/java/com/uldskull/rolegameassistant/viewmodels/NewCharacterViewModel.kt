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
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.models.character.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollCharacteristic
import com.uldskull.rolegameassistant.repository.breed.BreedsRepository
import com.uldskull.rolegameassistant.repository.character.CharacterRepository
import com.uldskull.rolegameassistant.repository.characteristic.BreedCharacteristicRepository
import com.uldskull.rolegameassistant.repository.ideal.IdealsRepository
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
    private val characteristicRepository: BreedCharacteristicRepository<LiveData<List<DomainBreedCharacteristic>>>,
    private val idealsRepository: IdealsRepository<LiveData<List<DomainIdeal>>>
) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "NewCharacterViewModel"
    }

    /**
     * Character's bonds
     */
    var characterBonds: MutableList<DomainBond>? = mutableListOf()

    /**
     * Character's ideals
     */
    var characterIdeals: MutableList<DomainIdeal?> = mutableListOf()
    /**
     * Character's picture.
     */
    var characterPictureUri: Uri? = null


    /**
     * Character's id.
     */
    var characterId: Long? = null
        set(value) {
            Log.d("NewCharacterViewModel characterId", value.toString())
            field = value
        }
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


    fun saveCharacter(characteristics: List<DomainRollCharacteristic>?): Long? {
        if (!characterName.isNullOrEmpty()) {
            Log.d("NewCharacterViewModel _ saveCharacterName", characterName)
        }
        if (!characterAge?.toString().isNullOrEmpty()) {
            Log.d("NewCharacterViewModel _ saveCharacterAge", characterAge?.toString())
        }
        if (!characterGender.isNullOrEmpty()) {
            Log.d("NewCharacterViewModel _ saveCharacterGender", characterGender)
        }
        if (!characterBiography.isNullOrEmpty()) {
            Log.d("NewCharacterViewModel _ saveCharacterBiography", characterBiography)
        }
        if (!characterHeight?.toString().isNullOrEmpty()) {
            Log.d("NewCharacterViewModel _ saveCharacterHeight", characterHeight?.toString())
        }
        if (characterBreed != null) {
            var breed = breedsRepository.findOneWithChildren(characterBreed?.breedId)
            Log.d("NewCharacterViewModel _ saveCharacterBreed", breed?.breed?.breedName)

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

        if (!characterIdeals.isNullOrEmpty()) {
            characterIdeals.forEach {
                Log.d(
                    "NewCharacterViewModel _ savecharacterIdeals",
                    it?.idealName
                )
            }
        }

        if (!characteristics.isNullOrEmpty()) {
            characteristics.forEach {
                Log.d(
                    TAG,
                    "Char : " + it.characteristicName + " " + it.characteristicTotal
                )


            }
        }

        var character =
            DomainCharacter(
                characterId = characterId,
                characterName = this.characterName,
                characterAge = this.characterAge,
                characterGender = this.characterGender,
                characterBiography = this.characterBiography,
                characterHeight = this.characterHeight,
                characterBreed = this.characterBreed,
                characterIdeaPoints = null,
                characterPictureUri = this.characterPictureUri.toString(),
                characterBonds = this.characterBonds,
                characterIdeals = this.characterIdeals,
                //characterSkills = null,
                //characterJob = null,
                //characterIdeals = null,
                //characterHobby = null,
                characterHealthPoints = null,
                characterEnergyPoints = null,
                characterAlignment = null,
                characterAppearance = characteristics?.find { c -> c.characteristicName == CharacteristicsName.APPEARANCE.characteristicName },
                characterCharisma = characteristics?.find { c -> c.characteristicName == CharacteristicsName.CHARISMA.characteristicName },
                characterConstitution = characteristics?.find { c -> c.characteristicName == CharacteristicsName.CONSTITUTION.characteristicName },
                characterDexterity = characteristics?.find { c -> c.characteristicName == CharacteristicsName.DEXTERITY.characteristicName },
                characterIntelligence = characteristics?.find { c -> c.characteristicName == CharacteristicsName.INTELLIGENCE?.characteristicName },
                characterPower = characteristics?.find { c -> c.characteristicName == CharacteristicsName.POWER.characteristicName },
                characterSize = characteristics?.find { c -> c.characteristicName == CharacteristicsName.SIZE.characteristicName },
                characterStrength = characteristics?.find { c -> c.characteristicName == CharacteristicsName.STRENGTH.characteristicName }
            )



        try {
            if (characterId == null) {

                Log.d("CHARACTER", "INSERT")

                characterId = characterRepository.insertOne(character)
                Log.d("CHARACTER", "character $character")
            } else {
                Log.d("CHARACTER", "UPDATE")
                Log.d("CHARACTER", "character $character ")
                characterRepository.updateOne(character)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

        Log.d("RESULT", character.toString())

        val searchResult = characterRepository.findOneById(characterId)

        Log.d("ideals", "$searchResult")

        return characterId
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

    fun addIdeal(domainModel: DomainIdeal) {
        characterIdeals.add(domainModel)
    }

    fun removeIdeal(domainModel: DomainIdeal) {
        if (!characterIdeals.isNullOrEmpty()) {
            if (characterIdeals.contains(domainModel)) {
                characterIdeals.remove(domainModel)
            }
        }


    }


}