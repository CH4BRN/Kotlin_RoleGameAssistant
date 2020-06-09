// File CharacterDao.kt
// @Author pierre.antoine - 30/01/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.character

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_CHARACTER_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_CHARACTER
import com.uldskull.rolegameassistant.infrastructure.database_model.db_character.DbCharacter

/**
 *   Interface "CharacterDao" :
 *   Allow interaction with SqLite database
 **/
@Dao
interface DbCharacterDao {

    //  CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(dbCharacter: DbCharacter): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(dbCharacters: List<DbCharacter>): List<Long>

    //  READ
    @Query("SELECT * FROM $TABLE_NAME_CHARACTER")
    fun getCharacters(): LiveData<List<DbCharacter>>

    @Query("SELECT * FROM $TABLE_NAME_CHARACTER WHERE $FIELD_CHARACTER_ID LIKE :id")
    fun getCharacterById(id: Long?): DbCharacter

    //  UPDATE
    @Update
    fun updateCharacter(vararg characters: DbCharacter): Int?
}