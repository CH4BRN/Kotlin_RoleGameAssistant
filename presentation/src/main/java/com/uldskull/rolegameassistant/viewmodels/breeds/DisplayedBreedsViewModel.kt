// File BreedsViewModel.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels.breeds

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreedWithCharacteristics
import com.uldskull.rolegameassistant.repository.breed.DisplayedBreedsRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 *   Class "BreedsViewModel" :
 *   ViewModel for displayed breeds
 **/
class DisplayedBreedsViewModel(
    application: Application,
    private val displayedBreedRepositoryImpl: DisplayedBreedsRepository<LiveData<List<DomainDisplayedBreed>>>
) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "DisplayedBreedsViewModel"
    }

    init {
        refreshDataFromRepository()
    }

    /**
     * Refresh data from repository
     */
    private fun refreshDataFromRepository() {
        Log.d(TAG, "refreshDataFromRepository")
        viewModelScope.launch {
            try {
                observedRepositoryBreeds = findAll()
            } catch (e: Exception) {
                throw e
            }
        }
    }


    /**
     * Find one breed by its ID
     */
    fun findBreedWithId(breedId: Long?): DomainDisplayedBreed? {
        Log.d(TAG, "findBreedWithId with id : $breedId")

        var result = displayedBreedRepositoryImpl.findOneById(breedId)
        Log.d(TAG, "findBreedWithId result" + result?.breedName)

        return result
    }

    /**
     *  Observable repository breeds
     */
    var observedRepositoryBreeds = displayedBreedRepositoryImpl.getAll()

    /**
     *  Mutable observable breeds
     */
    var observedMutableBreeds = MutableLiveData<List<DomainDisplayedBreed>>()

    /**
     * Gets a live data for repository breeds
     */
    private fun findAll(): LiveData<List<DomainDisplayedBreed>>? {
        Log.d(TAG, "findAll")
        thread(start = true) {
            observedRepositoryBreeds = displayedBreedRepositoryImpl.getAll()
        }
        return observedRepositoryBreeds
    }

    /**
     * Save one breed
     */
    fun saveOne(domainDisplayedBreed: DomainDisplayedBreed): Long? {
        Log.d(TAG, "saveOne")
        var result: Long? = displayedBreedRepositoryImpl.insertOne(domainDisplayedBreed)
        Log.d(TAG, "INSERTED $result")
        return result
    }

    /**
     * Lock object for threading
     */
    private val lock = java.lang.Object()

    /**
     * Save all breeds
     */
    fun saveAll(domainDisplayedBreed: List<DomainDisplayedBreed>): List<Long>? =
        synchronized(lock) {
            Log.d(TAG, "saveAll")

            var result: List<Long>? =
                displayedBreedRepositoryImpl.insertAll(domainDisplayedBreed)
            Log.d(TAG, "INSERTED $result")
            lock.notifyAll()
            return result
        }
}