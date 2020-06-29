// File BreedsViewModel.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels.breeds

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.repository.breed.DisplayedBreedsRepository
import kotlinx.coroutines.Dispatchers
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
                observableRepositoryBreeds = findAll()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    /**
     *  Observable repository breeds
     */
    var observableRepositoryBreeds = displayedBreedRepositoryImpl.getAll()

    /**
     * Mutable observable selected breed
     */
    var observableMutableSelectedBreed = MutableLiveData<DomainDisplayedBreed>()

    /**
     * Observable character's breeds ids
     */
    var observableSelectedBreeds = MutableLiveData<List<Long?>>()

    /**
     * Gets a live data for repository breeds
     */
    private fun findAll(): LiveData<List<DomainDisplayedBreed>>? {
        Log.d(TAG, "findAll")
        thread(start = true) {
            observableRepositoryBreeds = displayedBreedRepositoryImpl.getAll()
        }
        return observableRepositoryBreeds
    }

    /**
     * Save one breed
     */
    fun saveOne(domainDisplayedBreed: DomainDisplayedBreed): Long? {
        Log.d(TAG, "saveOne")
        if (domainDisplayedBreed == null) {
            throw Exception("Breed is null")
        }
        var result: Long? = null
        viewModelScope.launch(Dispatchers.IO) {
            result = displayedBreedRepositoryImpl.insertOne(domainDisplayedBreed)
        }

        Log.d("DEBUG$TAG", "INSERTED $result")
        refreshDataFromRepository()
        return result
    }

    fun updateOne(domainDisplayedBreed: DomainDisplayedBreed): Int? {
        if (domainDisplayedBreed == null) {
            throw Exception("Breed is null")
        }
        var result: Int? = null
        viewModelScope.launch(Dispatchers.IO) {
            result = displayedBreedRepositoryImpl.updateOne(domainDisplayedBreed)
        }
        Log.d("DEBUG$TAG", "UPDATED $result")
        refreshDataFromRepository()
        return result

    }

    /**
     * Lock object for threading
     */
    private val lock = Object()

    /**
     * Save all breeds
     */
    fun saveAll(domainDisplayedBreed: List<DomainDisplayedBreed>): List<Long>? =
        synchronized(lock) {
            Log.d(TAG, "saveAll")
            var result: List<Long>? = null
            viewModelScope.launch {
                result = displayedBreedRepositoryImpl.insertAll(domainDisplayedBreed)
            }
            Log.d(TAG, "INSERTED $result")
            lock.notifyAll()
            return result
        }
}