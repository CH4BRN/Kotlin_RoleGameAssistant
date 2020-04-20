// File CharacteristicViewModel.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedCharacteristic
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
    private val rollCharacteristicRepositoryImpl: RollCharacteristicRepository<MutableLiveData<List<DomainRollCharacteristic>>>,
    private val characteristicRepositoryImpl: CharacteristicRepository<LiveData<List<DomainCharacteristic>>>,
    private val breedsRepository: BreedsRepository<LiveData<List<DomainBreed>>>,
    private val diceServiceImpl: DiceService
) : AndroidViewModel(application) {

    var displayedCharacteristics: MutableList<DomainRollCharacteristic>? = mutableListOf()
        set(value) {
            Log.d(TAG, "displayedCharacteristics size : ${value?.size}")


            field = value
        }


    var constitutionBonus: Int = 0
        set(value) {
            Log.d(TAG, "constitutionBonus BEFORE $field")
            field = value
            Log.d(TAG, "constitutionBonus AFTER $field")
        }
    var intelligenceBonus: Int = 0
        set(value) {
            Log.d(TAG, "intelligenceBonus BEFORE $field")
            field = value
            Log.d(TAG, "intelligenceBonus AFTER $field")
        }
    var sizeBonus: Int = 0
        set(value) {
            Log.d(TAG, "sizeBonus BEFORE $field")
            field = value
            Log.d(TAG, "sizeBonus AFTER $field")
        }
    var dexterityBonus: Int = 0
        set(value) {
            Log.d(TAG, "dexterityBonus BEFORE $field")
            field = value
            Log.d(TAG, "dexterityBonus AFTER $field")
        }
    var strengthBonus: Int = 0
        set(value) {
            Log.d(TAG, "strengthBonus BEFORE $field")
            field = value
            Log.d(TAG, "strengthBonus AFTER $field")
        }
    var powerBonus: Int = 0
        set(value) {
            Log.d(TAG, "powerBonus BEFORE $field")
            field = value
            Log.d(TAG, "powerBonus AFTER $field")
        }
    var appearanceBonus: Int = 0
        set(value) {
            Log.d(TAG, "appearanceBonus BEFORE $field")
            field = value
            Log.d(TAG, "appearanceBonus AFTER $field")
        }

    companion object {
        private const val TAG = "CharacteristicViewModel"
    }

    var checkedCharacteristics: MutableList<DomainBreedCharacteristic> = mutableListOf()

    private val lock = java.lang.Object()
    var characterBreeds: MutableList<DomainBreed> = mutableListOf()
        set(value) {
            Log.d(TAG, "set characterBreeds")
            var checked = value.filter { b -> b.breedChecked }
            checked.forEach {
                Log.d(TAG, "Checked : ${it.breedName}")
            }
            calculateBreedBonuses(checked)
            field = value
        }


    private fun rollCharacteristics(): MutableList<DomainRollCharacteristic> {
        Log.d(TAG, "rollCharacteristics")
        var newList: MutableList<DomainRollCharacteristic> = mutableListOf()
        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.CONSTITUTION?.characteristicName,
                characteristicBonus = constitutionBonus,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )

        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.STRENGTH?.characteristicName,
                characteristicBonus = strengthBonus,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )

        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.POWER?.characteristicName,
                characteristicBonus = powerBonus,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )

        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.DEXTERITY?.characteristicName,
                characteristicBonus = dexterityBonus,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )
        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.SIZE?.characteristicName,
                characteristicBonus = sizeBonus,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )
        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.INTELLIGENCE?.characteristicName,
                characteristicBonus = intelligenceBonus,
                characteristicTotal = 0,
                characteristicRoll = 0,
                characteristicMax = 0
            )
        )
        newList.add(
            DomainRollCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.APPEARANCE?.characteristicName,
                characteristicBonus = appearanceBonus,
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
                bonus = constitutionBonus
            )
        )
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.STRENGTH,
                bonus = strengthBonus
            )
        )
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.POWER,
                bonus = powerBonus
            )
        )
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.DEXTERITY,
                bonus = dexterityBonus
            )
        )
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.SIZE,
                bonus = sizeBonus
            )
        )
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.INTELLIGENCE,
                bonus = intelligenceBonus
            )
        )
        newList.add(
            getDiceRollCharacteristic(
                name = CharacteristicsName.APPEARANCE,
                bonus = appearanceBonus
            )
        )

        observedCharacteristics?.value = newList

    }

    fun getDiceRollCharacteristic(
        name: CharacteristicsName,
        bonus: Int?
    ): DomainRollCharacteristic {

        var finalRoll: Int = 0

        when (name.characteristicName) {
            CharacteristicsName.STRENGTH.characteristicName,
            CharacteristicsName.CONSTITUTION.characteristicName,
            CharacteristicsName.DEXTERITY.characteristicName,
            CharacteristicsName.APPEARANCE.characteristicName,
            CharacteristicsName.POWER.characteristicName
            -> {
                diceServiceImpl.getMultipleDiceRollWithANumberOfFace(arrayListOf(6, 6, 6)).forEach {
                    finalRoll += it
                }
            }
            CharacteristicsName.SIZE.characteristicName,
            CharacteristicsName.INTELLIGENCE.characteristicName -> {
                diceServiceImpl.getMultipleDiceRollWithANumberOfFace(arrayListOf(6, 6)).forEach {
                    finalRoll += it
                }
                finalRoll += 6
            }
        }

        var total = finalRoll
        if (bonus != null && finalRoll != null) {
            total = finalRoll + bonus
        }

        return DomainRollCharacteristic(
            characteristicId = null,
            characteristicName = name.characteristicName,
            characteristicBonus = bonus,
            characteristicTotal = total,
            characteristicRoll = finalRoll,
            characteristicMax = 24
        )

    }

    private fun displayBonuses() {
        Log.d(
            TAG, "Bonuses : \n" +
                    "\tapp : $appearanceBonus" +
                    "\tcon : $constitutionBonus" +
                    "\tdex : $dexterityBonus" +
                    "\tint : $intelligenceBonus" +
                    "\tpow : $powerBonus" +
                    "\tsiz : $sizeBonus" +
                    "\tstr : $strengthBonus"
        )
    }

    var observedCharacteristics = rollCharacteristicRepositoryImpl?.getAll()

    private fun initializeBonuses() {
        appearanceBonus = 0
        constitutionBonus = 0
        dexterityBonus = 0
        intelligenceBonus = 0
        powerBonus = 0
        sizeBonus = 0
        strengthBonus = 0
    }

    /**
     * All rollCharacteristics from the repository
     */
    // var rollCharacteristics: MutableList<DomainRollCharacteristic> //= rollCharacteristics()

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
                observedCharacteristics = rollCharacteristicRepositoryImpl?.getAll()

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

    var rollCharacteristics = mutableListOf<DomainRollCharacteristic>()


    fun calculateBreedBonuses(breedList: List<DomainBreed>) {
        Log.d(TAG, "calculates bonuses")
        initializeBonuses()
        displayBonuses()
        Log.d(TAG, "{breedList.size} ${breedList.size}")
        breedList.forEach {
            Log.d(TAG, "${it.breedName}")
            var breedWithChildren = breedsRepository.findOneWithChildren(it.breedId)
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
                    }
                }
            }
        }

        if (displayedCharacteristics != null) {
            for (index in 0 until displayedCharacteristics!!.size) {

                var newCharacteristic: DomainRollCharacteristic = displayedCharacteristics!![index]
                Log.d(TAG, "Characteristic before : $newCharacteristic")
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
                }
                Log.d(TAG, "Characteristic after : $newCharacteristic")
                displayedCharacteristics!![index] = newCharacteristic
            }
            observedCharacteristics?.value = displayedCharacteristics
        }
        displayBonuses()
    }


}