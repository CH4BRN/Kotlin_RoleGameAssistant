// File DbRollCharacteristicDao.kt
// @Author pierre.antoine - 10/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.characteristic

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_BREED_CHARACTERISTIC_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_ROLL_CHARACTERISTICS
import com.uldskull.rolegameassistant.infrastructure.dao.DELETE_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.GenericDao
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRollCharacteristic

/**
 *   Interface "DbRollCharacteristicDao" :
 *   DbRollCharacteristics database interactions.
 **/
@Dao
abstract class DbRollCharacteristicsDao:GenericDao<DbRollCharacteristic> {

    //  READ
    @Query("$SELECT_ALL_FROM $TABLE_NAME_ROLL_CHARACTERISTICS")
    abstract fun getRollCharacteristics(): LiveData<List<DbRollCharacteristic>>

    @Query("$SELECT_ALL_FROM $TABLE_NAME_ROLL_CHARACTERISTICS WHERE $FIELD_BREED_CHARACTERISTIC_ID LIKE :id")
    abstract fun getRollCharacteristicById(id: Long?): DbRollCharacteristic

    //  DELETE
    @Delete
    abstract fun deleteRollCharacteristics(vararg dbRollCharacteristic: DbRollCharacteristic): Int

    @Query("$DELETE_FROM $TABLE_NAME_ROLL_CHARACTERISTICS")
    abstract fun deleteAllRollCharacteristics(): Int
}