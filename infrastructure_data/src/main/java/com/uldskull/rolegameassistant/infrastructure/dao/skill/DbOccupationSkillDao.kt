// File DbOccupationSkillDao.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.skill

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_OCCUPATION_SKILL_ID
import com.uldskull.rolegameassistant.infrastructure.Queries.DELETE_ALL_OCCUPATION_SKILLS
import com.uldskull.rolegameassistant.infrastructure.Queries.SELECT_ALL_OCCUPATION_SKILLS
import com.uldskull.rolegameassistant.infrastructure.dao.LIKE
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.WHERE
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbOccupationSkill

/**
 *   Interface "DbOccupationSkillDao" :
 *   TODO: Fill interface use.
 **/
@Dao
interface DbOccupationSkillDao {
    //  CREATE
    @Insert
    fun insertOccupationSkill(dbOccupationSkill: DbOccupationSkill): Long

    @Insert
    fun insertOccupationSkills(dbOccupationSkills: List<DbOccupationSkill>): List<Long>

    //  READ
    @Query(SELECT_ALL_OCCUPATION_SKILLS)
    fun getOccupationSkills(): LiveData<List<DbOccupationSkill>>

    @Query("$SELECT_ALL_OCCUPATION_SKILLS $WHERE $FIELD_OCCUPATION_SKILL_ID $LIKE :id")
    fun getOccupationSkillById(id: Long?): DbOccupationSkill

    //  UPDATE
    @Update
    fun updateOccupationSkill(vararg dbOccupationSkill: DbOccupationSkill): Int

    //  DELETE
    @Delete
    fun deleteOccupationSkills(vararg dbOccupationSkills: DbOccupationSkill): Int

    @Query(DELETE_ALL_OCCUPATION_SKILLS)
    fun deleteAllOccupationSkills(): Int
}