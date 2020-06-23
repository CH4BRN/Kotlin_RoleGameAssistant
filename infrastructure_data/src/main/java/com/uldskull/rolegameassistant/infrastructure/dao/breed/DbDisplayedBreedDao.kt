// File DbBreedDao.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.breed

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_BREED_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_DISPLAYED_BREED
import com.uldskull.rolegameassistant.infrastructure.dao.DELETE_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.GenericDao
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds.DbDisplayedBreed

/**
 *   Interface "DbBreedDao" :
 *   DbDisplayedBreed database interactions.
 **/
@Dao
abstract class DbDisplayedBreedDao:GenericDao<DbDisplayedBreed> {

    //  READ
    @Query("$SELECT_ALL_FROM $TABLE_NAME_DISPLAYED_BREED")
    abstract fun getBreeds(): LiveData<List<DbDisplayedBreed>>

    @Query("$SELECT_ALL_FROM $TABLE_NAME_DISPLAYED_BREED WHERE $FIELD_BREED_ID LIKE :id")
    abstract fun getBreedById(id: Long?): DbDisplayedBreed

    //  DELETE
    @Delete
    abstract fun deleteBreeds(vararg displayedBreeds: DbDisplayedBreed): Int

    @Query("$DELETE_FROM $TABLE_NAME_DISPLAYED_BREED")
    abstract fun deleteAllBreeds(): Int
}