// File DbJobsDao.kt
// @Author pierre.antoine - 22/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.occupation

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_OCCUPATION_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_OCCUPATIONS
import com.uldskull.rolegameassistant.infrastructure.dao.DELETE_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.GenericDao
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupation

/**
 *   Interface "DbJobsDao" :
 *   DbOccupation database interactions.
 **/
@Dao
abstract class DbOccupationsDao : GenericDao<DbOccupation> {
    //  READ
    @Query("$SELECT_ALL_FROM $TABLE_NAME_OCCUPATIONS")
    abstract fun getJobs(): LiveData<List<DbOccupation>>

    @Query("$SELECT_ALL_FROM $TABLE_NAME_OCCUPATIONS WHERE $FIELD_OCCUPATION_ID LIKE :id")
    abstract fun getJobById(id: Long?): DbOccupation

    @Query("$DELETE_FROM $TABLE_NAME_OCCUPATIONS")
    abstract fun deleteAllOccupations(): Int
}