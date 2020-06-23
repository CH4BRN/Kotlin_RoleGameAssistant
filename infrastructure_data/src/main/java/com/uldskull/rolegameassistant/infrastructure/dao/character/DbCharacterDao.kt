// File CharacterDao.kt
// @Author pierre.antoine - 30/01/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.character

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_CHARACTER_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_CHARACTER
import com.uldskull.rolegameassistant.infrastructure.dao.DELETE_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.GenericDao
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.database_model.db_character.DbCharacter

/**
 *   Interface "CharacterDao" :
 *   Db Character database interactions.
 **/
@Dao
abstract class DbCharacterDao : GenericDao<DbCharacter> {

    //  READ
    @Query("$SELECT_ALL_FROM $TABLE_NAME_CHARACTER")
    abstract fun getCharacters(): LiveData<List<DbCharacter>>

    @Query("$SELECT_ALL_FROM $TABLE_NAME_CHARACTER WHERE $FIELD_CHARACTER_ID LIKE :id")
    abstract fun getCharacterById(id: Long?): DbCharacter

    @Query("$DELETE_FROM $TABLE_NAME_CHARACTER")
    abstract fun deleteAll():Int

}