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
import com.uldskull.rolegameassistant.models.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.repository.occupations.OccupationsRepository

/**
 *   Class "DbJobsRepositoryImpl" :
 *   Repository for DbOccupation
 **/
class DbOccupationsRepositoryImpl(
    /**
     * Occupation dao
     */
    private val dbOccupationDao: DbOccupationsDao,
    /**
     * Occupation skill dao
     */
    private val dbOccupationDbSkillDao: DbOccupationDbSkillDao
) :
    OccupationsRepository<LiveData<List<DomainOccupation>>> {
    companion object {
        private const val TAG = "DbOccupationsRepositoryImpl"
    }
    /**
     * Converts a list of database entities into domain entities
     */
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
    override fun getAll(): LiveData<List<DomainOccupation>> {
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
        val result: DbOccupation
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
                val result = dbOccupationDao.insert(all.map { domainOccupation: DomainOccupation ->
                    DbOccupation.from(
                        domainOccupation
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
                val result = dbOccupationDao.insert(DbOccupation.from(one))
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
            return dbOccupationDao.deleteAllOccupations()
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainOccupation?): Int? {
        Log.d(TAG, "updateOne")
        return dbOccupationDao.update(DbOccupation.from(one))
    }

    /**
     * Inserts realtion between occupation and skills
     */
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

    /**
     * find one occupation with all its associated skills
     */
    override fun findOneWithChildren(occupationId: Long?): DomainOccupationWithSkills {
        val result: DbOccupationWithDbSkills = try {
            dbOccupationDbSkillDao.getOccupationWithSkills(occupationId)

        } catch (e: Exception) {
            Log.e(TAG, "findOneWithChildren FAILED")
            e.printStackTrace()
            throw e
        }
        Log.d(TAG, "$result")

        val occupation = result.occupation
        val skills = result.skills

        return DomainOccupationWithSkills(
            occupation = occupation.toDomain(),
            skills = skills.map { s -> s.toDomain() }
        )


    }

    /**
     * delete one occupation
     */
    override fun deleteOne(currentOccupationToEdit: DomainOccupation):Int {
        if(currentOccupationToEdit == null){
            throw Exception("ERROR : Occupation is null.")
        }
       return dbOccupationDao.delete( DbOccupation.from(currentOccupationToEdit))
    }

}