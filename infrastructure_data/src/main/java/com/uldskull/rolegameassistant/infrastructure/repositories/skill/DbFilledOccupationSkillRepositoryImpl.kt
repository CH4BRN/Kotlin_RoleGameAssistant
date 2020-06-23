// DbFilledSkillRepositoryImpl.kt created by UldSkull - 14/06/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.skill

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.skill.DbFilledOccupationSkillDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbFilledSkill
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.repository.skill.FilledOccupationSkillRepository

/**
Class "DbFilledSkillRepositoryImpl"

 repository for db filled skills
 */
class DbFilledOccupationSkillRepositoryImpl(
    /**
     * db filled skill dao
     */
    private val dbFilledOccupationSkillsDao: DbFilledOccupationSkillDao

) : FilledOccupationSkillRepository<LiveData<List<DomainSkillToFill>>> {
    companion object {
        private const val TAG = "DbFilledSkillRepositoryImpl"
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainSkillToFill>>? {
        Log.d(TAG, "getAll")
        try {
            return Transformations.map(dbFilledOccupationSkillsDao.getSkillsByTypes(1)) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e("ERROR", "get all failed.")
            e.printStackTrace()
            throw e
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainSkillToFill? {
        Log.d(TAG, "findOneById")
        var result: DbFilledSkill
        try {
            result = dbFilledOccupationSkillsDao.getOneById(id)
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

    /**
     * find the same skill
     */
    override fun findTheSame(skill: DomainSkillToFill): DomainSkillToFill? {
        Log.d("DEBUG$TAG", "skill?.filledSkillCharacterId :${skill?.filledSkillCharacterId} ")
        var skills = dbFilledOccupationSkillsDao.getAllByCharacterId(skill?.filledSkillCharacterId)
        var result = skills.find { s ->
            ((s.filledSkillName == DbFilledSkill.from(skill).filledSkillName) && (s.filledSkillDescription == DbFilledSkill.from(
                skill
            ).filledSkillDescription) && (s.filledSkillType == DbFilledSkill.from(skill).filledSkillType))
        }
        Log.d("DEBUG$TAG", "Result : $result")
        if (result != null) {
            return result?.toDomain()
        }
        return null
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainSkillToFill>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if (all != null && all.isNotEmpty()) {
            var idList = mutableListOf<Long>()
            try {
                all.forEach {
                    idList.add(dbFilledOccupationSkillsDao.insert(DbFilledSkill.from(it)))
                }


                Log.d(TAG, "insertAll RESULT = ${idList}")
                return idList
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
                    dbFilledOccupationSkillsDao.insert(
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
            return dbFilledOccupationSkillsDao.deleteAll()
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
            dbFilledOccupationSkillsDao.update(DbFilledSkill.from(one))
        } catch (e: Exception) {
            Log.e(TAG, "updateOne FAILED")
            e.printStackTrace()
            throw e
        }
        return 1
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

    /**
     * delete all skills by character id
     */
    override fun deleteAllByCharacterId(id: Long): Int {
        Log.d(TAG, "deleteAllByCharacterId")
        try {
            return dbFilledOccupationSkillsDao.deleteAllByCharacterId(id)
        } catch (e: Exception) {
            Log.e(TAG, "deleteAllByCharacterId FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /**
     * update tens value for a skill
     */
    override fun updateTensValues(skill: DomainSkillToFill, tensValues: Int): Int {
        return dbFilledOccupationSkillsDao?.updateTensValues(skill?.skillId!!, tensValues)
    }

    /**
     * update units value for a skill
     */
    override fun updateUnitsValues(skill: DomainSkillToFill, unitsValues: Int): Int {
        return dbFilledOccupationSkillsDao?.updateUnitsValues(skill?.skillId!!, unitsValues)
    }


}