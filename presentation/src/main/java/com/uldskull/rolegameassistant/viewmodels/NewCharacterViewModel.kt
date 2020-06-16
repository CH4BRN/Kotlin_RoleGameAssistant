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
import com.uldskull.rolegameassistant.models.character.character.DomainCharacterWithSkills
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedsCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.repository.breed.DisplayedBreedsRepository
import com.uldskull.rolegameassistant.repository.character.CharacterRepository
import com.uldskull.rolegameassistant.repository.characteristic.BreedsCharacteristicRepository
import com.uldskull.rolegameassistant.repository.ideal.IdealsRepository
import com.uldskull.rolegameassistant.repository.skill.FilledOccupationSkillRepository
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
    private val idealsRepository: IdealsRepository<LiveData<List<DomainIdeal>>>,
    private val filledOccupationSkillRepository: FilledOccupationSkillRepository<LiveData<List<DomainSkillToFill>>>
) : AndroidViewModel(application) {


    /**
     * Companion object
     */
    companion object {
        private const val TAG = "NewCharacterViewModel"
    }

    var characterOccupation: DomainOccupation? = null

    /**
     * Character's bonds
     */
    var characterBonds: MutableList<DomainBond?>? = mutableListOf()

    /**
     * Character's picture.
     */
    var characterPictureUri: Uri? = null

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
                this.characterAge.value = characterAge.toInt()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun getCharacterWithSkills(id: Long?): DomainCharacterWithSkills? {
        var characterWithSkills: DomainCharacterWithSkills? =
            characterRepository.findOneWithOccupationSkills(id)
        Log.d("DBUG$TAG", "characterWithSkills: $characterWithSkills")
        return characterWithSkills
    }

    /**
     * Get character skills by type and by id
     * id thend type
     */
    fun getCharacterSkills( id: Long?, type: Long): List<DomainSkillToFill>? {
        var characterWithSkills: DomainCharacterWithSkills? =
            characterRepository.findOneWithOccupationSkills(id)
        return characterWithSkills?.skills?.filter { s -> s.filledSkillType == type }
    }

    /**
     * Represents the selected character.
     */
    var currentCharacter = MutableLiveData<DomainCharacter>()

    init {
        if (currentCharacter.value == null) {
            currentCharacter.value = emptyCharacter()
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
            currentCharacter.value = emptyCharacter()
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
        if (currentCharacter?.value?.characterBreeds == null) {
            currentCharacter?.value?.characterBreeds = mutableListOf()
        }

        if (currentCharacter?.value?.characterIdeals == null) {
            currentCharacter?.value?.characterIdeals = mutableListOf()
        }
        if (ideaScore != null) {
            currentCharacter?.value?.characterIdeaPoints = ideaScore
        }
        if (healthScore != null) {
            currentCharacter?.value?.characterHealthPoints = healthScore
        }
        if (energyScore != null) {
            currentCharacter?.value?.characterEnergyPoints = energyScore
        }
        if (sanityScore != null) {
            currentCharacter?.value?.characterSanity = sanityScore
        }
        if (luckScore != null) {
            currentCharacter?.value?.characterLuck = luckScore
        }
        if (knowScore != null) {
            currentCharacter?.value?.characterKnow = knowScore
        }
        if (baseHealth != null) {
            currentCharacter?.value?.characterBaseHealthPoints = baseHealth
        }
        if (breedBonus != null) {
            currentCharacter?.value?.characterBreedBonus = breedBonus
        }
        if (skillsIds != null) {
            currentCharacter?.value?.characterSelectedOccupationSkill = skillsIds.toMutableList()
        }
        if (characterOccupation != null) {
            currentCharacter?.value?.characterOccupation = characterOccupation
        }
        if (spentOccupationPoints != null) {
            currentCharacter?.value?.characterSpentOccupationPoints = spentOccupationPoints
        }
        try {
            if (currentCharacter?.value?.characterId == null) {
                Log.d("DEBUG$TAG", "INSERT")
                characterId = characterRepository.insertOne(currentCharacter.value)
                currentCharacter?.value?.characterId = characterId
                Log.d("DEBUG$TAG", "character $currentCharacter")
            } else {
                Log.d("DEBUG$TAG", "UPDATE")
                Log.d("DEBUG$TAG", "character $currentCharacter ")
                characterRepository.updateOne(currentCharacter.value)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

        try {
            if (currentCharacter?.value?.characterId != null) {
                //  Hobby skills
                insertHobbySkills(filledHobbySkills)

                //   Occupation skills
                insertOccupationSkills(filledOccupationSkills)

                var skills = getCharacterWithSkills(characterId)
                Log.d("DEBUG$TAG", "Skills : ${skills?.skills}")

                var occupationSkills = getCharacterSkills(characterId, 0)
                occupationSkills?.forEach {
                    Log.d("DEBUG$TAG", "Occupation skill :${it.skillName?.toUpperCase()}")
                }

                var hobbySkills = getCharacterSkills(characterId, 1)
                hobbySkills?.forEach {
                    Log.d("DEBUG$TAG", "Hobby skill :${it.skillName?.toUpperCase()}")
                }
            }
        } catch (e: Exception) {
            Log.e("ERROR", "Insertion failed")
            e.printStackTrace()
            throw e
        }
        Log.d("RESULT", currentCharacter.toString())
        var breeds = getCharacterWithBreeds(characterId)



        // TODO Check saved breeds
        return currentCharacter?.value?.characterId
    }

    private fun insertOccupationSkills(filledOccupationSkills: List<DomainSkillToFill>?) {
        filledOccupationSkills?.forEach {
            var newSkill = DomainSkillToFill(
                filledSkillCharacterId = currentCharacter?.value?.characterId,
                filledSkillUnitsValue = it.filledSkillUnitsValue,
                filledSkillTotal = it.filledSkillTotal,
                filledSkillTensValue = it.filledSkillTensValue,
                filledSkillName = it.skillName,
                filledSkillMax = it.filledSkillMax,
                filledSkillId = it.skillId,
                filledSkillBase = it.filledSkillBase,
                filledSkillType = 0
            )
            Log.d("DEBUG$TAG", "OccupationSkill : $newSkill")
            if (filledOccupationSkillRepository.findOneById(newSkill.skillId) == null) {
                filledOccupationSkillRepository?.insertOne(newSkill)
                Log.d(
                    "DEBUG$TAG",
                    "Inserted skill ${newSkill.skillName} -  ${newSkill.filledSkillCharacterId}"
                )
            } else {
                var skill = filledOccupationSkillRepository.findOneById(newSkill.skillId)
                skill?.filledSkillCharacterId = currentCharacter?.value?.characterId
                skill?.filledSkillTensValue = newSkill?.filledSkillTensValue
                skill?.filledSkillUnitsValue = newSkill?.filledSkillUnitsValue

                var updated = filledOccupationSkillRepository.updateOne(skill)
                Log.d(
                    "DEBUG$TAG",
                    "Updated $updated skill : ${skill?.skillName} - ${skill?.filledSkillCharacterId}"
                )
            }
        }
    }

    private fun insertHobbySkills(filledHobbySkills: List<DomainSkillToFill>?) {
        filledHobbySkills?.forEach {
            var newSkill = DomainSkillToFill(
                filledSkillCharacterId = currentCharacter?.value?.characterId,
                filledSkillUnitsValue = it.filledSkillUnitsValue,
                filledSkillTotal = it.filledSkillTotal,
                filledSkillBase = it.filledSkillBase,
                filledSkillMax = it.filledSkillMax,
                filledSkillName = it.skillName,
                filledSkillTensValue = it.filledSkillTensValue,
                filledSkillType = 1
            )
            Log.d("DEBUG$TAG", "HobbySkill : $newSkill")
            if (filledOccupationSkillRepository.findOneById(newSkill.skillId) == null) {
                filledOccupationSkillRepository?.insertOne(newSkill)
                Log.d(
                    "DEBUG$TAG",
                    "Inserted skill ${newSkill.skillName} -  ${newSkill.filledSkillCharacterId}"
                )
            } else {
                var skill = filledOccupationSkillRepository.findOneById(newSkill.skillId)
                skill?.filledSkillCharacterId = currentCharacter?.value?.characterId
                skill?.filledSkillTensValue = newSkill?.filledSkillTensValue
                skill?.filledSkillUnitsValue = newSkill?.filledSkillUnitsValue

                var updated = filledOccupationSkillRepository.updateOne(skill)
                Log.d(
                    "DEBUG$TAG",
                    "Updated $updated skill : ${skill?.skillName} - ${skill?.filledSkillCharacterId}"
                )
            }
        }
    }

    /**
     * Set character Id
     */
    private fun setId() {
        if (characterId != null) {
            currentCharacter?.value?.characterId = characterId
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
            currentCharacter?.value?.characterAppearance =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.APPEARANCE.characteristicName }
            currentCharacter?.value?.characterConstitution =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.CONSTITUTION.characteristicName }
            currentCharacter?.value?.characterDexterity =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.DEXTERITY.characteristicName }
            currentCharacter?.value?.characterEducation =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.EDUCATION.characteristicName }
            currentCharacter?.value?.characterIntelligence =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.INTELLIGENCE.characteristicName }
            currentCharacter?.value?.characterPower =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.POWER.characteristicName }
            currentCharacter?.value?.characterSize =
                characteristics.find { c -> c?.characteristicName == CharacteristicsName.SIZE.characteristicName }
            currentCharacter?.value?.characterStrength =
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
            currentCharacter.value?.characterBonds = characterBonds
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
            currentCharacter?.value?.characterPictureUri = characterPictureUri.toString()
        }
    }

    /**
     * Get a character with its breeds.
     */
    fun getCharacterWithBreeds(id: Long?) {
        var character = characterRepository.findOneById(id)
        character?.characterBreeds?.forEach {
            Log.d("DEBUG$TAG", "Breed id : $it")
        }

    }

    /**
     * Set character's height.
     */
    private fun setHeight() {
        if (!characterHeight?.toString().isNullOrEmpty()) {
            Log.d("NewCharacterViewModel _ saveCharacterHeight", characterHeight?.toString())
            currentCharacter?.value?.characterHeight = characterHeight.value
        }
    }


    /**
     * Set character's biography.
     */
    private fun setBiography() {
        if (!characterBiography.value.isNullOrEmpty()) {
            Log.d(TAG, "CharacterBiography $characterBiography")
            currentCharacter?.value?.characterBiography = characterBiography.value
        }
    }

    /**
     * Set character's gender.
     */
    private fun setGender() {
        if (!characterGender.value.isNullOrEmpty()) {
            Log.d(TAG, "CharacterGender $characterGender")
            currentCharacter?.value?.characterGender = characterGender.value
        }
    }

    /**
     * Set character's age.
     */
    private fun setAge() {
        if (!characterAge?.toString().isNullOrEmpty()) {
            Log.d(TAG, "CharacterAge ${characterAge?.toString()}")
            currentCharacter?.value?.characterAge = characterAge.value
        }
    }

    /**
     * Set character's weight.
     */
    private fun setWeight() {
        if (characterWeight.value != null) {
            Log.d("DEBUG$TAG", "Character weight = ${characterWeight.value?.toString()}")
            currentCharacter?.value?.characterWeight = characterWeight.value

        }
    }

    /**
     * Set character's name
     */
    private fun setName() {
        if (!characterName.value.isNullOrEmpty()) {
            Log.d(TAG, "Name : $characterName")
            var character = currentCharacter?.value
            var newCharacter = DomainCharacter(
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
            currentCharacter?.value = newCharacter
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