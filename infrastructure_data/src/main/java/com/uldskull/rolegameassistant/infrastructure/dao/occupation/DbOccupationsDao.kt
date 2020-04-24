// File DbJobsDao.kt
// @Author pierre.antoine - 22/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.occupation

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupation

/**
 *   Interface "DbJobsDao" :
 *   DbJobs database interactions.
 **/
@Dao
interface DbOccupationsDao {
    //  CREATE
    @Insert
    fun insertJob(dbOccupation: DbOccupation): Long

    @Insert
    fun insertJobs(dbOccupations: List<DbOccupation>): List<Long>

    //  READ
    @Query("$SELECT_ALL_FROM ${DatabaseValues.TABLE_NAME_OCCUPATIONS}")
    fun getJobs(): LiveData<List<DbOccupation>>

    @Query("$SELECT_ALL_FROM ${DatabaseValues.TABLE_NAME_OCCUPATIONS} WHERE occupationId LIKE :id")
    fun getJobById(id: Long?): DbOccupation

    //  UPDATE
    @Update
    fun updateJob(vararg occupations: DbOccupation): Int

    //  DELETE
    @Delete
    fun deleteJobs(vararg occupations: DbOccupation): Int

    @Query("DELETE FROM ${DatabaseValues.TABLE_NAME_OCCUPATIONS}")
    fun deleteAllJobs(): Int
}