// File BreedCharacteristicsViewModel.kt
// @Author pierre.antoine - 13/04/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedCharacteristic
import com.uldskull.rolegameassistant.repository.characteristic.BreedCharacteristicRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 *   Class "BreedCharacteristicsViewModel" :
 *   TODO: Fill class use.
 **/
class BreedCharacteristicsViewModel(
    application: Application,
    private val breedCharacteristicRepositoryImpl: BreedCharacteristicRepository<LiveData<List<DomainBreedCharacteristic>>>
) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "BreedCharacteristicsViewModel"


    }

    private val lock = java.lang.Object()
    var breedCharacteristics = breedCharacteristicRepositoryImpl.getAll()

    private fun findAllBreedCharacteristics(): LiveData<List<DomainBreedCharacteristic>>? {
        Log.d(TAG, "findAllBreedCharacteristics")
        thread(start = true) {
            breedCharacteristics = breedCharacteristicRepositoryImpl.getAll()
        }
        return breedCharacteristics
    }

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

    fun saveOneBreedCharacteristic(domainBreedCharacteristic: DomainBreedCharacteristic): Long? {
        Log.d(TAG, "saveOneBreedCharacteristic")

        var result: Long? = breedCharacteristicRepositoryImpl.insertOne(domainBreedCharacteristic)
        Log.d(TAG, "saved $result")
        return result
    }

    fun findBreedCharacteristicWithId(breedCharacteristicId: Long?): DomainBreedCharacteristic? {
        Log.d(TAG, "findCharacteristicWithId with id : $breedCharacteristicId")

        var result = breedCharacteristicRepositoryImpl.findOneById(breedCharacteristicId)
        return result
    }

    fun saveAllBreedCharacteristics(domainBreedCharacteristics: List<DomainBreedCharacteristic>): List<Long>? =

        synchronized(lock) {
            Log.d(TAG, "saveAllBreedCharacteristics")
            Log.d(TAG, "saveAllBreedCharacteristics")

            var result: List<Long>? = breedCharacteristicRepositoryImpl.insertAll(domainBreedCharacteristics)
            Log.d(TAG, "INSERTED $result")

            lock.notifyAll()
            return result
        }

    fun deleteAllBreedCharacteristics(): Int? {
        Log.d(TAG, "deleteAllBreedCharacteristics")
        thread(start = true) {
            breedCharacteristicRepositoryImpl.deleteAll()
        }
        return 0
    }
}