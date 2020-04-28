// File OccupationSkillsViewModel.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill
import com.uldskull.rolegameassistant.repository.skill.OccupationSkillRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 *   Class "OccupationSkillsViewModel" :
 *   TODO: Fill class use.
 **/
class OccupationSkillsViewModel(
    application: Application,
    private val occupationSkillRepositoryImpl: OccupationSkillRepository<LiveData<List<DomainOccupationSkill>>>
) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "OccupationSkillsViewModel"
    }

    init {
        refreshDataFromRepository()
    }

    private val lock = java.lang.Object()

    var occupationSkills = occupationSkillRepositoryImpl.getAll()


    private fun findAllOccupationSkills(): LiveData<List<DomainOccupationSkill>>? {
        Log.d(TAG, "findAllOccupationSkills")
        thread(start = true) {
            occupationSkills = occupationSkillRepositoryImpl.getAll()
        }
        return occupationSkills
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                occupationSkills = findAllOccupationSkills()
            } catch (e: Exception) {
                Log.e(TAG, "refreshDataFromRepository FAILED")
                e.printStackTrace()
                throw e
            }
        }
    }

    fun saveOneOccupationSkill(domainOccupationSkill: DomainOccupationSkill): Long? {
        Log.d(TAG, "saveOneOccupationSKill")

        var result: Long? = occupationSkillRepositoryImpl.insertOne(domainOccupationSkill)
        Log.d(TAG, "saved $result")
        return result
    }

    fun findOccupationSkillWithId(occupationSkillId: Long?): DomainOccupationSkill? {
        Log.d(TAG, "findOccupationSkillWithId : $occupationSkillId")

        var result = occupationSkillRepositoryImpl.findOneById(occupationSkillId)
        return result
    }

    fun saveAllOccupationSkills(domainOccupationSkills: List<DomainOccupationSkill>): List<Long>? =

        synchronized(lock) {
            Log.d(TAG, "saveAllOccupationSkills")

            var result: List<Long>? =
                occupationSkillRepositoryImpl.insertAll(domainOccupationSkills)
            Log.d(TAG, "INSERTED $result")

            lock.notifyAll()
            return result
        }

    fun deleteAllOccupationSkills(): Int? {
        Log.d(TAG, "deleteAllOccupationSkills")
        thread(start = true) {
            occupationSkillRepositoryImpl.deleteAll()
        }
        return 0
    }

}