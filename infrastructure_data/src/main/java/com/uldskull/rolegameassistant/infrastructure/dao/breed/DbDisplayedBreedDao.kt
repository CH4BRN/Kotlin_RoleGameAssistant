// File DbBreedDao.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.breed

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_BREED_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_DISPLAYED_BREED
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds.DbDisplayedBreed

/**
 *   Interface "DbBreedDao" :
 *   DbBreed database interactions.
 **/
@Dao
interface DbDisplayedBreedDao {
    //  CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreed(dbDisplayedBreed: DbDisplayedBreed): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreeds(dbDisplayedBreeds: List<DbDisplayedBreed>): List<Long>

    //  READ
    @Query("$SELECT_ALL_FROM $TABLE_NAME_DISPLAYED_BREED")
    fun getBreeds(): LiveData<List<DbDisplayedBreed>>

    @Query("$SELECT_ALL_FROM $TABLE_NAME_DISPLAYED_BREED WHERE $FIELD_BREED_ID LIKE :id")
    fun getBreedById(id: Long?): DbDisplayedBreed

    //  UPDATE
    @Update
    fun updateBreed(vararg displayedBreeds: DbDisplayedBreed): Int

    //  DELETE
    @Delete
    fun deleteBreeds(vararg displayedBreeds: DbDisplayedBreed): Int

    @Query("DELETE FROM $TABLE_NAME_DISPLAYED_BREED")
    fun deleteAllBreeds(): Int
}