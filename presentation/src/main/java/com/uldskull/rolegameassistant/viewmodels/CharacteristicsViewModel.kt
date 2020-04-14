// File CharacteristicViewModel.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollCharacteristic
import com.uldskull.rolegameassistant.repository.breed.BreedsRepository
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository
import com.uldskull.rolegameassistant.repository.characteristic.RollCharacteristicRepository
import com.uldskull.rolegameassistant.useCases.diceRoll.DiceService
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


/**
 *   Class "CharacteristicViewModel" :
 *   TODO: Fill class use.
 **/
class CharacteristicsViewModel(
    application: Application,
    private val rollCharacteristicRepositoryImpl: RollCharacteristicRepository<LiveData<List<DomainRollCharacteristic>>>,
    private val characteristicRepositoryImpl: CharacteristicRepository<LiveData<List<DomainCharacteristic>>>,
    private val breedsRepository: BreedsRepository<LiveData<List<DomainBreed>>>,
    private val diceServiceImpl: DiceService
) : AndroidViewModel(application) {

    var CONSTITUTION_BONUS: Int = 0
    var CHARISMA_BONUS: Int = 0
    var INTELLIGENCE_BONUS: Int = 0
    var SIZE_BONUS: Int = 0
    var DEXTERITY_BONUS: Int = 0
    var STRENGTH_BONUS: Int = 0
    var POWER_BONUS: Int = 0
    var APPEARANCE_BONUS: Int = 0

    companion object {
        private const val TAG = "CharacteristicViewModel"
    }

    private val lock = java.lang.Object()

    var characterBreed: DomainBreed? = null
        set(value) {
            Log.d("NewCharacterViewModel characterBreed", value?.breedName.toString())
            initializeBonuses()
            var children = breedsRepository.findOneWithChildren(value?.breedId)
            children?.characteristics?.forEach {
                Log.d(TAG, it.characteristicName + " BONUS = " + it.characteristicBonus)
                if (it.characteristicBonus != null) {
                    when (it.characteristicName) {
                        CharacteristicsName.APPEARANCE.characteristicName -> APPEARANCE_BONUS += it.characteristicBonus!!
                        CharacteristicsName.INTELLIGENCE.characteristicName -> INTELLIGENCE_BONUS += it.characteristicBonus!!
                        CharacteristicsName.SIZE.characteristicName -> SIZE_BONUS += it.characteristicBonus!!
                        CharacteristicsName.DEXTERITY.characteristicName -> DEXTERITY_BONUS += it.characteristicBonus!!
                        CharacteristicsName.POWER.characteristicName -> POWER_BONUS += it.characteristicBonus!!
                        CharacteristicsName.STRENGTH.characteristicName -> STRENGTH_BONUS += it.characteristicBonus!!
                        CharacteristicsName.CHARISMA.characteristicName -> CHARISMA_BONUS += it.characteristicBonus!!
                        CharacteristicsName.CONSTITUTION.characteristicName -> CONSTITUTION_BONUS += it.characteristicBonus!!
                    }
                }
            }
            rollCharacteristics = rollCharacteristics()

            displayBonuses()
            field = value
        }


    private fun rollCharacteristics(): MutableList<DomainRollCharacteristic> {
        var newList: MutableList<DomainRollCharacteristic> = mutableListOf()
        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.CONSTITUTION?.characteristicName,
                characteristicBonus = CONSTITUTION_BONUS,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )
        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.CHARISMA?.characteristicName,
                characteristicBonus = CHARISMA_BONUS,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )

        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.STRENGTH?.characteristicName,
                characteristicBonus = STRENGTH_BONUS,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )

        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.POWER?.characteristicName,
                characteristicBonus = POWER_BONUS,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )

        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.DEXTERITY?.characteristicName,
                characteristicBonus = DEXTERITY_BONUS,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )
        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.SIZE?.characteristicName,
                characteristicBonus = SIZE_BONUS,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )
        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.INTELLIGENCE?.characteristicName,
                characteristicBonus = INTELLIGENCE_BONUS,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )
        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.APPEARANCE?.characteristicName,
                characteristicBonus = APPEARANCE_BONUS,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )
        return newList
    }

    fun populateRandomRollCharacteristics() {
        var newList: MutableList<DomainRollCharacteristic> = mutableListOf()
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.CONSTITUTION,
                bonus = CONSTITUTION_BONUS
            )
        )
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.CHARISMA,
                bonus = CHARISMA_BONUS
            )
        )
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.STRENGTH,
                bonus = STRENGTH_BONUS
            )
        )
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.POWER,
                bonus = POWER_BONUS
            )
        )
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.DEXTERITY,
                bonus = DEXTERITY_BONUS
            )
        )
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.SIZE,
                bonus = SIZE_BONUS
            )
        )
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.INTELLIGENCE,
                bonus = INTELLIGENCE_BONUS
            )
        )
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.APPEARANCE,
                bonus = APPEARANCE_BONUS
            )
        )

        rollCharacteristics = newList

    }

    fun getDiceRollCharacteristic(
        name: CharacteristicsName,
        bonus: Int?
    ): DomainRollCharacteristic {
        var roll = diceServiceImpl.getOneDiceRollWithANumberOfFace(10)
        var total = roll
        if (bonus != null && roll != null) {
            total = roll + bonus
        }

        return DomainRollCharacteristic(
            characteristicId = null,
            characteristicName = name.characteristicName,
            characteristicBonus = bonus,
            characteristicTotal = total,
            characteristicRoll = roll,
            characteristicMax = 24
        )

    }

    private fun displayBonuses() {
        Log.d(
            TAG, "Bonuses : \n" +
                    "\tapp : $APPEARANCE_BONUS" +
                    "\tcha : $CHARISMA_BONUS" +
                    "\tcon : $CONSTITUTION_BONUS" +
                    "\tdex : $DEXTERITY_BONUS" +
                    "\tint : $INTELLIGENCE_BONUS" +
                    "\tpow : $POWER_BONUS" +
                    "\tsiz : $SIZE_BONUS" +
                    "\tstr : $STRENGTH_BONUS"
        )
    }


    private fun initializeBonuses() {
        APPEARANCE_BONUS = 0
        CHARISMA_BONUS = 0
        CONSTITUTION_BONUS = 0
        DEXTERITY_BONUS = 0
        INTELLIGENCE_BONUS = 0
        POWER_BONUS = 0
        SIZE_BONUS = 0
        STRENGTH_BONUS = 0
    }

    /**
     * All rollCharacteristics from the repository
     */
    /*
    var observedRollCharacteristics = rollCharacteristicRepositoryImpl.getAll()
*/
    var rollCharacteristics = rollCharacteristics()

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

            } catch (e: Exception) {
                Log.e(TAG, "refreshData FAILED")
                e.printStackTrace()
                throw e
            }
        }
    }
