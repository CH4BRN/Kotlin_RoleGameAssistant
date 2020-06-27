// File DbBreedDao.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.breed

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_BREED_ID
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_BREED_NAME
import com.uldskull.rolegameassistant.infrastructure.Queries.DELETE_ALL_DISPLAYED_BREEDS
import com.uldskull.rolegameassistant.infrastructure.Queries.SELECT_ALL_DISPLAYED_BREEDS
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_DISPLAYED_BREED
import com.uldskull.rolegameassistant.infrastructure.dao.*
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds.DbDisplayedBreed

/**
 *   Interface "DbBreedDao" :
 *   DbDisplayedBreed database interactions.
 **/
@Dao
abstract class DbDisplayedBreedDao:GenericDao<DbDisplayedBreed> {

    //  READ
    @Query(SELECT_ALL_DISPLAYED_BREEDS)
    abstract fun getBreeds(): LiveData<List<DbDisplayedBreed>>

    @Query("$SELECT_ALL_FROM $TABLE_NAME_DISPLAYED_BREED $WHERE $FIELD_BREED_ID $LIKE :id")
    abstract suspend fun getBreedById(id: Long?): DbDisplayedBreed

    @Query("$SELECT_ALL_FROM $TABLE_NAME_DISPLAYED_BREED $WHERE $FIELD_BREED_NAME $LIKE :name ")
    abstract suspend fun getBreedByName(name:String?):DbDisplayedBreed

    //  DELETE
    @Delete
    abstract fun deleteBreeds(vararg displayedBreeds: DbDisplayedBreed): Int

    @Query(DELETE_ALL_DISPLAYED_BREEDS)
    abstract fun deleteAllBreeds(): Int
}