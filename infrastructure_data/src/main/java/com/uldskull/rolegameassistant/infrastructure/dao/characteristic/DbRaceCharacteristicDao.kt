// File DbRaceCharacteristicDao.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.characteristic

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRaceCharacteristic

/**
 *   Interface "DbRaceCharacteristicDao" :
 *   DbRaceCharacteristic database interactions.
 **/
@Dao
interface DbRaceCharacteristicDao {
    //  CREATE
    @Insert
    fun insertRaceCharacteristic(dbRaceCharacteristic: DbRaceCharacteristic): Long

    @Insert
    fun insertRaceCharacteristics(dbRaceCharacteristics: List<DbRaceCharacteristic>): List<Long>

    //  READ
    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_RACE_CHARACTERISTICS}")
    fun getRaceCharacteristics(): LiveData<List<DbRaceCharacteristic>>

    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_RACE_CHARACTERISTICS} WHERE characteristicId = :id")
    fun getRaceCharacteristicById(id: Long?): DbRaceCharacteristic

    //  UPDATE
    @Update
    fun updateRaceCharacteristic(vararg dbRaceCharacteristics: DbRaceCharacteristic): Int

    //  DELETE
    @Delete
    fun deleteRaceCharacteristics(vararg dbRaceCharacteristic: DbRaceCharacteristic): Int

    @Query("DELETE FROM ${DatabaseValues.TABLE_NAME_RACE_CHARACTERISTICS}")
    fun deleteAllRaceCharacteristics(): Int
}