// File DbBreedWithDbBonusCharacteristicsDao.kt
// @Author pierre.antoine - 29/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.breed

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.DbBreedWithCharacteristics

/**
 *   Class "DbBreedWithDbBonusCharacteristicsDao" :
 *   TODO: Fill class use.
 **/
@Dao
interface DbBreedWithDbCharacteristicsDao {

    @Transaction
    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_BREED}")
    fun getBreedsWithCharacteristics(): List<DbBreedWithCharacteristics>

    @Transaction
    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_BREED} WHERE breedId LIKE :id")
    fun getBreedWithCharacteristics(id: Long?): DbBreedWithCharacteristics

}