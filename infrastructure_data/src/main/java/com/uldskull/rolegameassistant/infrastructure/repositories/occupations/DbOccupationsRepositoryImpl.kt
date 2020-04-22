// File DbJobsRepositoryImpl.kt
// @Author pierre.antoine - 22/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.occupations

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.job.DbOccupationsDao
import com.uldskull.rolegameassistant.infrastructure.database_model.DbOccupation
import com.uldskull.rolegameassistant.models.character.DomainOccupation
import com.uldskull.rolegameassistant.repository.occupations.OccupationsRepository

/**
 *   Class "DbJobsRepositoryImpl" :
 *   TODO: Fill class use.
 **/
class DbOccupationsRepositoryImpl(
    private val dbJobDao: DbOccupationsDao
) :
    OccupationsRepository<LiveData<List<DomainOccupation>>> {
    companion object {
        private const val TAG = "DbJobsRepositoryImpl"
    }

    private fun List<DbOccupation>.asDomainModel(): List<DomainOccupation> {
        Log.d(TAG, "asDomainModel")
        return map {
            DomainOccupation(
                occupationId = it.jobId,
                occupationDescription = it.jobDescription,
                occupationName = it.jobName
            )
        }
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainOccupation>>? {
        Log.d(TAG, "getAll")
        try {
            //  Transforms the DbJobs into DomainJobs
            return Transformations.map(dbJobDao.getJobs()) {
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
            result = dbJobDao.getJobById(id)
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
                val result = dbJobDao.insertJobs(all.map { r ->
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
                val result = dbJobDao.insertJob(DbOccupation.from(one))
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
            return dbJobDao.deleteAllJobs()
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainOccupation?): Int? {
        Log.d(TAG, "updateOne")
        return dbJobDao.updateJob(DbOccupation.from(one))
    }
}