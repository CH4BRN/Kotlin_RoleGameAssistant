// File CharacteristicViewModel.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.repository.characteristic.BreedCharacteristicRepository
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


/**
 *   Class "CharacteristicViewModel" :
 *   TODO: Fill class use.
 **/
class CharacteristicViewModel(
    application: Application,
    private val breedCharacteristicRepositoryImpl: BreedCharacteristicRepository<LiveData<List<DomainBreedCharacteristic>>>,
    private val characteristicRepositoryImpl: CharacteristicRepository<LiveData<List<DomainCharacteristic>>>
) : AndroidViewModel(application) {

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                result = findAllBreedCharacteristics()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun saveOneBreedCharacteristic(domainBreedCharacteristic: DomainBreedCharacteristic): Long? {
        var result: Long? = null
        Log.d("CharacteristicViewModel", "saveOne")

        result = breedCharacteristicRepositoryImpl.insertOne(domainBreedCharacteristic)
        Log.d("CharacteristicViewModel", "saved $result")
        return result
    }

    private fun getTestDomainBreedCharacteristics(breedId: Long?): List<DomainBreedCharacteristic> {
        return listOf<DomainBreedCharacteristic>(
            DomainBreedCharacteristic(
                characteristicId = null,
                characteristicBonus = 42,
                characteristicName = CharacteristicsName.STRENGTH.characteristicName,
                characteristicBreedId = breedId
            ),
            DomainBreedCharacteristic(
                characteristicId = null,
                characteristicBonus = 42,
                characteristicName = CharacteristicsName.DEXTERITY.characteristicName,
                characteristicBreedId = breedId
            ),
            DomainBreedCharacteristic(
                characteristicId = null,
                characteristicBonus = 42,
                characteristicName = CharacteristicsName.POWER.characteristicName,
                characteristicBreedId = breedId
            )
        )
    }

    fun testInsertMultiple(raceId: Long?): List<Long>? {
        Log.d("testInsertMultiple", "CharacteristicViewModel START")

        var breedCharacteristics = getTestDomainBreedCharacteristics(raceId)

        breedCharacteristics.forEach {
            Log.d("testInsertMultiple", "Characteristic name = " + it.characteristicName)
            Log.d("testInsertMultiple", "Characteristic raceId = " + it.characteristicBreedId)
        }

        var breedCharacteristicsIds: List<Long>? = emptyList()
        Log.d(
            "testInsertMultiple",
            "CharacteristicViewModel raceCharacteristicIds size before = ${breedCharacteristicsIds?.size}"
        )

        try {
            breedCharacteristicsIds = this.saveAllBreedCharacteristics(
                breedCharacteristics
            )
            Log.d(
                "testInsertMultiple",
                "CharacteristicViewModel raceCharacteristicIds size while= ${breedCharacteristicsIds?.size}"
            )
        } catch (e: Exception) {
            Log.e("testInsertMultiple", "saveAllBreedCharacteristics FAILED")
            throw e
        }
        Log.d(
            "testInsertMultiple",
            "CharacteristicViewModel saveAllBreedCharacteristics after= ${breedCharacteristicsIds?.size}"
        )

        Log.d(
            "testInsertMultiple",
            "CharacteristicViewModel findBreedCharacteristicWithId START"
        )

        breedCharacteristicsIds?.forEach {
            var raceCharacteristic = this.findBreedCharacteristicWithId(it)
            if (raceCharacteristic == null) {
                throw Exception("Not found")
            }
            Log.d(
                "testInsertMultiple",
                "raceCharacteristic name = ${raceCharacteristic.characteristicName}"
            )
        }

        return breedCharacteristicsIds

    }


    fun testInsertOne(raceId: Long?): Long? {
        Log.d("testInsertOne", "CharacteristicViewModel START")

        var raceCharacteristic = getTestDomainBreedCharacteristic(raceId)
        Log.d(
            "testInsertOne",
            "CharacteristicViewModel DomainBreedCharacteristic instantiated with raceId : " +
                    "${raceCharacteristic.characteristicBreedId}"
        )
        var raceCharacteristicId: Long? = 0
        Log.d(
            "testInsertOne",
            "CharacteristicViewModel raceCharacteristicId before = ${raceCharacteristicId.toString()}"
        )

        try {
            raceCharacteristicId = this.saveOneBreedCharacteristic(
                raceCharacteristic
            )
            Log.d(
                "testInsertOne",
                "CharacteristicViewModel breedCharacteristicId while= ${raceCharacteristicId.toString()}"
            )
        } catch (e: Exception) {
            Log.e("testInsertOne", "saveOneBreedCharacteristic FAILED")
            throw e
        }
        Log.d(
            "testInsertOne",
            "CharacteristicViewModel breedCharacteristicId after= ${raceCharacteristicId.toString()}"
        )
        Log.d(
            "testInsertOne",
            "CharacteristicViewModel findBreedCharacteristicWithId START"
        )
        var foundBreedCharacteristic: DomainBreedCharacteristic? = null
        try {
            foundBreedCharacteristic =
                this.findBreedCharacteristicWithId(raceCharacteristicId)
        } catch (e: Exception) {
            Log.e(
                "testInsertOne",
                "CharacteristicViewModel findBreedCharacteristicWithId FAILED"
            )
        }

        Log.d(
            "testInsertOne",
            "CharacteristicViewModel foundBreedCharacteristic : "
                    + foundBreedCharacteristic?.characteristicName + " \n" +
                    "Breed id : " + foundBreedCharacteristic?.characteristicBreedId.toString()
        )

        Log.d(
            "testInsertOne",
            "CharacteristicViewModel findBreedCharacteristicWithId END"
        )
        return raceCharacteristicId
    }

    private fun getTestDomainBreedCharacteristic(raceId: Long?): DomainBreedCharacteristic {
        var raceCharacteristic = DomainBreedCharacteristic(
            characteristicId = null,
            characteristicBonus = 42,
            characteristicName = CharacteristicsName.STRENGTH.characteristicName,
            characteristicBreedId = raceId
        )
        return raceCharacteristic
    }

    fun findCharacteristicWithId(characteristicId: Long?): DomainCharacteristic? {
        Log.d("CharacteristicViewModel", "findCharacteristicWithId with id : $characteristicId")

        var result = breedCharacteristicRepositoryImpl.findOneById(characteristicId)
        Log.d(
            "CharacteristicViewModel",
            "findCharacteristicWithId result" + result?.characteristicName
        )

        return result
    }

    fun findBreedCharacteristicWithId(raceCharacteristicId: Long?): DomainBreedCharacteristic? {
        Log.d("CharacteristicViewModel", "findCharacteristicWithId with id : $raceCharacteristicId")

        var result = breedCharacteristicRepositoryImpl.findOneById(raceCharacteristicId)
        Log.d(
            "CharacteristicViewModel",
            "findCharacteristicWithId result " + result?.characteristicName
        )

        return result
    }

    private val lock = java.lang.Object()
    fun saveAllBreedCharacteristics(domainBreedCharacteristics: List<DomainBreedCharacteristic>): List<Long>? =
        synchronized(lock) {
            var result: List<Long>? = null
            Log.d("CharacteristicViewModel", "saveAllBreedCharacteristics")

            result = breedCharacteristicRepositoryImpl.insertAll(domainBreedCharacteristics)
            Log.d("CharacteristicViewModel", "INSERTED $result")

            lock.notifyAll()
            return result
        }

    fun saveAllCharacteristics(domainCharacteristics: List<DomainCharacteristic>): List<Long>? =
        synchronized(lock) {
            var result: List<Long>? = null
            Log.d("CharacteristicViewModel", "saveAllCharacteristics")
            thread(start = true) {
                result = characteristicRepositoryImpl.insertAll(domainCharacteristics)
                Log.d("CharacteristicViewModel", "INSERTED $result")
            }
            lock.notifyAll()
            return result

        }

    var result = breedCharacteristicRepositoryImpl.getAll()

    private fun findAllBreedCharacteristics(): LiveData<List<DomainBreedCharacteristic>>? {
        Log.d("testInsert", "findAllBreedCharacteristics")
        thread(start = true) {
            result = breedCharacteristicRepositoryImpl.getAll()
        }

        return result
    }

    fun deleteAllBreedCharacteristics(): Int? {
        Log.d("testInsert", "deleteAllBreedCharacteristics")
        thread(start = true) {
            breedCharacteristicRepositoryImpl.deleteAll()
        }
        return 0
    }

}