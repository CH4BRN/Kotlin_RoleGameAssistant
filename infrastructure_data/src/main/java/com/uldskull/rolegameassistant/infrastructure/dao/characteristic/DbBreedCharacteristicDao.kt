// File DbBreedCharacteristicDao.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.characteristic

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbBreedCharacteristic

/**
 *   Interface "DbBreedCharacteristicDao" :
 *   DbBreedCharacteristic database interactions.
 **/
@Dao
interface DbBreedCharacteristicDao {
    //  CREATE
    @Insert
    fun insertBreedCharacteristic(dbBreedCharacteristic: DbBreedCharacteristic): Long

    @Insert
    fun insertBreedCharacteristics(dbBreedCharacteristics: List<DbBreedCharacteristic>): List<Long>

    //  READ
    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_BREED_CHARACTERISTICS}")
    fun getBreedCharacteristics(): LiveData<List<DbBreedCharacteristic>>

    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_BREED_CHARACTERISTICS} WHERE characteristicId LIKE :id")
    fun getBreedCharacteristicById(id: Long?): DbBreedCharacteristic

    //  UPDATE
    @Update
    fun updateBreedCharacteristic(vararg dbBreedCharacteristics: DbBreedCharacteristic): Int

    //  DELETE
    @Delete
    fun deleteBreedCharacteristics(vararg dbBreedCharacteristic: DbBreedCharacteristic): Int

    @Query("DELETE FROM ${DatabaseValues.TABLE_NAME_BREED_CHARACTERISTICS}")
    fun deleteAllBreedCharacteristics(): Int
}