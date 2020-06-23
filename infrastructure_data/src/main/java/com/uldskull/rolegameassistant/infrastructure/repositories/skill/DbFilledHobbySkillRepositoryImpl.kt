// DbFilledHobbySkillRepositoryImpl.kt created by UldSkull - 15/06/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.skill

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.skill.DbFilledOccupationSkillDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbFilledSkill
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.repository.skill.FilledHobbySkillRepository

/**
Class "DbFilledHobbySkillRepositoryImpl"

Repository for hobby filled skill
 */
class DbFilledHobbySkillRepositoryImpl(
    /**
     * Filled skill dao
     */
    private val dbFilledOccupationSkillDao: DbFilledOccupationSkillDao
) : FilledHobbySkillRepository<LiveData<List<DomainSkillToFill>>> {
    /**
     * Delete all skills by character id
     */
    override fun deleteAllByCharacterId(id: Long): Int {
        Log.d(TAG, "deleteAllByCharacterId")
        try {
            return dbFilledOccupationSkillDao.deleteAllByCharacterId(id)
        } catch (e: Exception) {
            Log.e(TAG, "deleteAllByCharacterId FAILED")
            e.printStackTrace()
            throw e
        }
    }

    companion object {
        private const val TAG = "DbFilledHobbySkillRepositoryImpl"
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainSkillToFill>>? {
        Log.d(TAG, "getAll")
        try {
            return Transformations.map(dbFilledOccupationSkillDao.getSkillsByTypes(1)) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e("ERROR", "get all failed.")
            e.printStackTrace()
            throw e
        }
    }
    /**
     * Converts a list of database entities into domain entities
     */
    private fun List<DbFilledSkill>.asDomainModel(): List<DomainSkillToFill> {
        Log.d("DEBUG$TAG", "asDomainModel")
        return map {
            DomainSkillToFill(
                filledSkillCharacterId = it.filledSkillCharacterId,
                filledSkillBase = it.filledSkillBase,
                filledSkillId = it.filledSkillId,
                filledSkillMax = it.filledSkillMax,
                filledSkillName = it.filledSkillName,
                filledSkillTensValue = it.filledSkillTensValue,
                filledSkillUnitsValue = it.filledSkillUnitsValue,
                filledSkillTotal = it.filledSkillTotal,
                filledSkillType = it.filledSkillType
            )
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainSkillToFill? {
        Log.d(TAG, "findOneById")
        var result: DbFilledSkill
        try {
            result = dbFilledOccupationSkillDao.getOneById(id)
        } catch (e: Exception) {
            Log.e("ERROR$TAG", "findOneById FAILED")
            e.printStackTrace()
            throw e
        }
        if (result == null) {
            return null
        }
        return result.toDomain()
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainSkillToFill>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if (all != null && all.isNotEmpty()) {
            try {
                val result = dbFilledOccupationSkillDao.insert(all.map { s ->
                    DbFilledSkill.from(
                        s
                    )
                })
                Log.d(TAG, "insertAll RESULT = ${result}")
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
    override fun insertOne(one: DomainSkillToFill?): Long? {
        Log.d(TAG, "insertOne $one")
        return if (one != null) {
            try {
                val result =
                    dbFilledOccupationSkillDao.insert(
                        DbFilledSkill.from(
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
            return dbFilledOccupationSkillDao.deleteAll()
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainSkillToFill?): Int? {
        Log.d(TAG, "updateOne")
        try {
            dbFilledOccupationSkillDao.update(DbFilledSkill.from(one))
        } catch (e: Exception) {
            Log.e(TAG, "updateOne FAILED")
            e.printStackTrace()
            throw e
        }
        return 1
    }

}