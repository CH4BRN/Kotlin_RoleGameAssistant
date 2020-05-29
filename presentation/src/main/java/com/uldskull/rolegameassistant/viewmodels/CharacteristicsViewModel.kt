// File CharacteristicViewModel.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.repository.breed.DisplayedBreedsRepository
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository
import com.uldskull.rolegameassistant.repository.characteristic.RollsCharacteristicRepository
import com.uldskull.rolegameassistant.useCases.diceRoll.DiceService
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


/**
 *   Class "CharacteristicViewModel" :
 *   TODO: Fill class use.
 **/
class CharacteristicsViewModel(
    application: Application,
    private val rollsCharacteristicRepositoryImpl: RollsCharacteristicRepository<MutableLiveData<List<DomainRollsCharacteristic>>>,
    private val characteristicRepositoryImpl: CharacteristicRepository<LiveData<List<DomainCharacteristic>>>,
    private val displayedBreedsRepository: DisplayedBreedsRepository<LiveData<List<DomainDisplayedBreed>>>,
    private val diceServiceImpl: DiceService
) : AndroidViewModel(application) {
    /**
     * Displayed characteristics
     */
    var displayedCharacteristics: MutableList<DomainRollsCharacteristic>? = mutableListOf()
        set(value) {
            Log.d(TAG, "displayedCharacteristics size : ${value?.size}")
            field = value
        }


    /**
     * Constitution bonus
     */
    var constitutionBonus: Int = 0
        set(value) {
            Log.d(TAG, "constitutionBonus BEFORE $field")
            field = value
            Log.d(TAG, "constitutionBonus AFTER $field")
        }
    /**
     * Intelligence bonus
     */
    var intelligenceBonus: Int = 0
        set(value) {
            Log.d(TAG, "intelligenceBonus BEFORE $field")
            field = value
            Log.d(TAG, "intelligenceBonus AFTER $field")
        }
    /**
     * Size bonus
     */
    var sizeBonus: Int = 0
        set(value) {
            Log.d(TAG, "sizeBonus BEFORE $field")
            field = value
            Log.d(TAG, "sizeBonus AFTER $field")
        }
    /**
     * Dexterity bonus.
     */
    var dexterityBonus: Int = 0
        set(value) {
            Log.d(TAG, "dexterityBonus BEFORE $field")
            field = value
            Log.d(TAG, "dexterityBonus AFTER $field")
        }
    /**
     * Strength bonus.
     */
    var strengthBonus: Int = 0
        set(value) {
            Log.d(TAG, "strengthBonus BEFORE $field")
            field = value
            Log.d(TAG, "strengthBonus AFTER $field")
        }
    /**
     * Power bonus
     */
    var powerBonus: Int = 0
        set(value) {
            Log.d(TAG, "powerBonus BEFORE $field")
            field = value
            Log.d(TAG, "powerBonus AFTER $field")
        }
    /**
     * Appearance bonus.
     */
    var appearanceBonus: Int = 0
        set(value) {
            Log.d(TAG, "appearanceBonus BEFORE $field")
            field = value
            Log.d(TAG, "appearanceBonus AFTER $field")
        }
    /**
     * Education bonus.
     */
    var educationBonus: Int = 0
        set(value) {
            Log.d(TAG, "educationBonus BEFORE $field")
            field = value
            Log.d(TAG, "educationBonus AFTER $field")
        }

    companion object {
        private const val TAG = "CharacteristicViewModel"
    }

    /**
     * Get the checked breed.
     */
    fun getCheckedBreeds(): List<DomainDisplayedBreed> {
        return if (!characterDisplayedBreeds.isNullOrEmpty()) {
            characterDisplayedBreeds!!.filter { b -> b.breedChecked }
        } else emptyList()

    }

    /**
     * Lock object for multi-threading
     */
    private val lock = java.lang.Object()
    /**
     * Character breeds
     */
    var characterDisplayedBreeds: MutableList<DomainDisplayedBreed>? = mutableListOf()
        set(value) {
            Log.d("DEBUG $TAG", "set characterBreeds")
            var checked = value?.filter { b -> b.breedChecked }
            checked?.forEach {
                Log.d("DEBUG $TAG", "Checked : ${it.breedName}")
            }
            calculateBreedBonuses(checked)
            field = value
        }


    /**
     * Populates the roll characteristics with dice rolls
     */
    fun populateRandomRollCharacteristics() {
        var newList: MutableList<DomainRollsCharacteristic> = mutableListOf()
        newList.add(
            getConstitutionDiceRolledCharacteristic()
        )
        newList.add(
            getStrengthDiceRolledCharacteristic()
        )
        newList.add(
            getPowerDiceRolledCharacteristic()
        )
        newList.add(
            getDexterityDiceRolledCharacteristic()
        )
        newList.add(
            getSizeDiceRolledCharacteristic()
        )
        newList.add(
            getIntelligenceDiceRolledCharacteristic()
        )
        newList.add(
            getAppearanceDiceRolledCharacteristic()
        )
        newList.add(
            getEducationDiceRolledCharacteristic()
        )

        observedCharacteristics?.value = newList

    }

    private fun getConstitutionDiceRolledCharacteristic(): DomainRollsCharacteristic {
        return getDiceRollCharacteristic(
            name = CharacteristicsName.CONSTITUTION,
            bonus = constitutionBonus,
            rollRule = "3D6"
        )
    }

    private fun getStrengthDiceRolledCharacteristic(): DomainRollsCharacteristic {
        return getDiceRollCharacteristic(
            name = CharacteristicsName.STRENGTH,
            bonus = strengthBonus,
            rollRule = "3D6"
        )
    }

    private fun getPowerDiceRolledCharacteristic(): DomainRollsCharacteristic {
        return getDiceRollCharacteristic(
            name = CharacteristicsName.POWER,
            bonus = powerBonus,
            rollRule = "3D6"
        )
    }

    private fun getDexterityDiceRolledCharacteristic(): DomainRollsCharacteristic {
        return getDiceRollCharacteristic(
            name = CharacteristicsName.DEXTERITY,
            bonus = dexterityBonus,
            rollRule = "3D6"
        )
    }

    private fun getSizeDiceRolledCharacteristic(): DomainRollsCharacteristic {
        return getDiceRollCharacteristic(
            name = CharacteristicsName.SIZE,
            bonus = sizeBonus,
            rollRule = "2D6+6"
        )
    }

    private fun getIntelligenceDiceRolledCharacteristic(): DomainRollsCharacteristic {
        return getDiceRollCharacteristic(
            name = CharacteristicsName.INTELLIGENCE,
            bonus = intelligenceBonus,
            rollRule = "2D6+6"
        )
    }

    private fun getAppearanceDiceRolledCharacteristic(): DomainRollsCharacteristic {
        return getDiceRollCharacteristic(
            name = CharacteristicsName.APPEARANCE,
            bonus = appearanceBonus,
            rollRule = "3D6"
        )
    }

    private fun getEducationDiceRolledCharacteristic(): DomainRollsCharacteristic {
        return getDiceRollCharacteristic(
            name = CharacteristicsName.EDUCATION,
            bonus = educationBonus,
            rollRule = "3D6+3"
        )
    }

    fun getDiceRollCharacteristic(
        name: CharacteristicsName,
        bonus: Int?,
        rollRule: String?
    ): DomainRollsCharacteristic {

        var finalRoll: Int = 0

        when (name.characteristicName) {
            CharacteristicsName.STRENGTH.characteristicName
            -> {
                diceServiceImpl.getMultipleDiceRollWithANumberOfFace(arrayListOf(6, 6, 6)).forEach {
                    finalRoll += it
                }
            }
            CharacteristicsName.CONSTITUTION.characteristicName
            -> {
                println("CONSTITUTION")
                diceServiceImpl.getMultipleDiceRollWithANumberOfFace(arrayListOf(6, 6, 6)).forEach {
                    finalRoll += it
                }
            }
            CharacteristicsName.DEXTERITY.characteristicName
            -> {
                println("DEXTERITY")
                diceServiceImpl.getMultipleDiceRollWithANumberOfFace(arrayListOf(6, 6, 6)).forEach {
                    finalRoll += it
                }
            }
            CharacteristicsName.APPEARANCE.characteristicName
            -> {
                println("DEXTERITY")
                diceServiceImpl.getMultipleDiceRollWithANumberOfFace(arrayListOf(6, 6, 6)).forEach {
                    finalRoll += it
                }
            }
            CharacteristicsName.POWER.characteristicName
            -> {
                println("POWER")
                diceServiceImpl.getMultipleDiceRollWithANumberOfFace(arrayListOf(6, 6, 6)).forEach {
                    finalRoll += it
                }
            }
            CharacteristicsName.SIZE.characteristicName
            -> {
                println("SIZE")
                diceServiceImpl.getMultipleDiceRollWithANumberOfFace(arrayListOf(6, 6)).forEach {
                    finalRoll += it
                }
                finalRoll += 6
            }
            CharacteristicsName.INTELLIGENCE.characteristicName
            -> {
                println("INTELLIGENCE")
                diceServiceImpl.getMultipleDiceRollWithANumberOfFace(arrayListOf(6, 6)).forEach {
                    finalRoll += it
                }
                finalRoll += 6
            }
            CharacteristicsName.EDUCATION.characteristicName
            -> {
                println("EDUCATION")
                diceServiceImpl.getMultipleDiceRollWithANumberOfFace(arrayListOf(6, 6, 6)).forEach {
                    finalRoll += it
                }
                finalRoll += 3
            }
        }

        var total = finalRoll
        if (bonus != null) {
            total = finalRoll + bonus
        }

        return DomainRollsCharacteristic(
            characteristicId = null,
            characteristicName = name.characteristicName,
            characteristicBonus = bonus,
            characteristicTotal = total,
            characteristicRoll = finalRoll,
            characteristicMax = 24,
            characteristicRollRule = rollRule
        )

    }

    fun getIntelligence(): DomainRollsCharacteristic? {
        var intelligence =
            displayedCharacteristics?.find { c -> c.characteristicName == CharacteristicsName.INTELLIGENCE.toString() }
        Log.d(TAG, "INTELLIGENCE : $intelligence")

        return intelligence
    }

    fun getEducation(): DomainRollsCharacteristic? {
        var education =
            displayedCharacteristics?.find { c -> c.characteristicName == CharacteristicsName.EDUCATION.characteristicName }
        Log.d(TAG, "EDUCATION : $education")

        return education
    }

    /**
     * Get occupation skills score
     */
    fun getOccupationSkillsScore(): Int {
        var score = 0

        var education = getEducation()
        var educationScore = education?.characteristicTotal
        if (educationScore != null) {
            score = educationScore * 20
        }
        return score
    }


    private fun displayBonuses() {
        Log.d(
            TAG, "Bonuses : \n" +
                    "\tapp : $appearanceBonus\n" +
                    "\tcon : $constitutionBonus\n" +
                    "\tdex : $dexterityBonus\n" +
                    "\tint : $intelligenceBonus\n" +
                    "\tpow : $powerBonus\n" +
                    "\tsiz : $sizeBonus\n" +
                    "\tstr : $strengthBonus\n" +
                    "\tedu : $educationBonus"

        )
    }

    var observedCharacteristics = rollsCharacteristicRepositoryImpl.getAll()

    private fun initializeBonuses() {
        appearanceBonus = 0
        constitutionBonus = 0
        dexterityBonus = 0
        intelligenceBonus = 0
        powerBonus = 0
        sizeBonus = 0
        strengthBonus = 0
        educationBonus = 0
    }


    /**
     * Initialize the ViewModel
     */
    init {
        //  Refreshes the data.
        refreshDataFromRepository()
    }


    /**
     * Refreshes the data from the repository.
     */
    private fun refreshDataFromRepository() {
        Log.d(TAG, "refreshDataFromRepository")
        viewModelScope.launch {
            try {
                observedCharacteristics = rollsCharacteristicRepositoryImpl.getAll()

            } catch (e: Exception) {
                Log.e(TAG, "refreshData FAILED")
                e.printStackTrace()
                throw e
            }
        }
    }


    fun saveAllCharacteristics(domainCharacteristics: List<DomainCharacteristic>): List<Long>? =
        synchronized(lock) {
            Log.d(TAG, "saveAllCharacteristics")
            var result: List<Long>? = null

            thread(start = true) {
                result = characteristicRepositoryImpl.insertAll(domainCharacteristics)
                Log.d("CharacteristicViewModel", "INSERTED $result")
            }
            lock.notifyAll()
            return result
        }


    fun deleteAllRollCharacteristics(): Int? {
        Log.d(TAG, "deleteAllRollCharacteristics")
        thread(start = true) {
            rollsCharacteristicRepositoryImpl.deleteAll()
        }
        return 0
    }

    fun updateOneRollCharacteristic(domainModel: DomainRollsCharacteristic): Int? {
        Log.d(TAG, "updateOneRollCharacteristic")
        var result: Int? = -1
        thread(start = true) {
            result = rollsCharacteristicRepositoryImpl.updateOne(domainModel)
        }
        return result
    }


    fun calculateBreedBonuses(displayedBreedList: List<DomainDisplayedBreed>?) {
        Log.d(TAG, "calculates bonuses")
        initializeBonuses()
        displayBonuses()
        Log.d(TAG, "{breedList.size} ${displayedBreedList?.size}")
        displayedBreedList?.forEach {
            Log.d(TAG, "${it.breedName}")
            var breedWithChildren = displayedBreedsRepository.findOneWithChildren(it.breedId)
            breedWithChildren?.characteristics?.forEach { characteristic ->
                if (characteristic.characteristicBonus != null) {
                    var bonus = characteristic.characteristicBonus!!
                    Log.d(
                        TAG,
                        "Bonus ${characteristic.characteristicName}  ${characteristic.characteristicBonus}"
                    )
                    when (characteristic.characteristicName) {
                        CharacteristicsName.APPEARANCE.characteristicName -> appearanceBonus += bonus
                        CharacteristicsName.SIZE.characteristicName -> sizeBonus += bonus
                        CharacteristicsName.STRENGTH.characteristicName -> strengthBonus += bonus
                        CharacteristicsName.POWER.characteristicName -> powerBonus += bonus
                        CharacteristicsName.INTELLIGENCE.characteristicName -> intelligenceBonus += bonus
                        CharacteristicsName.DEXTERITY.characteristicName -> dexterityBonus += bonus
                        CharacteristicsName.CONSTITUTION.characteristicName -> constitutionBonus += bonus
                        CharacteristicsName.EDUCATION.characteristicName -> educationBonus += bonus
                    }
                }
            }
        }

        assignsBonusForAllCharacteristics()
        displayBonuses()
    }

    private fun assignsBonusForAllCharacteristics() {
        if (displayedCharacteristics != null) {
            for (index in 0 until displayedCharacteristics!!.size) {

                var newCharacteristic: DomainRollsCharacteristic = displayedCharacteristics!![index]
                Log.d(TAG, "Characteristic before : $newCharacteristic")
                assignsBonus(newCharacteristic)
                Log.d(TAG, "Characteristic after : $newCharacteristic")
                displayedCharacteristics!![index] = newCharacteristic
            }
            observedCharacteristics?.value = displayedCharacteristics
        }
    }

    private fun assignsBonus(newCharacteristic: DomainRollsCharacteristic) {
        when (newCharacteristic.characteristicName) {
            CharacteristicsName.APPEARANCE.characteristicName -> {
                newCharacteristic.characteristicBonus = appearanceBonus
            }
            CharacteristicsName.POWER.characteristicName -> {
                newCharacteristic.characteristicBonus = powerBonus
            }
            CharacteristicsName.STRENGTH.characteristicName -> {
                newCharacteristic.characteristicBonus = strengthBonus
            }
            CharacteristicsName.DEXTERITY.characteristicName -> {
                newCharacteristic.characteristicBonus = dexterityBonus
            }
            CharacteristicsName.SIZE.characteristicName -> {
                newCharacteristic.characteristicBonus = sizeBonus
            }
            CharacteristicsName.INTELLIGENCE.characteristicName -> {
                newCharacteristic.characteristicBonus = intelligenceBonus
            }
            CharacteristicsName.CONSTITUTION.characteristicName -> {
                newCharacteristic.characteristicBonus = constitutionBonus
            }
            CharacteristicsName.EDUCATION.characteristicName -> {
                newCharacteristic.characteristicBonus = educationBonus
            }
        }
    }

    fun getConstitution(): DomainRollsCharacteristic? {
        var constitution =
            displayedCharacteristics?.find { c -> c.characteristicName == CharacteristicsName.CONSTITUTION.toString() }
        Log.d(TAG, "CONSTITUTION : $constitution")
        return constitution
    }

    fun getStrength(): DomainRollsCharacteristic? {
        return displayedCharacteristics?.find { c -> c.characteristicName == CharacteristicsName.STRENGTH.characteristicName }
    }

    fun getSize(): DomainRollsCharacteristic? {
        return displayedCharacteristics?.find { c -> c.characteristicName == CharacteristicsName.SIZE.characteristicName }
    }

    fun getPower(): DomainRollsCharacteristic? {
        return displayedCharacteristics?.find { c -> c.characteristicName == CharacteristicsName.POWER.characteristicName }
    }


}