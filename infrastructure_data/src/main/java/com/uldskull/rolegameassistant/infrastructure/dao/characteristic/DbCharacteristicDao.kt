// File DbCharacteristicDao.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.characteristic

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_BREED_CHARACTERISTIC_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_CHARACTERISTICS
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbCharacteristic

/**
 *   Interface "DbCharacteristicDao" :
 *   TODO: Fill interface use.
 **/
@Dao
interface DbCharacteristicDao {
    //  CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacteristic(dbBreedCharacteristic: DbCharacteristic): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacteristics(dbBreedCharacteristics: List<DbCharacteristic>): List<Long>

    //  READ
    @Query("SELECT * FROM $TABLE_NAME_CHARACTERISTICS")
    fun getCharacteristics(): LiveData<List<DbCharacteristic>>

    @Query("SELECT * FROM $TABLE_NAME_CHARACTERISTICS WHERE $FIELD_BREED_CHARACTERISTIC_ID = :id")
    fun getCharacteristicById(id: Long?): DbCharacteristic

    //  UPDATE
    @Update
    fun updateCharacteristic(vararg dbBreedCharacteristics: DbCharacteristic): Int

    //  DELETE
    @Delete
    fun deleteCharacteristics(vararg dbBreedCharacteristic: DbCharacteristic): Int

    @Query("DELETE FROM $TABLE_NAME_CHARACTERISTICS")
    fun deleteAllCharacteristics(): Int
}