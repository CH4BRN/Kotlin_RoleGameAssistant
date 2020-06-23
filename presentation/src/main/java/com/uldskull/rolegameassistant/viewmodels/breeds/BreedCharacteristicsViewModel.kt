// File BreedCharacteristicsViewModel.kt
// @Author pierre.antoine - 13/04/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels.breeds

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedsCharacteristic
import com.uldskull.rolegameassistant.repository.characteristic.BreedsCharacteristicRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 *   Class "BreedCharacteristicsViewModel" :
 *   Viewmodel for breed's characteristics.
 **/
class BreedCharacteristicsViewModel(
    application: Application,
    private val breedsCharacteristicRepositoryImpl: BreedsCharacteristicRepository<LiveData<List<DomainBreedsCharacteristic>>>
) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "BreedCharacteristicsViewModel"
    }

    /**
     * Lock object for threading
     */
    private val lock = java.lang.Object()

    /**
     * LiveData for breed characteristics
     */
    var breedCharacteristics = breedsCharacteristicRepositoryImpl.getAll()

    /**
     * Gets a live data for breed characteristics
     */
    private fun findAllBreedCharacteristics(): LiveData<List<DomainBreedsCharacteristic>>? {
        Log.d(TAG, "findAllBreedCharacteristics")
        thread(start = true) {
            breedCharacteristics = breedsCharacteristicRepositoryImpl.getAll()
        }
        return breedCharacteristics
    }

    /**
     * Refresh data from repository
     */
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                breedCharacteristics = findAllBreedCharacteristics()
            } catch (e: Exception) {
                Log.e(TAG, "refreshDataFromRepository FAILED")
                e.printStackTrace()
                throw e
            }
        }
    }

    /**
     * Save one breed characteristic
     */
    fun saveOneBreedCharacteristic(domainBreedsCharacteristic: DomainBreedsCharacteristic): Long? {
        Log.d(TAG, "saveOneBreedCharacteristic")

        var result: Long? = breedsCharacteristicRepositoryImpl.insertOne(domainBreedsCharacteristic)
        Log.d(TAG, "saved $result")
        return result
    }

    /**
     * Find one breed characteristic by its id
     */
    fun findBreedCharacteristicWithId(breedCharacteristicId: Long?): DomainBreedsCharacteristic? {
        Log.d(TAG, "findCharacteristicWithId with id : $breedCharacteristicId")

        var result = breedsCharacteristicRepositoryImpl.findOneById(breedCharacteristicId)
        return result
    }

    /**
     * Save all breed characteristics
     */
    fun saveAllBreedCharacteristics(domainBreedsCharacteristics: List<DomainBreedsCharacteristic>): List<Long>? =

        synchronized(lock) {
            Log.d(TAG, "saveAllBreedCharacteristics")
            Log.d(TAG, "saveAllBreedCharacteristics")

            var result: List<Long>? = breedsCharacteristicRepositoryImpl.insertAll(domainBreedsCharacteristics)
            Log.d(TAG, "INSERTED $result")

            lock.notifyAll()
            return result
        }

    /**
     * Delete all breed characteristics.
     */
    fun deleteAllBreedCharacteristics(): Int? {
        Log.d(TAG, "deleteAllBreedCharacteristics")
        thread(start = true) {
            breedsCharacteristicRepositoryImpl.deleteAll()
        }
        return 0
    }
}