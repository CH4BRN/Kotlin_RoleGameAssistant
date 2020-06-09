// File DbIdealsDao.kt
// @Author pierre.antoine - 06/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.ideal

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_IDEAL_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_IDEAL
import com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal.DbIdeal

/**
 *   Interface "DbIdealsDao" :
 *   TODO: Fill interface use.
 **/
@Dao
interface DbIdealsDao {

    //  CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIdeal(dbIdeal: DbIdeal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIdeals(dbIdeals: List<DbIdeal>): List<Long>

    //  READ
    @Query("SELECT * FROM $TABLE_NAME_IDEAL")
    fun getIdeals(): LiveData<List<DbIdeal>>

    @Query("SELECT * FROM $TABLE_NAME_IDEAL WHERE $FIELD_IDEAL_ID LIKE :id")
    fun getIdealById(id: Long?): DbIdeal

    //  UPDATE
    @Update
    fun updateIdeal(vararg ideals: DbIdeal): Int

    //  DELETE
    @Delete
    fun deleteIdeals(vararg ideal: DbIdeal): Int

    @Query("DELETE FROM $TABLE_NAME_IDEAL")
    fun deleteAllIdeals(): Int
}