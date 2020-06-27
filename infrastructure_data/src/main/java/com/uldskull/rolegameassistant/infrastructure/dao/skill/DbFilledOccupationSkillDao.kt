// DbFilledSkillDao.kt created by UldSkull - 14/06/2020

package com.uldskull.rolegameassistant.infrastructure.dao.skill

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_FILLED_OCCUPATION_SKILL_CHARACTER_ID
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_FILLED_OCCUPATION_SKILL_ID
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_FILLED_SKILL_TYPE
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_FILLED_OCCUPATION_SKILL
import com.uldskull.rolegameassistant.infrastructure.dao.*
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbFilledSkill

/**
 * Interface "DbFilledSkillDao"
 * DbFilledSkill database interactions.
 **/
@Dao
abstract class DbFilledOccupationSkillDao : GenericDao<DbFilledSkill> {
    //  READ
    @Query("$SELECT_ALL_FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL")
    abstract fun getFilledSkill(): LiveData<List<DbFilledSkill>>

    @Query("$SELECT_ALL_FROM DbFilledSkill WHERE $FIELD_FILLED_SKILL_TYPE LIKE :type")
    abstract fun getSkillsByTypes(type: Long): LiveData<List<DbFilledSkill>>


    @Query("$SELECT_ALL_FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL $WHERE $FIELD_FILLED_OCCUPATION_SKILL_ID $LIKE :id")
    abstract fun getOneById(id: Long?): DbFilledSkill

    @Query("$SELECT_ALL_FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL $WHERE $FIELD_FILLED_OCCUPATION_SKILL_CHARACTER_ID $LIKE :id")
    abstract fun getAllByCharacterId(id: Long?): List<DbFilledSkill>

    @Query("$UPDATE $TABLE_NAME_FILLED_OCCUPATION_SKILL SET filledSkillTensValue = :tens WHERE filledSkillId = :id ")
    abstract fun updateTensValues(id: Long, tens: Int): Int

    @Query("$UPDATE $TABLE_NAME_FILLED_OCCUPATION_SKILL SET filledSkillTensValue = :units WHERE filledSkillId = :id ")
    abstract fun updateUnitsValues(id: Long, units: Int): Int


    //  DELETE
    @Query("$DELETE_FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL $WHERE $FIELD_FILLED_OCCUPATION_SKILL_CHARACTER_ID $LIKE :id")
    abstract fun deleteAllByCharacterId(id: Long): Int

    @Query("$DELETE_FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL ")
    abstract fun deleteAll(): Int
}