// DbFilledSkillRepositoryImpl.kt created by UldSkull - 14/06/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.skill

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.character.DbCharacterWithDbFilledSkillsDao
import com.uldskull.rolegameassistant.infrastructure.dao.skill.DbFilledSkillDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbFilledSkill
import com.uldskull.rolegameassistant.models.character.skill.DomainFilledSkill
import com.uldskull.rolegameassistant.repository.skill.FilledOccupationSkillRepository

/**
Class "DbFilledSkillRepositoryImpl"

TODO: Describe class utility.
 */
class DbFilledSkillRepositoryImpl(
    private val dbFilledSkillsDao: DbFilledSkillDao

) : FilledOccupationSkillRepository<LiveData<List<DomainFilledSkill>>> {
    companion object {
        private const val TAG = "DbFilledSkillRepositoryImpl"
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainFilledSkill>>? {
        Log.d(TAG, "getAll")
        try {
            return Transformations.map(dbFilledSkillsDao.getFilledSkill()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e("ERROR", "get all failed.")
            e.printStackTrace()
            throw e
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainFilledSkill? {
        Log.d(TAG, "findOneById")
        var result: DbFilledSkill
        try {
            result = dbFilledSkillsDao.getOneById(id)
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
    override fun insertAll(all: List<DomainFilledSkill>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if (all != null && all.isNotEmpty()) {
            try {
                val result = dbFilledSkillsDao.insert(all.map { s ->
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
    override fun insertOne(one: DomainFilledSkill?): Long? {
        Log.d(TAG, "insertOne $one")
        return if (one != null) {
            try {
                val result =
                    dbFilledSkillsDao.insert(
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
            return dbFilledSkillsDao.deleteAll()
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainFilledSkill?): Int? {
        Log.d(TAG, "updateOne")
        try {
            dbFilledSkillsDao.update(DbFilledSkill.from(one))
        } catch (e: Exception) {
            Log.e(TAG, "updateOne FAILED")
            e.printStackTrace()
            throw e
        }
        return 1
    }



    private fun List<DbFilledSkill>.asDomainModel(): List<DomainFilledSkill> {
        Log.d("DEBUG$TAG", "asDomainModel")
        return map {
            DomainFilledSkill(
                filledSkillCharacterId = it.filledSkillCharacterId,
                filledSkillBase = it.filledSkillBase,
                filledSkillId = it.filledSkillId,
                filledSkillMax = it.filledSkillMax,
                filledSkillName = it.filledSkillName,
                filledSkillTensValue = it.filledSkillTensValue,
                filledSkillTotal = it.filledSkillTotal,
                filledSkillUnitsValue = it.filledSkillUnitsValue
            )
        }
    }

    override fun deleteAllByCharacterId(id: Long): Int {
        Log.d(TAG, "deleteAllByCharacterId")
        try {
            return dbFilledSkillsDao.deleteAllByCharacterId(id)
        } catch (e: Exception) {
            Log.e(TAG, "deleteAllByCharacterId FAILED")
            e.printStackTrace()
            throw e
        }
    }

}