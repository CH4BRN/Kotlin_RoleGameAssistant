// File JobsViewModel.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels.occupations

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill
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
                repositoryOccupations = findAll()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun findAll(): LiveData<List<DomainOccupation>>? {
        Log.d(TAG, "findAll")
        thread(start = true) {
            repositoryOccupations = occupationsRepositoryImpl.getAll()
        }
        return repositoryOccupations
    }

    fun findOneWithChildren(id: Long?): DomainOccupationWithSkills? {
        Log.d(TAG, "findOneWithChildren")
        try {
            return occupationsRepositoryImpl.findOneWithChildren(id)
        } catch (e: Exception) {
            Log.e(TAG, "findOneWithChildren FAILED")
            e.printStackTrace()
            throw e
        }
    }

    var selectedOccupationIncome: MutableLiveData<String>? = MutableLiveData()
    var selectedOccupationContacts: MutableLiveData<String>? = MutableLiveData()
    var selectedOccupationSpecial: MutableLiveData<String>? = MutableLiveData()
    var selectedOccupationIndex: MutableLiveData<Int>? = MutableLiveData()
    var selectedOccupation: MutableLiveData<DomainOccupation>? = MutableLiveData()
    var repositoryOccupations = occupationsRepositoryImpl.getAll()
    var displayedOccupations = MutableLiveData<List<String?>>()
    var observedOccupationsSkills: MutableLiveData<List<DomainOccupationSkill>>? =
        MutableLiveData()
// TODO : Fill class.
}