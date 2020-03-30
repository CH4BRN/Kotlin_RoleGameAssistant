// File CharacteristicViewModel.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRaceCharacteristic
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository
import com.uldskull.rolegameassistant.repository.characteristic.RaceCharacteristicRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


/**
 *   Class "CharacteristicViewModel" :
 *   TODO: Fill class use.
 **/
class CharacteristicViewModel(
    application: Application,
    private val raceCharacteristicRepositoryImpl: RaceCharacteristicRepository<LiveData<List<DomainRaceCharacteristic>>>,
    private val characteristicRepositoryImpl: CharacteristicRepository<LiveData<List<DomainCharacteristic>>>
) : AndroidViewModel(application) {

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                result = findAllRaceCharacteristics()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun saveOneRaceCharacteristic(domainRaceCharacteristic: DomainRaceCharacteristic): Long? {
        var result: Long? = null
        Log.d("CharacteristicViewModel", "saveOne")

        result = raceCharacteristicRepositoryImpl.insertOne(domainRaceCharacteristic)
        Log.d("CharacteristicViewModel", "saved $result")
        return result
    }

    private fun getTestDomainRaceCharacteristics(raceId: Long?): List<DomainRaceCharacteristic> {
        return listOf<DomainRaceCharacteristic>(
            DomainRaceCharacteristic(
                characteristicId = null,
                characteristicBonus = 42,
                characteristicName = CharacteristicsName.STRENGTH.characteristicName,
                characteristicRaceId = raceId
            ),
            DomainRaceCharacteristic(
                characteristicId = null,
                characteristicBonus = 42,
                characteristicName = CharacteristicsName.DEXTERITY.characteristicName,
                characteristicRaceId = raceId
            ),
            DomainRaceCharacteristic(
                characteristicId = null,
                characteristicBonus = 42,
                characteristicName = CharacteristicsName.POWER.characteristicName,
                characteristicRaceId = raceId
            )
        )
    }

    fun testInsertMultiple(raceId: Long?): List<Long>? {
        Log.d("testInsertMultiple", "CharacteristicViewModel START")

        var raceCharacteristics = getTestDomainRaceCharacteristics(raceId)

        raceCharacteristics.forEach {
            Log.d("testInsertMultiple", "Characteristic name = " + it.characteristicName)
            Log.d("testInsertMultiple", "Characteristic raceId = " + it.characteristicRaceId)
        }

        var raceCharacteristicsIds: List<Long>? = emptyList()
        Log.d(
            "testInsertMultiple",
            "CharacteristicViewModel raceCharacteristicIds size before = ${raceCharacteristicsIds?.size}"
        )

        try {
            raceCharacteristicsIds = this.saveAllRaceCharacteristics(
                raceCharacteristics
            )
            Log.d(
                "testInsertMultiple",
                "CharacteristicViewModel raceCharacteristicIds size while= ${raceCharacteristicsIds?.size}"
            )
        } catch (e: Exception) {
            Log.e("testInsertMultiple", "saveAllRaceCharacteristics FAILED")
            throw e
        }
        Log.d(
            "testInsertMultiple",
            "CharacteristicViewModel saveAllRaceCharacteristics after= ${raceCharacteristicsIds?.size}"
        )

        Log.d(
            "testInsertMultiple",
            "CharacteristicViewModel findRaceCharacteristicWithId START"
        )

        raceCharacteristicsIds?.forEach {
            var raceCharacteristic = this.findRaceCharacteristicWithId(it)
            if (raceCharacteristic == null) {
                throw Exception("Not found")
            }
            Log.d(
                "testInsertMultiple",
                "raceCharacteristic name = ${raceCharacteristic.characteristicName}"
            )
        }

        return raceCharacteristicsIds

    }


    fun testInsertOne(raceId: Long?): Long? {
        Log.d("testInsertOne", "CharacteristicViewModel START")

        var raceCharacteristic = getTestDomainRaceCharacteristic(raceId)
        Log.d(
            "testInsertOne",
            "CharacteristicViewModel DomainRaceCharacteristic instantiated with raceId : " +
                    "${raceCharacteristic.characteristicRaceId}"
        )
        var raceCharacteristicId: Long? = 0
        Log.d(
            "testInsertOne",
            "CharacteristicViewModel raceCharacteristicId before = ${raceCharacteristicId.toString()}"
        )

        try {
            raceCharacteristicId = this.saveOneRaceCharacteristic(
                raceCharacteristic
            )
            Log.d(
                "testInsertOne",
                "CharacteristicViewModel raceCharacteristicId while= ${raceCharacteristicId.toString()}"
            )
        } catch (e: Exception) {
            Log.e("testInsertOne", "saveOneRaceCharacteristic FAILED")
            throw e
        }
        Log.d(
            "testInsertOne",
            "CharacteristicViewModel raceCharacteristicId after= ${raceCharacteristicId.toString()}"
        )
        Log.d(
            "testInsertOne",
            "CharacteristicViewModel findRaceCharacteristicWithId START"
        )
        var foundRaceCharacteristic: DomainRaceCharacteristic? = null
        try {
            foundRaceCharacteristic =
                this.findRaceCharacteristicWithId(raceCharacteristicId)
        } catch (e: Exception) {
            Log.e(
                "testInsertOne",
                "CharacteristicViewModel findRaceCharacteristicWithId FAILED"
            )
        }

        Log.d(
            "testInsertOne",
            "CharacteristicViewModel foundRaceCharacteristic : "
                    + foundRaceCharacteristic?.characteristicName + " \n" +
                    "Race id : " + foundRaceCharacteristic?.characteristicRaceId.toString()
        )

        Log.d(
            "testInsertOne",
            "CharacteristicViewModel findRaceCharacteristicWithId END"
        )
        return raceCharacteristicId
    }

    private fun getTestDomainRaceCharacteristic(raceId: Long?): DomainRaceCharacteristic {
        var raceCharacteristic = DomainRaceCharacteristic(
            characteristicId = null,
            characteristicBonus = 42,
            characteristicName = CharacteristicsName.STRENGTH.characteristicName,
            characteristicRaceId = raceId
        )
        return raceCharacteristic
    }

    fun findCharacteristicWithId(characteristicId: Long?): DomainCharacteristic? {
        Log.d("CharacteristicViewModel", "findCharacteristicWithId with id : $characteristicId")

        var result = raceCharacteristicRepositoryImpl.findOneById(characteristicId)
        Log.d(
            "CharacteristicViewModel",
            "findCharacteristicWithId result" + result?.characteristicName
        )

        return result
    }

    fun findRaceCharacteristicWithId(raceCharacteristicId: Long?): DomainRaceCharacteristic? {
        Log.d("CharacteristicViewModel", "findCharacteristicWithId with id : $raceCharacteristicId")

        var result = raceCharacteristicRepositoryImpl.findOneById(raceCharacteristicId)
        Log.d(
            "CharacteristicViewModel",
            "findCharacteristicWithId result " + result?.characteristicName
        )

        return result
    }

    private val lock = java.lang.Object()
    fun saveAllRaceCharacteristics(domainRaceCharacteristics: List<DomainRaceCharacteristic>): List<Long>? =
        synchronized(lock) {
            var result: List<Long>? = null
            Log.d("CharacteristicViewModel", "saveAllRaceCharacteristics")

            result = raceCharacteristicRepositoryImpl.insertAll(domainRaceCharacteristics)
            Log.d("CharacteristicViewModel", "INSERTED $result")

            lock.notifyAll()
            return result
        }

    fun saveAllCharacteristics(domainCharacteristics: List<DomainCharacteristic>): List<Long>? =
        synchronized(lock) {
            var result: List<Long>? = null
            Log.d("CharacteristicViewModel", "saveAllRaceCharacteristics")
            thread(start = true) {
                result = characteristicRepositoryImpl.insertAll(domainCharacteristics)
                Log.d("CharacteristicViewModel", "INSERTED $result")
            }
            lock.notifyAll()
            return result

        }

    var result = raceCharacteristicRepositoryImpl.getAll()

    private fun findAllRaceCharacteristics(): LiveData<List<DomainRaceCharacteristic>>? {
        Log.d("testInsert", "findAllRaceCharacteristics")
        thread(start = true) {
            result = raceCharacteristicRepositoryImpl.getAll()
        }

        return result
    }

    fun deleteAllRaceCharacteristics(): Int? {
        Log.d("testInsert", "deleteAllRaceCharacteristics")
        thread(start = true) {
            raceCharacteristicRepositoryImpl.deleteAll()
        }
        return 0
    }

}