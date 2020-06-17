// DbFilledSkillDao.kt created by UldSkull - 14/06/2020

package com.uldskull.rolegameassistant.infrastructure.dao.skill

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_FILLED_OCCUPATION_SKILL_CHARACTER_ID
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_FILLED_OCCUPATION_SKILL_ID
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_FILLED_OCCUPATION_SKILL_TYPE
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
abstract class DbFilledOccupationSkillDao : GenericDao<DbFilledSkill> {
    //  READ
    @Query("SELECT * FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL")
    abstract fun getFilledSkill(): LiveData<List<DbFilledSkill>>

    @Query("SELECT * FROM DbFilledSkill WHERE $FIELD_FILLED_OCCUPATION_SKILL_TYPE LIKE :type")
    abstract fun getSkillsByTypes(type:Long):LiveData<List<DbFilledSkill>>


    @Query("SELECT * FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL $WHERE $FIELD_FILLED_OCCUPATION_SKILL_ID $LIKE :id")
    abstract fun getOneById(id: Long?): DbFilledSkill

    @Query("SELECT * FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL $WHERE $FIELD_FILLED_OCCUPATION_SKILL_CHARACTER_ID $LIKE :id")
    abstract fun getAllByCharacterId(id:Long?):List<DbFilledSkill>

    @Query("UPDATE $TABLE_NAME_FILLED_OCCUPATION_SKILL SET filledSkillTensValue = :tens WHERE filledSkillId = :id ")
    abstract fun updateTensValues(id:Long, tens:Int):Int

    @Query("UPDATE $TABLE_NAME_FILLED_OCCUPATION_SKILL SET filledSkillTensValue = :units WHERE filledSkillId = :id ")
    abstract fun updateUnitsValues(id:Long, units:Int):Int


    //  DELETE
    @Query("$DELETE_FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL $WHERE $FIELD_FILLED_OCCUPATION_SKILL_CHARACTER_ID $LIKE :id")
    abstract fun deleteAllByCharacterId(id: Long): Int

    @Query("$DELETE_FROM $TABLE_NAME_FILLED_OCCUPATION_SKILL ")
    abstract fun deleteAll(): Int
}