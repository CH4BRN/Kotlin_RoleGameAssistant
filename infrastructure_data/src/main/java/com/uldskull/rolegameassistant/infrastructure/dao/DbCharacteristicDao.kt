// File DbCharacteristicDao.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbCharacteristic

/**
 *   Interface "DbCharacteristicDao" :
 *   DbCharacteristics database interactions.
 **/
@Dao
interface DbCharacteristicDao {
    //  CREATE
    @Insert
    fun insertCharacteristic(dbCharacteristic: DbCharacteristic): Long

    @Insert
    fun insertCharacteristics(dbCharacteristics: List<DbCharacteristic>): List<Long>

    //  READ
    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_CHARACTERISTICS}")
    fun getCharacteristics(): LiveData<List<DbCharacteristic>>

    //  UPDATE
    @Update
    fun updateCharacteristic(vararg characteristics: DbCharacteristic): Int

    //  DELETE
    @Delete
    fun deleteCharacteristics(vararg characteristics: DbCharacteristic): Int

    @Query("DELETE FROM ${DatabaseValues.TABLE_NAME_CHARACTERISTICS}")
    fun deleteAllCharacteristics(): Int

}