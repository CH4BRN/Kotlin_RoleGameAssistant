// File DbOccupationSkillDao.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.skill

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_OCCUPATION_SKILL_ID
import com.uldskull.rolegameassistant.infrastructure.Queries.DELETE_ALL_OCCUPATION_SKILLS
import com.uldskull.rolegameassistant.infrastructure.Queries.SELECT_ALL_OCCUPATION_SKILLS
import com.uldskull.rolegameassistant.infrastructure.dao.GenericDao
import com.uldskull.rolegameassistant.infrastructure.dao.LIKE
import com.uldskull.rolegameassistant.infrastructure.dao.WHERE
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbSkillToCheck

/**
 *   Interface "DbOccupationSkillDao" :
 *  DbSkillToCheck database interactions.
 **/
@Dao
abstract class DbSkillToCheckDao : GenericDao<DbSkillToCheck> {

    //  READ
    @Query(SELECT_ALL_OCCUPATION_SKILLS)
    abstract fun getOccupationSkills(): LiveData<List<DbSkillToCheck>>

    @Query("$SELECT_ALL_OCCUPATION_SKILLS $WHERE $FIELD_OCCUPATION_SKILL_ID $LIKE :id")
    abstract fun getOccupationSkillById(id: Long?): DbSkillToCheck

    //  DELETE
    @Query(DELETE_ALL_OCCUPATION_SKILLS)
    abstract fun deleteAllOccupationSkills(): Int
}