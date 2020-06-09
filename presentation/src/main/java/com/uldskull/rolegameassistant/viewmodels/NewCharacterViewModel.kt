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
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.character.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.character.DomainCharacterWithBreeds
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedsCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.repository.breed.DisplayedBreedsRepository
import com.uldskull.rolegameassistant.repository.character.CharacterRepository
import com.uldskull.rolegameassistant.repository.characteristic.BreedsCharacteristicRepository
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
    private val displayedBreedsRepository: DisplayedBreedsRepository<LiveData<List<DomainDisplayedBreed>>>,
    private val characteristicRepository: BreedsCharacteristicRepository<LiveData<List<DomainBreedsCharacteristic>>>,
    private val idealsRepository: IdealsRepository<LiveData<List<DomainIdeal>>>
) : AndroidViewModel(application) {

    /**
     * Companion object
     */
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
   // var characterIdeals: MutableList<DomainIdeal?> = mutableListOf()





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
     * Observable character weight
     */
    var characterWeight = MutableLiveData<Int>()


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
    var characterDisplayedBreeds: MutableList<DomainDisplayedBreed>? = mutableListOf()


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

    /**
     * Represents the selected character.
     */
    var currentCharacter: DomainCharacter? = null

    /**
     * Observable character
     */
    var selectedCharacter = MutableLiveData<DomainCharacter>()


    /**
     * Save a character.
     */
    fun saveCharacter(
        characteristics: List<DomainRollsCharacteristic?>?,
        ideaScore: Int?,
        healthScore: Int?,
        energyScore: Int?,
        sanityScore:Int?,
        luckScore:Int?,
        knowScore:Int?,
        baseHealth:Int?,
        breedBonus:Int?
    ): Long? {

        if (currentCharacter == null) {
            currentCharacter = emptyCharacter()
        }

        setId()
        setName()
        setWeight()
        setAge()
        setGender()
        setBiography()

        setHeight()

        setPictureUri()
        setBonds()

        setCharacteristics(characteristics)
        if(currentCharacter?.characterIdeals == null){
            currentCharacter?.characterIdeals = mutableListOf()
        }
        if (ideaScore != null) {
            currentCharacter?.characterIdeaPoints = ideaScore
        }
        if (healthScore != null) {
            currentCharacter?.characterHealthPoints = healthScore
        }
        if (energyScore != null) {
            currentCharacter?.characterEnergyPoints = energyScore
        }
        if(sanityScore != null){
            currentCharacter?.characterSanity = sanityScore
        }
        if(luckScore != null){
            currentCharacter?.characterLuck = luckScore
        }
        if(knowScore != null){
            currentCharacter?.characterKnow = knowScore
        }
        if(baseHealth != null){
            currentCharacter?.characterBaseHealthPoints = baseHealth
        }
        if(breedBonus != null){
            currentCharacter?.characterBreedBonus = breedBonus
        }
        try {
            if (currentCharacter?.characterId == null) {
                Log.d("DEBUG$TAG", "INSERT")
                characterId = characterRepository.insertOne(currentCharacter)
                currentCharacter?.characterId = characterId
                Log.d("DEBUG$TAG", "character $currentCharacter")
            } else {
                Log.d("DEBUG$TAG", "UPDATE")
                Log.d("DEBUG$TAG", "character $currentCharacter ")
                characterRepository.updateOne(currentCharacter)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

        Log.d("RESULT", currentCharacter.toString())

        return currentCharacter?.characterId
    }

    /**
     * Set character Id
     */
    private fun setId() {
        if (characterId != null) {
            currentCharacter?.characterId = characterId
        }
    }

    /**
     * Set characters's characteristics.
     */
    private fun setCharacteristics(characteristics: List<DomainRollsCharacteristic?>?) {
        if (!characteristics.isNullOrEmpty()) {
            characteristics.forEach {
                Log.d(
                    TAG,
                    "Char : " + it?.characteristicName + " " + it?.characteristicTotal
                )
            }
            currentCharacter?.characterAppearance =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.APPEARANCE.characteristicName }
            currentCharacter?.characterConstitution =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.CONSTITUTION.characteristicName }
            currentCharacter?.characterDexterity =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.DEXTERITY.characteristicName }
            currentCharacter?.characterEducation =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.EDUCATION.characteristicName }
            currentCharacter?.characterIntelligence =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.INTELLIGENCE.characteristicName }
            currentCharacter?.characterPower =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.POWER.characteristicName }
            currentCharacter?.characterSize =
                characteristics?.find { c -> c?.characteristicName == CharacteristicsName.SIZE.characteristicName }
            currentCharacter?.characterStrength =
                characteristics?.find { c -> c?.characteristicName == CharacteristicsName.STRENGTH.characteristicName }
        }
    }



    /**
     * Set character's bonds.
     */
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

    /**
     * Set character's picture uri
     */
    private fun setPictureUri() {
        if (!characterPictureUri?.toString().isNullOrEmpty()) {
            Log.d(
                "NewCharacterViewModel _ savecharacterPictureUri",
                characterPictureUri?.toString()

            )
            currentCharacter?.characterPictureUri = characterPictureUri.toString()
        }
    }

    /**
     * Get a character with its breeds.
     */
    fun getCharacterWithBreeds(id: Long?):DomainCharacterWithBreeds? {
        var cWb: DomainCharacterWithBreeds? = characterRepository?.findOneWithBreeds(id)
        Log.d("DEBUG$TAG", "$cWb")
        return cWb

    }

    /**
     * Set character's height.
     */
    private fun setHeight() {
        if (!characterHeight?.toString().isNullOrEmpty()) {
            Log.d("NewCharacterViewModel _ saveCharacterHeight", characterHeight?.toString())
            currentCharacter?.characterHeight = characterHeight
        }
    }



    /**
     * Set character's biography.
     */
    private fun setBiography() {
        if (!characterBiography.isNullOrEmpty()) {
            Log.d(TAG, "CharacterBiography $characterBiography")
            currentCharacter?.characterBiography = characterBiography
        }
    }

    /**
     * Set character's gender.
     */
    private fun setGender() {
        if (!characterGender.isNullOrEmpty()) {
            Log.d(TAG, "CharacterGender $characterGender")
            currentCharacter?.characterGender = characterGender
        }
    }

    /**
     * Set character's age.
     */
    private fun setAge() {
        if (!characterAge?.toString().isNullOrEmpty()) {
            Log.d(TAG, "CharacterAge ${characterAge?.toString()}")
            currentCharacter?.characterAge = characterAge
        }
    }

    /**
     * Set character's weight.
     */
    private fun setWeight() {
        if (characterWeight.value != null) {
            Log.d("DEBUG$TAG", "Character weight = ${characterWeight?.value?.toString()}")
            currentCharacter?.characterWeight = characterWeight?.value

        }
    }

    /**
     * Set character's name
     */
    private fun setName() {
        if (!characterName.value.isNullOrEmpty()) {
            Log.d(TAG, "Name : $characterName")
            currentCharacter?.characterName = characterName.value
        }
    }

    /**
     * Empty the current character.
     */
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
            characterIdeals = mutableListOf(),
            characterBonds = mutableListOf(),
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
            characterEducation = null,
            characterWeight = null,
            characterSanity = null,
            characterLuck = null,
            characterKnow = null,
            characterBaseHealthPoints = null,
            characterBreedBonus = null
        )
    }

    /**
     * Save character's height.
     */
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