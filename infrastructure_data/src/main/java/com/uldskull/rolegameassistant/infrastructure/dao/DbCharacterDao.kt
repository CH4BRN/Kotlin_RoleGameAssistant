// File CharacterDao.kt
// @Author pierre.antoine - 30/01/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCharacter

/**
 *   Interface "CharacterDao" :
 *   Allow interaction with SqLite database
 **/
@Dao
interface DbCharacterDao {

    //  CREATE
    @Insert
    fun insertCharacter(dbCharacter: DbCharacter): Long

    @Insert
    fun insertCharacters(dbCharacters: List<DbCharacter>): List<Long>

    //  READ
    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_CHARACTER}")
    fun getCharacters(): LiveData<List<DbCharacter>>
}