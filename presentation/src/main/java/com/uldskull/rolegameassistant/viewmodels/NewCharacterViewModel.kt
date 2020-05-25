// File NewCharacterViewModel.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    var characterBonds: MutableList<DomainBond?>? = mutableListOf()

    /**
     * Character's ideals
     */
    var characterIdeals: MutableList<DomainIdeal?> = mutableListOf()

    var characterAlignment: Int = 0
        get() {
            field = calculateCharacterAlignment()
            return field
        }

    private fun calculateCharacterAlignment(): Int {
        var alignment: Int
        if (characterIdeals.isNotEmpty()) {
            alignment = 0
            characterIdeals.forEach {
                var evilPoints = 0
                if (it?.idealEvilPoints != null) {
                    evilPoints = it.idealEvilPoints!!
                }

                var goodPoints = 0
                if (it?.idealGoodPoints != null) {
                    goodPoints = it.idealGoodPoints!!
                }

                alignment -= evilPoints
                alignment += goodPoints
            }
            return alignment
        }
        return 0
    }

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
    var characterName = MutableLiveData<String>()


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
    var characterBreeds: MutableList<DomainBreed>? = mutableListOf()


    /**
     * Save age into view model
     */
    fun saveAge(characterAge: String) {
        Log.d("NewCharacterViewModel", "age = $characterAge")
        if (characterAge.isNotEmpty()) {
            try {
                this.characterAge = characterAge.toInt()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    var currentCharacter: DomainCharacter? = null

    var selectedCharacter: DomainCharacter? = null


    fun saveCharacter(
        characteristics: List<DomainRollCharacteristic?>?,
        ideaScore: Int?,
        healthScore: Int?,
        energyScore: Int?
    ): Long? {

        if (currentCharacter == null) {
            currentCharacter = emptyCharacter()
        }
        setId()
        setName()
        setAge()
        setGender()
        setBiography()
        setAlignment()
        setHeight()
        setBreeds()
        setPictureUri()
        setBonds()
        setIdeals()
        setCharacteristics(characteristics)
        if (ideaScore != null) {
            currentCharacter?.characterIdeaPoints = ideaScore
        }
        if (healthScore != null) {
            currentCharacter?.characterHealthPoints = healthScore
        }
        if (energyScore != null) {
            currentCharacter?.characterEnergyPoints = energyScore
        }
        try {
            if (currentCharacter?.characterId == null) {
                Log.d("CHARACTER", "INSERT")
                characterId = characterRepository.insertOne(currentCharacter)
                currentCharacter?.characterId = characterId
                Log.d("CHARACTER", "character $currentCharacter")
            } else {
                Log.d("CHARACTER", "UPDATE")
                Log.d("CHARACTER", "character $currentCharacter ")
                characterRepository.updateOne(currentCharacter)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

        Log.d("RESULT", currentCharacter.toString())

        return currentCharacter?.characterId
    }

    private fun setId() {
        if (characterId != null) {
            currentCharacter?.characterId = characterId
        }
    }

    private fun setCharacteristics(characteristics: List<DomainRollCharacteristic?>?) {
        if (!characteristics.isNullOrEmpty()) {
            characteristics.forEach {
                Log.d(
                    TAG,
                    "Char : " + it?.characteristicName + " " + it?.characteristicTotal
                )
            }
            currentCharacter?.characterConstitution =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.CONSTITUTION.characteristicName }
            currentCharacter?.characterAppearance =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.APPEARANCE.characteristicName }
            currentCharacter?.characterDexterity =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.DEXTERITY.characteristicName }
            currentCharacter?.characterIntelligence =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.INTELLIGENCE.characteristicName }

            var power =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.POWER.characteristicName }
            if (power != null) {
                currentCharacter?.characterPower = power!!
            }


            currentCharacter?.characterSize =
                characteristics?.find { c -> c?.characteristicName == CharacteristicsName.SIZE.characteristicName }
            currentCharacter?.characterStrength =
                characteristics?.find { c -> c?.characteristicName == CharacteristicsName.STRENGTH.characteristicName }
        }
    }

    private fun setIdeals() {
        if (!characterIdeals.isNullOrEmpty()) {
            characterIdeals.forEach {
                if (it?.idealName != null) {
                    Log.d(
                        "NewCharacterViewModel _ savecharacterIdeals",
                        it?.idealName!!
                    )
                }

            }
            currentCharacter?.characterIdeals = characterIdeals
        }
    }

    private fun setBonds() {
        if (!characterBonds.isNullOrEmpty()) {
            characterBonds?.forEach {
                if (it?.bondTitle != null) {
                    Log.d(
                        "NewCharacterViewModel _ savecharacterBonds",
                        it?.bondTitle
                    )
                }
            }

            currentCharacter?.characterBonds = characterBonds
        }
    }

    private fun setPictureUri() {
        if (!characterPictureUri?.toString().isNullOrEmpty()) {
            Log.d(
                "NewCharacterViewModel _ savecharacterPictureUri",
                characterPictureUri?.toString()

            )
            currentCharacter?.characterPictureUri = characterPictureUri.toString()
        }
    }

    private fun setBreeds() {
        if (characterBreeds != null) {
            currentCharacter?.characterBreeds = mutableListOf()
            characterBreeds!!.forEach {
                var breed = breedsRepository.findOneWithChildren(it.breedId)
                currentCharacter?.characterBreeds?.add(breed?.breed)
                Log.d("NewCharacterViewModel _ saveCharacterBreed", breed?.breed?.breedName)

                breed?.characteristics?.forEach {
                    Log.d(
                        "NewCharacterViewModel",
                        "characteristic : ${it.characteristicName} bonus ${it.characteristicBonus}"
                    )
                }
            }

        }
    }

    private fun setHeight() {
        if (!characterHeight?.toString().isNullOrEmpty()) {
            Log.d("NewCharacterViewModel _ saveCharacterHeight", characterHeight?.toString())
            currentCharacter?.characterHeight = characterHeight
        }
    }

    private fun setAlignment() {
        if (characterAlignment != null) {
            Log.d(TAG, "CharacterAlignment $characterAlignment")
            currentCharacter?.characterAlignment = characterAlignment
        }
    }

    private fun setBiography() {
        if (!characterBiography.isNullOrEmpty()) {
            Log.d(TAG, "CharacterBiography $characterBiography")
            currentCharacter?.characterBiography = characterBiography
        }
    }

    private fun setGender() {
        if (!characterGender.isNullOrEmpty()) {
            Log.d(TAG, "CharacterGender $characterGender")
            currentCharacter?.characterGender = characterGender
        }
    }

    private fun setAge() {
        if (!characterAge?.toString().isNullOrEmpty()) {
            Log.d(TAG, "CharacterAge ${characterAge?.toString()}")
            currentCharacter?.characterAge = characterAge
        }
    }

    private fun setName() {
        if (!characterName.value.isNullOrEmpty()) {
            Log.d(TAG, "Name : $characterName")
            currentCharacter?.characterName = characterName.value
        }
    }

    private fun emptyCharacter(): DomainCharacter {
        return DomainCharacter(
            characterId = null,
            characterAppearance = null,
            characterConstitution = null,
            characterDexterity = null,
            characterIntelligence = null,
            characterPower = null,
            characterSize = null,
            characterStrength = null,
            characterIdeals = null,
            characterBonds = null,
            characterPictureUri = null,
            characterHeight = null,
            characterGender = null,
            characterAlignment = null,
            characterEnergyPoints = null,
            characterHealthPoints = null,
            characterIdeaPoints = null,
            characterBiography = null,
            characterAge = null,
            characterName = null,
            characterBreeds = null,
            characterEducation = null
        )
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