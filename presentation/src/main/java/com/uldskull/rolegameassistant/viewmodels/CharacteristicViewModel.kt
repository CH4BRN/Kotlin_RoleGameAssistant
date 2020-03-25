// File CharacteristicViewModel.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


/**
 *   Class "CharacteristicViewModel" :
 *   TODO: Fill class use.
 **/
class CharacteristicViewModel(
    application: Application,
    private val characteristicRepositoryImpl: CharacteristicRepository<LiveData<List<DomainCharacteristic>>>
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

    fun saveOne(domainCharacteristic: DomainCharacteristic): Long? {
        var result: Long? = null
        Log.d("CharacteristicViewModel", "saveOne")
        thread(start = true) {
            result = characteristicRepositoryImpl.insertOne(domainCharacteristic)
            Log.d("CharacteristicViewModel", "INSERTED $result")
        }
        return result
    }

    var result = characteristicRepositoryImpl.getAll()

    private fun findAll(): LiveData<List<DomainCharacteristic>>? {
        Log.d("CharacteristicViewModel", "findAll")
        thread(start = true) {
            result = characteristicRepositoryImpl.getAll()
        }

        return result
    }

    fun deleteAll(): Int? {
        Log.d("CharacteristicViewModel", "deleteAll")
        thread(start = true) {
            characteristicRepositoryImpl.deleteAll()
        }
        return 0
    }

}