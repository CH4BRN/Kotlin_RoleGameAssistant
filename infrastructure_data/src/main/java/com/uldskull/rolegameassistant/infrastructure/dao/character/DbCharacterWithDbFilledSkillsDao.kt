// DbCharacterWithDbFilledSkillsDao.kt created by UldSkull - 13/06/2020

package com.uldskull.rolegameassistant.infrastructure.dao.character

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_CHARACTER
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.database_model.db_character.DbCharacterWithDbSkills

/**
 * Class "DbCharacterWithSkillsDao"
 * DbCharacterWithDbSkills database interactions.
 */
@Dao
interface DbCharacterWithDbFilledSkillsDao {
    @Transaction
    @Query("$SELECT_ALL_FROM $TABLE_NAME_CHARACTER")
    suspend fun getCharacterWithSkills(): List<DbCharacterWithDbSkills>
}