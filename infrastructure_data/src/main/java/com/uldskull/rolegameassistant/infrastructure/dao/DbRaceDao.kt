// File DbRaceDao.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.DbRace

/**
 *   Interface "DbRaceDao" :
 *   DbRace database interactions.
 **/
@Dao
interface DbRaceDao {
    //  CREATE
    @Insert
    fun insertRace(dbRace: DbRace): Long

    @Insert
    fun insertRaces(dbRaces: List<DbRace>): List<Long>

    //  READ
    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_RACE}")
    fun getRaces(): LiveData<List<DbRace>>

    //  UPDATE
    @Update
    fun updateRace(vararg races: DbRace): Int

    //  DELETE
    @Delete
    fun deleteRaces(vararg races: DbRace): Int

    @Query("DELETE FROM ${DatabaseValues.TABLE_NAME_RACE}")
    fun deleteAllRaces(): Int
}