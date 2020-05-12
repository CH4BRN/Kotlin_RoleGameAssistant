// File BreedsViewModel.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels.breeds

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

    companion object {
        private const val TAG = "BreedsViewModel"
    }

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        Log.d(TAG, "refreshDataFromRepository")
        viewModelScope.launch {
            try {
                observedBreeds = findAll()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun findBreedsWithCharacteristics(): List<DomainBreedWithCharacteristics> {
        Log.d(TAG, "findBreedsWithCharacteristics")
        var result = breedRepositoryImpl.findAllWithChildren()

        return result


    }

    fun findBreedWithCharacteristics(id: Long?): DomainBreedWithCharacteristics? {
        Log.d(TAG, "findBreedWithCharacteristics")

        // var result = breedRepositoryImpl.findOneWithChildren(id)

        //  Log.d("RacesViewModel", "result" + result?.breed?.breedName)
        //Log.d("RacesViewModel", "result" + result?.characteristics?.first()?.characteristicName)
        return null // result
    }

    /**
     * Find one breed by its ID
     */
    fun findBreedWithId(raceId: Long?): DomainBreed? {
        Log.d(TAG, "findBreedWithId with id : $raceId")

        var result = breedRepositoryImpl.findOneById(raceId)
        Log.d(TAG, "findBreedWithId result" + result?.breedName)

        return result
    }


    var displayedBreeds: MutableList<DomainBreed> = mutableListOf()
        set(value) {
            Log.d(TAG, "displayedBreeds size :  ${value.size}")
            field = value
        }

    var observedBreeds = breedRepositoryImpl.getAll()

    private fun findAll(): LiveData<List<DomainBreed>>? {
        Log.d(TAG, "findAll")
        thread(start = true) {
            observedBreeds = breedRepositoryImpl.getAll()
        }
        return observedBreeds
    }

    fun saveOne(domainBreed: DomainBreed): Long? {
        Log.d(TAG, "saveOne")
        var result: Long? = breedRepositoryImpl.insertOne(domainBreed)
        Log.d(TAG, "INSERTED $result")
        return result
    }

    private val lock = java.lang.Object()

    fun saveAll(domainBreed: List<DomainBreed>): List<Long>? = synchronized(lock) {
        Log.d(TAG, "saveAll")

        var result: List<Long>? = breedRepositoryImpl.insertAll(domainBreed)
        Log.d(TAG, "INSERTED $result")
        lock.notifyAll()
        return result
    }
}