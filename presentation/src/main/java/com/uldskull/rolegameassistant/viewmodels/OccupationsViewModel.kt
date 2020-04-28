// File JobsViewModel.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.repository.occupations.OccupationsRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 *   Class "JobsViewModel" :
 *   TODO: Fill class use.
 **/
class OccupationsViewModel(
    application: Application,
    private val occupationsRepositoryImpl: OccupationsRepository<LiveData<List<DomainOccupation>>>
) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "OccupationsViewModel"
    }

    init {
        Log.d(TAG, "Init")
        refreshDataFromRepository()
    }


    private fun refreshDataFromRepository() {
        Log.d(TAG, "refreshDataFromRepository")
        viewModelScope.launch {
            try {
                observedOccupations = findAll()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun findAll(): LiveData<List<DomainOccupation>>? {
        Log.d(TAG, "findAll")
        thread(start = true) {
            observedOccupations = occupationsRepositoryImpl.getAll()
        }
        return observedOccupations
    }

    fun findOneWithChildren(id: Long?): DomainOccupationWithSkills? {
        Log.d(TAG, "findOneWithChildren")
        try {
            var occupationWithSkills = occupationsRepositoryImpl?.findOneWithChildren(id)
            if (occupationWithSkills != null) {
                this.selectedOccupation = occupationWithSkills.occupation
            }
            return occupationWithSkills
        } catch (e: Exception) {
            Log.e(TAG, "findOneWithChildren FAILED")
            e.printStackTrace()
            throw e
        }
    }

    var selectedOccupation: DomainOccupation? = null
        set(value) {
            Log.d(TAG, "Selected occupation : ${value}")
            displayedOccupations.forEach() { domainOccupation ->
                Log.d(TAG, domainOccupation?.occupationName)
            }
            if (value != null) {
                selectedOccupationIndex = displayedOccupations.indexOfFirst { domainOccupation -> domainOccupation?.occupationId == value.occupationId }
            }

            field = value
        }

    var selectedOccupationIndex = -1
        set(value) {
            Log.d(TAG, "selectedOccupationIndex : ${value}")
            field = value
        }
    var observedOccupations = occupationsRepositoryImpl.getAll()
    var displayedOccupations = mutableListOf<DomainOccupation?>()
// TODO : Fill class.
}