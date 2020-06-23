// File DbIdealsDao.kt
// @Author pierre.antoine - 06/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.ideal

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_IDEAL_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_IDEAL
import com.uldskull.rolegameassistant.infrastructure.dao.DELETE_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.GenericDao
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal.DbIdeal

/**
 *   Interface "DbIdealsDao" :
 *   DbIdeal database interactions.
 **/
@Dao
abstract class DbIdealsDao : GenericDao<DbIdeal> {
    //  READ
    @Query("$SELECT_ALL_FROM $TABLE_NAME_IDEAL")
    abstract fun getIdealsLiveData(): LiveData<List<DbIdeal>>

    @Query("$SELECT_ALL_FROM $TABLE_NAME_IDEAL WHERE $FIELD_IDEAL_ID LIKE :id")
    abstract fun getIdealById(id: Long?): DbIdeal

    @Query("$SELECT_ALL_FROM  $TABLE_NAME_IDEAL")
    abstract fun getIdeals(): List<DbIdeal>


    //  DELETE
    @Delete
    abstract fun deleteIdeals(vararg ideal: DbIdeal): Int

    @Query("$DELETE_FROM $TABLE_NAME_IDEAL")
    abstract fun deleteAllIdeals(): Int
}