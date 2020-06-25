// File JobsViewModel.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels.occupations

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.models.skill.DomainSkillToCheck
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

    /**
     * Refresh data from repository
     */
    fun refreshDataFromRepository() {
        Log.d(TAG, "refreshDataFromRepository")
        viewModelScope.launch {
            try {
                findAll()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    /**
     * Find all data from repository
     */
    private fun findAll(): LiveData<List<DomainOccupation>>? {
        Log.d(TAG, "findAll")
        thread(start = true) {
            repositoryOccupations = occupationsRepositoryImpl.getAll()
        }
        return repositoryOccupations
    }

    /**
     * Find one entity with its children
     */
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

    /**
     * Insert one occupation
     */
    fun insertOccupation(domainOccupation: DomainOccupation): Long? {
        if (domainOccupation == null) {
            return 0
        } else {
            return when {
                domainOccupation.occupationId == null -> {
                    occupationsRepositoryImpl.insertOne(domainOccupation)
                }
                occupationsRepositoryImpl.findOneById(domainOccupation.occupationId) == null -> {
                    occupationsRepositoryImpl.insertOne(domainOccupation)
                }
                else -> {
                    occupationsRepositoryImpl.updateOne(domainOccupation)
                    return domainOccupation.occupationId
                }
            }
        }
    }

    /**
     * gets occupation by its id
     */
    fun getOccupationById(id: Long?): DomainOccupation? {
        return if(id != null){
            occupationsRepositoryImpl.findOneById(id)
        }else{
            null
        }
    }

    /**
     * Delete occupation
     */
    fun deleteOccupation(currentOccupationToEdit: DomainOccupation):Int {
        if(currentOccupationToEdit == null){
            throw Exception("Occupation is null")
        }
        return occupationsRepositoryImpl.deleteOne(currentOccupationToEdit)
    }

    /**
     * Current occupation to edit
     */
    var currentOccupationToEdit: DomainOccupation? = null

    /**
     * Observable occupation income
     */
    var selectedOccupationIncome: MutableLiveData<String>? = MutableLiveData()

    /**
     * Observable selected character skills
     */
    val selectedCharacterOccupationsSkills = MutableLiveData<List<Long?>>()

    /**
     * Observable occupation contacts
     */
    var selectedOccupationContacts: MutableLiveData<String>? = MutableLiveData()

    /**
     * Observable occupation special
     */
    var selectedOccupationSpecial: MutableLiveData<String>? = MutableLiveData()

    /**
     * Observable occupation index
     */
    var selectedOccupationIndex: MutableLiveData<Int>? = MutableLiveData()

    /**
     * Observable selected occupation
     */
    var selectedOccupation: MutableLiveData<DomainOccupation>? = MutableLiveData()

    /**
     * Observable repository occupations
     */
    var repositoryOccupations = occupationsRepositoryImpl.getAll()

    /**
     * Observable displayed occupations
     */
    var displayedOccupations = MutableLiveData<List<String>>()

    /**
     * Observable occupation skills
     */
    var observedOccupationsSkills: MutableLiveData<List<DomainSkillToCheck>>? =
        MutableLiveData()
}