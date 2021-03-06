// File DbJobsRepositoryImpl.kt
// @Author pierre.antoine - 22/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.occupations

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.occupation.DbOccupationDbSkillDao
import com.uldskull.rolegameassistant.infrastructure.dao.occupation.DbOccupationsDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupation
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupationAndDbSkillCrossRef
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupationWithDbSkills
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.repository.occupations.OccupationsRepository

/**
 *   Class "DbJobsRepositoryImpl" :
 *   TODO: Fill class use.
 **/
class DbOccupationsRepositoryImpl(
    private val dbOccupationDao: DbOccupationsDao,
    private val dbOccupationDbSkillDao: DbOccupationDbSkillDao
) :
    OccupationsRepository<LiveData<List<DomainOccupation>>> {
    companion object {
        private const val TAG = "DbOccupationsRepositoryImpl"
    }

    private fun List<DbOccupation>.asDomainModel(): List<DomainOccupation> {
        Log.d(TAG, "asDomainModel")
        return map {
            DomainOccupation(
                occupationId = it.occupationId,
                occupationName = it.occupationName,
                occupationSpecial = it.occupationSpecial,
                occupationIncome = it.occupationIncome,
                occupationContacts = it.occupationContacts
            )
        }
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainOccupation>>? {
        Log.d(TAG, "getAll")
        try {
            //  Transforms the DbJobs into DomainJobs
            return Transformations.map(dbOccupationDao.getJobs()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainOccupation? {
        Log.d(TAG, "findOneById")
        var result: DbOccupation
        try {
            result = dbOccupationDao.getJobById(id)
        } catch (e: Exception) {
            Log.e(TAG, "findOneById FAILED")
            e.printStackTrace()
            throw e
        }
        return result.toDomain()
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainOccupation>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if ((all != null) && (all.isNotEmpty())) {
            try {
                val result = dbOccupationDao.insertJobs(all.map { r ->
                    DbOccupation.from(
                        r
                    )
                })
                Log.d(TAG, "insertAll RESULT = ${result.size}")
                return result
            } catch (e: Exception) {
                Log.e(TAG, "insertAll FAILED")
                e.printStackTrace()
                throw e
            }
        } else {
            Log.d(TAG, "insertAll RESULT = 0")
            return emptyList()
        }
    }

    /** Insert one entity  -  it can return a long, which is the new rowId for the inserted item.*/
    override fun insertOne(one: DomainOccupation?): Long? {
        Log.d(TAG, "insertOne")
        return if (one != null) {
            try {
                val result = dbOccupationDao.insertOccupation(DbOccupation.from(one))
                Log.d(TAG, "insertOne RESULT = $result")
                result
            } catch (e: Exception) {
                Log.e(TAG, "insertOne FAILED")
                e.printStackTrace()
                throw  e
            }
        } else {
            -1
        }
    }

    /** Delete all entities **/
    override fun deleteAll(): Int {
        Log.d(TAG, "deleteAll")
        try {
            return dbOccupationDao.deleteAllJobs()
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainOccupation?): Int? {
        Log.d(TAG, "updateOne")
        return dbOccupationDao.updateJob(DbOccupation.from(one))
    }

    override fun insertOccupationAndSkillCross(occupationId: Long?, skillId: Long): Long {
        Log.d(TAG, "insertOccupationAndSkillCross")
        return try {
            if (occupationId != null && skillId != null) {
                dbOccupationDbSkillDao.insertCross(
                    DbOccupationAndDbSkillCrossRef(
                        occupationId = occupationId,
                        skillId = skillId
                    )
                )
            } else {
                -1
            }

        } catch (e: Exception) {
            Log.e(TAG, "insertOccupationAndSkillCross FAILED")
            e.printStackTrace()
            throw e
        }
    }

    override fun findOneWithChildren(occupationId: Long?): DomainOccupationWithSkills {
        var result: DbOccupationWithDbSkills = try {
            dbOccupationDbSkillDao.getOccupationWithSkills(occupationId)

        } catch (e: Exception) {
            Log.e(TAG, "findOneWithChildren FAILED")
            e.printStackTrace()
            throw e
        }
        Log.d(TAG, "$result")

        var occupation = result.occupation
        var skills = result.skills
        var occupationWithSkills = DomainOccupationWithSkills(
            occupation = occupation.toDomain(),
            skills = skills.map { s -> s.toDomain() }
        )

        return occupationWithSkills


    }

}