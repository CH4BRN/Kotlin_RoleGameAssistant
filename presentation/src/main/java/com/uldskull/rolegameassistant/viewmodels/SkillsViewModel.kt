// File SkillsViewModel.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.skill.DomainSkillToCheck
import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.repository.skill.SkillToCheckRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 *   Class "SkillsViewModel" :
 *   Designed to store and manage UI-related data in a lifecycle conscious way.
 *   The ViewModel class allows data to survive configuration changes such as
 *   screen rotations.
 **/
class SkillsViewModel(
    application: Application,
    private val skillToCheckToCheckRepository: SkillToCheckRepository<LiveData<List<DomainSkillToCheck>>>
) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "SkillsViewModel"
    }

    init {
        Log.d(TAG, "Init")
        refreshDataFromRepository()
    }

    /**
     * Character occupation skills
     */
    var characterOccupationSkills: List<DomainSkillToFill>? = mutableListOf()

    /**
     * Character hobby skills
     */
    var characterHobbySkills: List<DomainSkillToFill>? = mutableListOf()

    /**
     * Repository skills to check
     */
    var repositorySkillsToCheck: LiveData<List<DomainSkillToCheck>>? = skillToCheckToCheckRepository.getAll()

    /**
     * Mutable skills to check
     */
    var mutableSkillsToCheck:MutableLiveData<List<DomainSkillToCheck>>? = MutableLiveData()

    /**
     * Refresh data from repository
     */
    private fun refreshDataFromRepository(){
        Log.d(TAG, "refreshDataFromRepository")
        viewModelScope.launch {
            try {
                repositorySkillsToCheck = findAll()
            }catch (e:Exception){
                Log.e("ERROR", "skillToCheckRepository?.getAll() failed")
                e.printStackTrace()
                throw e
            }
        }
    }

    /**
     * Find all data from repository
     */
    private fun findAll():LiveData<List<DomainSkillToCheck>>?{
        Log.d(TAG, "findAll")
        thread(start = true){
            repositorySkillsToCheck = skillToCheckToCheckRepository.getAll()
        }
        return repositorySkillsToCheck
    }

    /**
     * observable hobbies skills
     */
    var hobbiesSkills = MutableLiveData<List<DomainSkillToCheck?>>()

    /**
     * observable hobby skills
     */
    var hobbySkills = MutableLiveData<List<DomainSkillToFill>>()


}