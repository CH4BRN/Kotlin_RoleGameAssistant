// DbFilledSkillDao.kt created by UldSkull - 14/06/2020

package com.uldskull.rolegameassistant.infrastructure.dao.skill

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_FILLED_OCCUPATION_SKILL_CHARACTER_ID
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_FILLED_OCCUPATION_SKILL_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_FILLED_OCCUPATION_SKILL
import com.uldskull.rolegameassistant.infrastructure.dao.DELETE_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.GenericDao
import com.uldskull.rolegameassistant.infrastructure.dao.LIKE
import com.uldskull.rolegameassistant.infrastructure.dao.WHERE
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbFilledSkill

/**
 * Interface "DbFilledSkillDao"
 * TODO : Fill interface use
 **/
@Dao
abstract class DbFilledSkillDao : GenericDao<DbFilledSkill> {
    //  READ
    @Query("SELECT * FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL")
    abstract fun getFilledSkill(): LiveData<List<DbFilledSkill>>

    @Query("SELECT * FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL $WHERE $FIELD_FILLED_OCCUPATION_SKILL_ID $LIKE :id")
    abstract fun getOneById(id: Long?): DbFilledSkill
    //  DELETE
    @Query("$DELETE_FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL $WHERE $FIELD_FILLED_OCCUPATION_SKILL_CHARACTER_ID $LIKE :id")
    abstract fun deleteAllByCharacterId(id: Long): Int

    @Query("$DELETE_FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL ")
    abstract fun deleteAll(): Int
}