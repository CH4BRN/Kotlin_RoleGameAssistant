// File DbRollCharacteristicDao.kt
// @Author pierre.antoine - 10/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.characteristic

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_BREED_CHARACTERISTIC_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_ROLL_CHARACTERISTICS
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRollCharacteristic

/**
 *   Interface "DbRollCharacteristicDao" :
 *   TODO: Fill interface use.
 **/
@Dao
interface DbRollCharacteristicsDao {
    //  CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRollCharacteristic(dbRollCharacteristic: DbRollCharacteristic): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRollCharacteristics(dbRollCharacteristics: List<DbRollCharacteristic>): List<Long>

    //  READ
    @Query("SELECT * FROM $TABLE_NAME_ROLL_CHARACTERISTICS")
    fun getRollCharacteristics(): LiveData<List<DbRollCharacteristic>>

    @Query("SELECT * FROM $TABLE_NAME_ROLL_CHARACTERISTICS WHERE $FIELD_BREED_CHARACTERISTIC_ID LIKE :id")
    fun getRollCharacteristicById(id: Long?): DbRollCharacteristic

    //  UPDATE
    @Update
    fun updateRollCharacteristic(vararg dbRollCharacteristics: DbRollCharacteristic): Int

    //  DELETE
    @Delete
    fun deleteRollCharacteristics(vararg dbRollCharacteristic: DbRollCharacteristic): Int

    @Query("DELETE FROM $TABLE_NAME_ROLL_CHARACTERISTICS")
    fun deleteAllRollCharacteristics(): Int
}