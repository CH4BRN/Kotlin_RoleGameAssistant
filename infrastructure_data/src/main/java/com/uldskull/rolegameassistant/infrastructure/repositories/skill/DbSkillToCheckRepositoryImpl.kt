// File DbOccupationSkillRepositoryImpl.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.skill

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.skill.DbSkillToCheckDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbSkillToCheck
import com.uldskull.rolegameassistant.models.skill.DomainSkillToCheck
import com.uldskull.rolegameassistant.repository.skill.SkillToCheckRepository

/**
 *   Class "DbOccupationSkillRepositoryImpl" :
 *   Repository for skills to check
 **/
class DbSkillToCheckRepositoryImpl(
    /**
     * skill dao
     */
    private val dbSkillToCheckDao: DbSkillToCheckDao
) :
    SkillToCheckRepository<LiveData<List<DomainSkillToCheck>>> {

    companion object {
        private const val TAG = "DbOccupationSkillRepositoryImpl"
    }
    /**
     * Converts a list of database entities into domain entities
     */
    private fun List<DbSkillToCheck>.asDomainModel(): List<DomainSkillToCheck> {
        Log.d(TAG, "asDomainModel")
        return map {
            DomainSkillToCheck(
                skillId = it.skillId,
                skillName = it.skillName,
                skillDescription = it.skillDescription,
                skillMax = it.skillMax,
                skillIsChecked = it.skillIsChecked,
                skillBase = it.skillBase
            )
        }
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainSkillToCheck>> {
        Log.d(TAG, "getAll")
        try {
            return Transformations.map(dbSkillToCheckDao.getOccupationSkills()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getAll FAILED ! ")
            throw e
        }
    }



    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override suspend fun insertAll(all: List<DomainSkillToCheck>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if (all != null && all.isNotEmpty()) {
            try {
                val result = dbSkillToCheckDao.insert(all.map { s ->
                    DbSkillToCheck.from(
                        s
                    )
                })
                Log.d(TAG, "insertAll RESULT = ${result.size}")
                return result
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        } else {
            Log.d(TAG, "insertAll RESULT = 0")
            return emptyList()
        }
    }


    /** Insert one entity  -  it can return a long, which is the new rowId for the inserted item.*/
    override suspend fun insertOne(one: DomainSkillToCheck?): Long? {
        Log.d(TAG, "insertOne $one")
        return if (one != null) {
            try {
                val result =
                    dbSkillToCheckDao.insert(
                        DbSkillToCheck.from(
                            one
                        )
                    )
                Log.d(TAG, "insertOne RESULT = $result")
                result
            } catch (e: Exception) {
                Log.e(TAG, "insertOne FAILED")
                e.printStackTrace()
                throw e
            }
        } else {
            -1
        }
    }

    /** Delete all entities **/
    override suspend fun deleteAll(): Int {
        Log.d(TAG, "deleteAll")
        try {
            return dbSkillToCheckDao.deleteAllSkills()
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /**  Update one entity  **/
    override suspend fun updateOne(one: DomainSkillToCheck?): Int? {
        Log.d(TAG, "updateOne")
        try {
            dbSkillToCheckDao.update(DbSkillToCheck.from(one))
        } catch (e: Exception) {
            Log.e(TAG, "updateOne FAILED")
            e.printStackTrace()
            throw e
        }
        return 1
    }
}