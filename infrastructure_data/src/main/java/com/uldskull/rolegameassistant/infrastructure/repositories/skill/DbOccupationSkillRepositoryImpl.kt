// File DbOccupationSkillRepositoryImpl.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.skill

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.skill.DbOccupationSkillDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbOccupationSkill
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill
import com.uldskull.rolegameassistant.repository.skill.OccupationSkillRepository

/**
 *   Class "DbOccupationSkillRepositoryImpl" :
 *   TODO: Fill class use.
 **/
class DbOccupationSkillRepositoryImpl(
    private val dbOccupationSkillDao: DbOccupationSkillDao
) :
    OccupationSkillRepository<LiveData<List<DomainOccupationSkill>>> {

    companion object {
        private const val TAG = "DbOccupationSkillRepositoryImpl"
    }

    private fun List<DbOccupationSkill>.asDomainModel(): List<DomainOccupationSkill> {
        Log.d(TAG, "asDomainModel")
        return map {
            DomainOccupationSkill(
                skillId = it.skillId,
                skillName = it.skillName,
                skillDescription = it.skillDescription
            )
        }
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainOccupationSkill>>? {
        Log.d(TAG, "getAll")
        try {
            return Transformations.map(dbOccupationSkillDao.getOccupationSkills()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getAll FAILED ! ")
            throw e
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainOccupationSkill? {
        Log.d(TAG, "findOneById")
        var result: DbOccupationSkill
        try {
            result = dbOccupationSkillDao.getOccupationSkillById(id)
        } catch (e: Exception) {
            Log.e(TAG, "findOneById FAILED")
            throw e
        }
        return result.toDomain()
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainOccupationSkill>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if (all != null && all.isNotEmpty()) {
            try {
                val result = dbOccupationSkillDao.insertOccupationSkills(all.map { s ->
                    DbOccupationSkill.from(
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
    override fun insertOne(one: DomainOccupationSkill?): Long? {
        Log.d(TAG, "insertOne $one")
        return if (one != null) {
            try {
                val result =
                    dbOccupationSkillDao.insertOccupationSkill(
                        DbOccupationSkill.from(
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
    override fun deleteAll(): Int {
        Log.d(TAG, "deleteAll")
        try {
            return dbOccupationSkillDao.deleteAllOccupationSkills()
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainOccupationSkill?): Int? {
        Log.d(TAG, "updateOne")
        try {
            dbOccupationSkillDao.updateOccupationSkill(DbOccupationSkill.from(one))
        } catch (e: Exception) {
            Log.e(TAG, "updateOne FAILED")
            e.printStackTrace()
            throw e
        }
        return 1
    }
// TODO : Fill class.
}