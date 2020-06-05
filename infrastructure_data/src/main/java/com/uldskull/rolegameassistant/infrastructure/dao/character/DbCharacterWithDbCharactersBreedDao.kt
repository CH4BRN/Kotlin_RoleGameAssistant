// DbCharacterWithDbCharactersBreedDao.kt created by UldSkull - 29/05/2020

package com.uldskull.rolegameassistant.infrastructure.dao.character

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_CHARACTER_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_CHARACTER
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.database_model.db_character.DbCharacterWithBreeds

/**
Interface "DbCharacterWithDbCharactersBreedDao"

TODO : Describe interface utility.
 **/
@Dao
interface DbCharacterWithDbCharactersBreedDao {

    @Transaction
    @Query("$SELECT_ALL_FROM $TABLE_NAME_CHARACTER")
    fun getCharacterWithBreeds(): List<DbCharacterWithBreeds>

    @Transaction
    @Query("$SELECT_ALL_FROM $TABLE_NAME_CHARACTER WHERE $FIELD_CHARACTER_ID LIKE :id")
    fun getCharacterWithBreeds(id: Long?): DbCharacterWithBreeds
}