/*
    private fun findAllRollCharacteristics(): LiveData<List<DomainRollCharacteristic>>? {
        Log.d(TAG, "findAllRollCharacteristics")
        thread(start = true) {
            observedRollCharacteristics = rollCharacteristicRepositoryImpl.getAll()
        }
        return observedRollCharacteristics
    }

*/
    /*
    fun getRoll(): List<DomainRollCharacteristic> {
        Log.d(TAG, "Roll")
        var newCharacteristics = mutableListOf<DomainRollCharacteristic>()
        observedRollCharacteristics?.value?.forEach {
            Log.d(TAG, "${it.characteristicName} __ ${it.characteristicBonus}")
            var roll = diceServiceImpl.getOneDiceRollWithANumberOfFace(10)
            var total: Int
            if (it.characteristicBonus != null) {
                total = roll!! + it.characteristicBonus!!
            } else {
                total = roll!!
            }
            newCharacteristics.add(
                DomainRollCharacteristic(
                    characteristicId = null,
                    characteristicMax = it.characteristicMax,
                    characteristicName = it.characteristicName,
                    characteristicBonus = it.characteristicBonus,
                    characteristicRoll = roll,
                    characteristicTotal = total
                )
            )
        }
        return newCharacteristics

    }
*/

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
            rollCharacteristicRepositoryImpl.deleteAll()
        }
        return 0
    }

    fun updateOneRollCharacteristic(domainModel: DomainRollCharacteristic): Int? {
        Log.d(TAG, "updateOneRollCharacteristic")
        var result: Int? = -1
        thread(start = true) {
            result = rollCharacteristicRepositoryImpl.updateOne(domainModel)
        }
        return result
    }

}