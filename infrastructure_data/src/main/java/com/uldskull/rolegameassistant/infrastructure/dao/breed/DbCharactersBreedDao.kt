// DbCharactersBreedDao.kt created by UldSkull - 29/05/2020

package com.uldskull.rolegameassistant.infrastructure.dao.breed

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_CHARACTER_BREED_ID
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_CHARACTER_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_CHARACTERS_BREED
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.characterBreeds.DbCharactersBreed

/**
Interface "DbCharacterBreedDao"

 **/
@Dao
interface DbCharactersBreedDao {
    //  CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharactersBreed(dbCharactersBreed: DbCharactersBreed): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharactersBreeds(dbCharactersBreeds: List<DbCharactersBreed>): List<Long>

    //  READ
    @Query("$SELECT_ALL_FROM $TABLE_NAME_CHARACTERS_BREED")
    fun getCharactersBreeds(): LiveData<List<DbCharactersBreed>>

    @Query("$SELECT_ALL_FROM $TABLE_NAME_CHARACTERS_BREED WHERE $FIELD_CHARACTER_BREED_ID LIKE :id")
    fun getCharactersBreedById(id: Long?): DbCharactersBreed

    //  UPDATE
    @Update
    fun updateCharactersBreed(vararg displayedBreeds: DbCharactersBreed): Int

    //  DELETE
    @Delete
    fun deleteCharactersBreeds(vararg displayedBreeds: DbCharactersBreed): Int

    @Query("DELETE FROM ${TABLE_NAME_CHARACTERS_BREED}")
    fun deleteAllCharactersBreeds(): Int

    @Query("DELETE FROM ${TABLE_NAME_CHARACTERS_BREED} WHERE $FIELD_CHARACTER_ID LIKE :id")
    fun deleteCharacterBreedsById(id:Long?)
}