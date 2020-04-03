// File DbBreedDao.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.breed

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.DbBreed

/**
 *   Interface "DbBreedDao" :
 *   DbBreed database interactions.
 **/
@Dao
interface DbBreedDao {
    //  CREATE
    @Insert
    fun insertBreed(dbBreed: DbBreed): Long

    @Insert
    fun insertBreeds(dbBreeds: List<DbBreed>): List<Long>

    //  READ
    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_BREED}")
    fun getBreeds(): LiveData<List<DbBreed>>

    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_BREED} WHERE breedId LIKE :id")
    fun getBreedById(id: Long?): DbBreed

    //  UPDATE
    @Update
    fun updateBreed(vararg breeds: DbBreed): Int

    //  DELETE
    @Delete
    fun deleteBreeds(vararg breeds: DbBreed): Int

    @Query("DELETE FROM ${DatabaseValues.TABLE_NAME_BREED}")
    fun deleteAllBreeds(): Int
}