// File RaceViewModel.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.DomainRace
import com.uldskull.rolegameassistant.repository.race.RaceRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 *   Class "RaceViewModel" :
 *   TODO: Fill class use.
 **/
class RacesViewModel(
    application: Application,
    private val raceRepositoryImpl: RaceRepository<LiveData<List<DomainRace>>>
) : AndroidViewModel(application) {

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                result = findAll()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun findRaceWithCharacteristics() {
        Log.d("RacesViewModel", "findRaceWithCharacteristics")

        var result = raceRepositoryImpl.findOneWithChildren()

        Log.d("RacesViewModel", "result" + result.raceName)
    }

    fun findRaceWithId(raceId: Long?): DomainRace? {
        Log.d("RacesViewModel", "findRaceWithId with id : $raceId")

        var result = raceRepositoryImpl.findOneById(raceId)
        Log.d("RacesViewModel", "findRaceWithId result" + result?.raceName)

        return result
    }


    var result = raceRepositoryImpl.getAll()

    private fun findAll(): LiveData<List<DomainRace>>? {
        Log.d("RaceViewModel", "findAll")
        thread(start = true) {
            result = raceRepositoryImpl.getAll()
        }
        return result
    }


    fun testInsert(): Long? {
        Log.d("testInsert", "RacesViewModel START")

        var warriorRace = DomainRace(
            raceId = null,
            raceDescription = "Strength bonus",
            raceName = "Warrior"
        )
        Log.d("testInsert", "RacesViewModel warriorRace instantiated")
        var raceId: Long? = 0
        Log.d("testInsert", "RacesViewModel raceId before = ${raceId.toString()}")

        try {
            raceId = this.saveOne(
                warriorRace
            )
            Log.d("testInsert", "RacesViewModel raceId while= ${raceId.toString()}")
        } catch (e: Exception) {
            Log.e("testInsert", "RacesViewModel FAILED")
            throw e
        }
        Log.d("testInsert", "RacesViewModel raceId after= ${raceId.toString()}")

        var race = this.findRaceWithId(raceId)

        Log.d("testInsert", "RacesViewModel found race : ${race?.raceName}")

        Log.d("testInsert", "RacesViewModel END")
        return raceId
    }


    fun saveOne(domainRace: DomainRace): Long? {
        var result: Long? = null
        Log.d("RaceViewModel", "saveOne")
        result = raceRepositoryImpl.insertOne(domainRace)
        Log.d("RaceViewModel", "INSERTED $result")
        return result
    }

    private val lock = java.lang.Object()

    fun saveAll(domainRace: List<DomainRace>): List<Long>? = synchronized(lock) {
        var result: List<Long>? = emptyList()
        Log.d("RaceViewModel", "saveAll")

        result = raceRepositoryImpl.insertAll(domainRace)
        Log.d("RaceViewModel", "INSERTED $result")
        lock.notifyAll()
        return result


    }


// TODO : Fill class.
}