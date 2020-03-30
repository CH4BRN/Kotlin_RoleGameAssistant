// File DbCharacteristicDao.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.characteristic

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbCharacteristic

/**
 *   Interface "DbCharacteristicDao" :
 *   TODO: Fill interface use.
 **/
@Dao
interface DbCharacteristicDao {
    //  CREATE
    @Insert
    fun insertCharacteristic(dbRaceCharacteristic: DbCharacteristic): Long

    @Insert
    fun insertCharacteristics(dbRaceCharacteristics: List<DbCharacteristic>): List<Long>

    //  READ
    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_CHARACTERISTICS}")
    fun getCharacteristics(): LiveData<List<DbCharacteristic>>

    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_CHARACTERISTICS} WHERE characteristicId = :id")
    fun getCharacteristicById(id: Long?): DbCharacteristic

    //  UPDATE
    @Update
    fun updateCharacteristic(vararg dbRaceCharacteristics: DbCharacteristic): Int

    //  DELETE
    @Delete
    fun deleteCharacteristics(vararg dbRaceCharacteristic: DbCharacteristic): Int

    @Query("DELETE FROM ${DatabaseValues.TABLE_NAME_CHARACTERISTICS}")
    fun deleteAllCharacteristics(): Int
}