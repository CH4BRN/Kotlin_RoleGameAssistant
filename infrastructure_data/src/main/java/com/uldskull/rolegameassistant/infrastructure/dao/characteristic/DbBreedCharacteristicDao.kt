// File DbBreedCharacteristicDao.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.characteristic

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_BREED_CHARACTERISTIC_ID
import com.uldskull.rolegameassistant.infrastructure.Queries.DELETE_ALL_BREED_CHARACTERISTICS
import com.uldskull.rolegameassistant.infrastructure.Queries.SELECT_ALL_BREED_CHARACTERISTICS
import com.uldskull.rolegameassistant.infrastructure.dao.GenericDao
import com.uldskull.rolegameassistant.infrastructure.dao.LIKE
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.WHERE
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbBreedCharacteristic

/**
 *   Interface "DbBreedCharacteristicDao" :
 *   DbBreedCharacteristic database interactions.
 **/
@Dao
abstract class DbBreedCharacteristicDao : GenericDao<DbBreedCharacteristic>{

    //  READ
    @Query(SELECT_ALL_BREED_CHARACTERISTICS)
    abstract fun getBreedCharacteristics(): LiveData<List<DbBreedCharacteristic>>

    @Query("$SELECT_ALL_BREED_CHARACTERISTICS $WHERE $FIELD_BREED_CHARACTERISTIC_ID $LIKE :id")
    abstract fun getBreedCharacteristicById(id: Long?): DbBreedCharacteristic

    //  DELETE
    @Delete
    abstract fun deleteBreedCharacteristics(vararg dbBreedCharacteristic: DbBreedCharacteristic): Int

    @Query(DELETE_ALL_BREED_CHARACTERISTICS)
    abstract fun deleteAllBreedCharacteristics(): Int
}