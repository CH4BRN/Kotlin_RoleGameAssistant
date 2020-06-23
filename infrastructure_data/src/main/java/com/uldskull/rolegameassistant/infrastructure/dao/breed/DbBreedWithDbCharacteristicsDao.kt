// File DbBreedWithDbBonusCharacteristicsDao.kt
// @Author pierre.antoine - 29/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.breed

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_BREED_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_DISPLAYED_BREED
import com.uldskull.rolegameassistant.infrastructure.dao.LIKE
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.WHERE
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds.DbDisplayedBreedWithCharacteristics

/**
 *   Class "DbBreedWithDbBonusCharacteristicsDao" :
 *   DbDisplayedBreedWithCharacteristics database interactions.
 **/
@Dao
interface DbBreedWithDbCharacteristicsDao {

    @Transaction
    @Query("$SELECT_ALL_FROM $TABLE_NAME_DISPLAYED_BREED")
    fun getBreedsWithCharacteristics(): List<DbDisplayedBreedWithCharacteristics>

    @Transaction
    @Query("$SELECT_ALL_FROM $TABLE_NAME_DISPLAYED_BREED $WHERE $FIELD_BREED_ID $LIKE :id")
    fun getBreedWithCharacteristics(id: Long?): DbDisplayedBreedWithCharacteristics

}