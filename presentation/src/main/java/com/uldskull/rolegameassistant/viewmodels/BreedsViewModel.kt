// File BreedsViewModel.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.models.character.breed.DomainBreedWithCharacteristics
import com.uldskull.rolegameassistant.repository.breed.BreedsRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 *   Class "BreedsViewModel" :
 *   TODO: Fill class use.
 **/
class BreedsViewModel(
    application: Application,
    private val breedRepositoryImpl: BreedsRepository<LiveData<List<DomainBreed>>>
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

    fun findBreedsWithCharacteristics(): List<DomainBreedWithCharacteristics> {
        Log.d("RacesViewModel", "findBreedsWithCharacteristics")
        var result = breedRepositoryImpl.findAllWithChildren()

        return result


    }

    fun findBreedWithCharacteristics(id: Long?): DomainBreedWithCharacteristics? {
        Log.d("RacesViewModel", "findBreedWithCharacteristics")

        // var result = breedRepositoryImpl.findOneWithChildren(id)

        //  Log.d("RacesViewModel", "result" + result?.breed?.breedName)
        //Log.d("RacesViewModel", "result" + result?.characteristics?.first()?.characteristicName)
        return null // result
    }

    /**
     * Find one breed by its ID
     */
    fun findBreedWithId(raceId: Long?): DomainBreed? {
        Log.d("BreedsViewModel", "findBreedWithId with id : $raceId")

        var result = breedRepositoryImpl.findOneById(raceId)
        Log.d("BreedsViewModel", "findBreedWithId result" + result?.breedName)

        return result
    }


    var result = breedRepositoryImpl.getAll()

    private fun findAll(): LiveData<List<DomainBreed>>? {
        Log.d("BreedsViewModel", "findAll")
        thread(start = true) {
            result = breedRepositoryImpl.getAll()
        }
        return result
    }





    fun saveOne(domainBreed: DomainBreed): Long? {
        var result: Long? = null
        Log.d("BreedsViewModel", "saveOne")
        result = breedRepositoryImpl.insertOne(domainBreed)
        Log.d("BreedsViewModel", "INSERTED $result")
        return result
    }

    private val lock = java.lang.Object()

    fun saveAll(domainBreed: List<DomainBreed>): List<Long>? = synchronized(lock) {
        var result: List<Long>? = emptyList()
        Log.d("BreedsViewModel", "saveAll")

        result = breedRepositoryImpl.insertAll(domainBreed)
        Log.d("BreedsViewModel", "INSERTED $result")
        lock.notifyAll()
        return result


    }
}