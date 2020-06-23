// File DbCharacteristicDao.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.characteristic

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_BREED_CHARACTERISTIC_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_CHARACTERISTICS
import com.uldskull.rolegameassistant.infrastructure.dao.DELETE_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.GenericDao
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbCharacteristic

/**
 *   Interface "DbCharacteristicDao" :
 *   DbCharacteristics database interactions.
 **/
@Dao
abstract class DbCharacteristicDao : GenericDao<DbCharacteristic> {
    //  READ
    @Query("$SELECT_ALL_FROM $TABLE_NAME_CHARACTERISTICS")
    abstract fun getCharacteristics(): LiveData<List<DbCharacteristic>>

    @Query("$SELECT_ALL_FROM $TABLE_NAME_CHARACTERISTICS WHERE $FIELD_BREED_CHARACTERISTIC_ID = :id")
    abstract fun getCharacteristicById(id: Long?): DbCharacteristic


    //  DELETE
    @Delete
    abstract fun deleteCharacteristics(vararg dbBreedCharacteristic: DbCharacteristic): Int

    @Query("$DELETE_FROM $TABLE_NAME_CHARACTERISTICS")
    abstract fun deleteAllCharacteristics(): Int
}