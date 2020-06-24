// File NewCharacterViewModel.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels.character

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.DomainBond
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.DomainCharacterWithSkills
import com.uldskull.rolegameassistant.models.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.models.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.repository.character.CharacterRepository
import com.uldskull.rolegameassistant.repository.skill.FilledOccupationSkillRepository
import com.uldskull.rolegameassistant.useCases.skills.GetAnHobbySkillWithCharacterIdUseCase
import java.util.*

/**
 *   Class "NewCharacterViewModel" :
 *   Designed to store and manage UI-related data in a lifecycle conscious way.
 *   The ViewModel class allows data to survive configuration changes such as
 *   screen rotations.
 **/
class NewCharacterViewModel(
    application: Application,
    private val characterRepository: CharacterRepository<LiveData<List<DomainCharacter>>>,
    private val filledOccupationSkillRepository: FilledOccupationSkillRepository<LiveData<List<DomainSkillToFill>>>,
    private val getAnHobbySkillWithCharacterIdUseCase: GetAnHobbySkillWithCharacterIdUseCase
) : AndroidViewModel(application) {


    /**
     * Companion object
     */
    companion object {
        private const val TAG = "NewCharacterViewModel"
    }

    /**
     * Character occupation
     */
    var characterOccupation: DomainOccupation? = null

    /**
     * Character's bonds
     */
    var characterBonds: MutableList<DomainBond?>? = mutableListOf()

    /**
     * Character's picture.
     */
    var characterPictureUri: String? = null

    /**
     * Character skills ids
     */
    var characterSkillsIds: MutableList<Long?> = mutableListOf()

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
    var characterHeight = MutableLiveData<Int>()

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
    var characterAge = MutableLiveData<Int>()

    /**
     * Character gender.
     */
    var characterGender = MutableLiveData<String>()

    /**
     * Character biography.
     */
    var characterBiography = MutableLiveData<String>()

    /**
     * Save age into view model
     */
    fun saveAge(characterAge: String) {
        Log.d("NewCharacterViewModel", "age = $characterAge")
        if (characterAge.isNotEmpty()) {
            try {
                this.characterAge.value = characterAge.toInt()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    /**
     * Represents the selected character.
     */
    var currentCharacter:DomainCharacter? = null

    init {
        if (currentCharacter == null) {
            currentCharacter = emptyCharacter()
        }
    }

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
        sanityScore: Int?,
        luckScore: Int?,
        knowScore: Int?,
        baseHealth: Int?,
        breedBonus: Int?,
        skillsIds: List<Long?>,
        filledOccupationSkills: List<DomainSkillToFill>?,
        filledHobbySkills: List<DomainSkillToFill>?,
        spentOccupationPoints: Int?
    ): Long? {

        if (currentCharacter == null) {
            currentCharacter= emptyCharacter()
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
        if (currentCharacter?.characterBreeds == null) {
            currentCharacter?.characterBreeds = mutableListOf()
        }

        if (currentCharacter?.characterIdeals == null) {
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
        if (sanityScore != null) {
            currentCharacter?.characterSanity = sanityScore
        }
        if (luckScore != null) {
            currentCharacter?.characterLuck = luckScore
        }
        if (knowScore != null) {
            currentCharacter?.characterKnow = knowScore
        }
        if (baseHealth != null) {
            currentCharacter?.characterBaseHealthPoints = baseHealth
        }
        if (breedBonus != null) {
            currentCharacter?.characterBreedBonus = breedBonus
        }
        if (skillsIds != null) {
            currentCharacter?.characterSelectedOccupationSkill = skillsIds.toMutableList()
        }
        if (characterOccupation != null) {
            currentCharacter?.characterOccupation = characterOccupation
        }
        if (spentOccupationPoints != null) {
            currentCharacter?.characterSpentOccupationPoints = spentOccupationPoints
        }

        var characterId: Long?

        characterId = if (currentCharacter?.characterId == null) {
            null
        } else {
            currentCharacter?.characterId
        }

        if (characterId == null) {
            Log.d("DEBUG$TAG", "INSERT")
            try {
                characterId = characterRepository.insertOne(currentCharacter)
                currentCharacter?.characterId = characterId

            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        } else {
            try {
                characterRepository.updateOne(currentCharacter)

            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        }
        Log.d("DEBUG$TAG", "character $currentCharacter ")

        try {
            if (characterId != null) {
                try {
                    //  Hobby skills
                    insertHobbySkills(filledHobbySkills)
                    //  Get hobby skills
                    var hobbySkills = getCharacterSkills(characterId, 1)
                } catch (e: Exception) {
                    Log.e("ERROR", "Failed to insert hobby skills")
                    e.printStackTrace()
                    throw e
                }

                try {
                    //   Occupation skills
                    insertOccupationSkills(filledOccupationSkills)
                    //  Gets occupation skills
                    val occupationSkills = getCharacterSkills(characterId, 0)
                    occupationSkills?.forEach {
                        Log.d(
                            "DEBUG$TAG",
                            "Occupation skill :${it.skillName?.toUpperCase(Locale.ENGLISH)} - ${it.filledSkillTensValue}${it.filledSkillUnitsValue}"
                        )
                    }
                } catch (e: Exception) {
                    Log.e("ERROR", "Failed to insert occupation skills")
                    e.printStackTrace()
                    throw e
                }

                //  Gets all skills
                var skills = getCharacterWithSkills(characterId)

            }
        } catch (e: Exception) {
            Log.e("ERROR", "Insertion failed")
            e.printStackTrace()
            throw e
        }
        Log.d("RESULT", currentCharacter.toString())
        var breeds = getCharacterWithBreeds(characterId)
        return currentCharacter?.characterId
    }

    /**
     * Get character with skills
     */
    fun getCharacterWithSkills(id: Long?): DomainCharacterWithSkills? {
        val characterWithSkills: DomainCharacterWithSkills? =
            characterRepository.findOneWithOccupationSkills(id)
        Log.d("DBUG$TAG", "characterWithSkills: $characterWithSkills")
        return characterWithSkills
    }
    /**
     * Get character skills by type and by id
     * id thend type
     */
    fun getCharacterSkills(id: Long?, type: Long): List<DomainSkillToFill>? {
        val characterWithSkills: DomainCharacterWithSkills? =
            characterRepository.findOneWithOccupationSkills(id)
        return characterWithSkills?.skills?.filter { s -> s.filledSkillType == type }
    }

    /**
     * Insert occupations skills
     */
    private fun insertOccupationSkills(filledOccupationSkills: List<DomainSkillToFill>?) {
        filledOccupationSkills?.forEach { filledSkill ->
            val newSkill = DomainSkillToFill(
                filledSkillCharacterId = currentCharacter?.characterId,
                filledSkillTotal = filledSkill.filledSkillTotal,
                filledSkillTensValue = filledSkill.filledSkillTensValue,
                filledSkillUnitsValue = filledSkill.filledSkillUnitsValue,
                filledSkillName = filledSkill.skillName,
                filledSkillMax = filledSkill.filledSkillMax,
                filledSkillId = null,
                filledSkillBase = filledSkill.filledSkillBase,
                filledSkillType = 0
            )
            Log.d(
                "DEBUG$TAG",
                "OccupationSkill : ${newSkill.skillName?.toUpperCase(Locale.ENGLISH)} - ${newSkill.filledSkillTensValue}${newSkill.filledSkillUnitsValue}"
            )
            val oldSkill = filledOccupationSkillRepository.findTheSame(newSkill)

            if (oldSkill == null) {
                Log.d("DEBUG$TAG", "insertOne".toUpperCase(Locale.ENGLISH))
                val id = filledOccupationSkillRepository.insertOne(newSkill)
                Log.d("DEBUG$TAG", "Inserted id : $id")
            } else {
                Log.d("DEBUG$TAG", "updateOne".toUpperCase(Locale.ENGLISH))
                Log.d(
                    "DEBUG$TAG",
                    "Old skill : ${oldSkill.skillName?.toUpperCase(Locale.ENGLISH)} - ${oldSkill.filledSkillTensValue}${oldSkill.filledSkillUnitsValue}"
                )
                oldSkill.filledSkillUnitsValue = newSkill.filledSkillUnitsValue
                oldSkill.filledSkillTensValue = newSkill.filledSkillTensValue
                Log.d(
                    "DEBUG$TAG",
                    "Old skill : ${oldSkill.skillName?.toUpperCase(Locale.ENGLISH)} - ${oldSkill.filledSkillTensValue}${oldSkill.filledSkillUnitsValue}"
                )

                val count = filledOccupationSkillRepository.updateOne(oldSkill)
                Log.d("DEBUG$TAG", "$count skills updated.")
            }
        }
    }

    /**
     * Insert hobby skills
     */
    private fun insertHobbySkills(filledHobbySkills: List<DomainSkillToFill>?) {
        Log.d("DEBUG$TAG", "FilledHobbySkills : ${filledHobbySkills?.size}")
        filledHobbySkills?.forEach {
            val newSkill = DomainSkillToFill(
                filledSkillCharacterId = currentCharacter?.characterId,
                filledSkillTotal = it.filledSkillTotal,
                filledSkillBase = it.filledSkillBase,
                filledSkillMax = it.filledSkillMax,
                filledSkillName = it.skillName,
                filledSkillTensValue = it.filledSkillTensValue,
                filledSkillUnitsValue = it.filledSkillUnitsValue,
                filledSkillType = 1
            )
            Log.d(
                "DEBUG$TAG",
                "HobbySkill : ${newSkill.skillName?.toUpperCase(Locale.ENGLISH)} - ${newSkill.filledSkillTensValue}${newSkill.filledSkillUnitsValue}"
            )
            val oldSkill = filledOccupationSkillRepository.findTheSame(newSkill)

            if (oldSkill == null) {
                Log.d("DEBUG$TAG", "insertOne".toUpperCase(Locale.ENGLISH))
                val id = filledOccupationSkillRepository.insertOne(newSkill)
                Log.d("DEBUG$TAG", "Inserted id : $id")
            } else {
                Log.d("DEBUG$TAG", "updateOne".toUpperCase(Locale.ENGLISH))
                Log.d(
                    "DEBUG$TAG",
                    "Old skill : ${oldSkill.skillName?.toUpperCase(Locale.ENGLISH)} - ${oldSkill.filledSkillTensValue}${oldSkill.filledSkillUnitsValue}"
                )
                oldSkill.filledSkillUnitsValue = newSkill.filledSkillUnitsValue
                oldSkill.filledSkillTensValue = newSkill.filledSkillTensValue
                Log.d(
                    "DEBUG$TAG",
                    "Old skill : ${oldSkill.skillName?.toUpperCase(Locale.ENGLISH)} - ${oldSkill.filledSkillTensValue}${oldSkill.filledSkillUnitsValue}"
                )

                val count = filledOccupationSkillRepository.updateOne(oldSkill)
                Log.d("DEBUG$TAG", "$count skills updated.")
            }


        }
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
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.SIZE.characteristicName }
            currentCharacter?.characterStrength =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.STRENGTH.characteristicName }
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
                        it.bondTitle
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
    fun getCharacterWithBreeds(id: Long?) {
        val character = characterRepository.findOneById(id)
        character?.characterBreeds?.forEach {
            Log.d("DEBUG$TAG", "Breed id : $it")
        }

    }

    /**
     * Set character's height.
     */
    private fun setHeight() {
        if (characterHeight.toString().isNotEmpty()) {
            Log.d("NewCharacterViewModel _ saveCharacterHeight", characterHeight.toString())
            currentCharacter?.characterHeight = characterHeight.value
        }
    }


    /**
     * Set character's biography.
     */
    private fun setBiography() {
        if (!characterBiography.value.isNullOrEmpty()) {
            Log.d(TAG, "CharacterBiography $characterBiography")
            currentCharacter?.characterBiography = characterBiography.value
        }
    }

    /**
     * Set character's gender.
     */
    private fun setGender() {
        if (!characterGender.value.isNullOrEmpty()) {
            Log.d(TAG, "CharacterGender $characterGender")
            currentCharacter?.characterGender = characterGender.value
        }
    }

    /**
     * Set character's age.
     */
    private fun setAge() {
        if (characterAge.toString().isNotEmpty()) {
            Log.d(TAG, "CharacterAge $characterAge")
            currentCharacter?.characterAge = characterAge.value
        }
    }

    /**
     * Set character's weight.
     */
    private fun setWeight() {
        if (characterWeight.value != null) {
            Log.d("DEBUG$TAG", "Character weight = ${characterWeight.value?.toString()}")
            currentCharacter?.characterWeight = characterWeight.value

        }
    }

    /**
     * Set character's name
     */
    private fun setName() {
        if (!characterName.value.isNullOrEmpty()) {
            Log.d(TAG, "Name : $characterName")
            val character = currentCharacter
            val newCharacter = DomainCharacter(
                //  Change character name
                characterName = characterName.value,
                //  Keep values
                characterId = character?.characterId,
                characterAge = character?.characterAge,
                characterGender = character?.characterGender,
                characterBiography = character?.characterBiography,
                characterOccupation = character?.characterOccupation,
                characterSelectedOccupationSkill = character?.characterSelectedOccupationSkill,
                characterBreedBonus = character?.characterBreedBonus,
                characterBaseHealthPoints = character?.characterBaseHealthPoints,
                characterKnow = character?.characterKnow,
                characterLuck = character?.characterLuck,
                characterSanity = character?.characterSanity,
                characterWeight = character?.characterWeight,
                characterAlignment = character?.characterAlignment,
                characterAppearance = character?.characterAppearance,
                characterBonds = character?.characterBonds,
                characterConstitution = character?.characterConstitution,
                characterDexterity = character?.characterDexterity,
                characterEducation = character?.characterEducation,
                characterEnergyPoints = character?.characterEnergyPoints,
                characterHealthPoints = character?.characterHealthPoints,
                characterHeight = character?.characterHeight,
                characterIdeals = character?.characterIdeals,
                characterIdeaPoints = character?.characterIdeaPoints,
                characterIntelligence = character?.characterIntelligence,
                characterPictureUri = character?.characterPictureUri,
                characterPower = character?.characterPower,
                characterSize = character?.characterSize,
                characterStrength = character?.characterStrength,
                characterBreeds = character?.characterBreeds,
                characterSelectedHobbiesSkill = character?.characterSelectedHobbiesSkill
            )
            currentCharacter = newCharacter
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
            characterBreedBonus = null,
            characterSelectedOccupationSkill = mutableListOf(),
            characterOccupation = null,
            characterBreeds = mutableListOf(),
            characterSelectedHobbiesSkill = mutableListOf()
        )
    }

    /**
     * Save character's height.
     */
    fun saveHeight(characterHeight: String) {
        Log.d("NewCharacterViewModel", "height = $characterHeight")
        if (characterHeight != null && characterHeight.isNotEmpty()) {
            try {
                this.characterHeight.value = characterHeight.toInt()
            } catch (e: Exception) {
                throw e
            }
        }
    }
}


