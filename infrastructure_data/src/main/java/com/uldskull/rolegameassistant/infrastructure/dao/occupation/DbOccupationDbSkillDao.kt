// File DbOccupationDbSkillDao.kt
// @Author pierre.antoine - 24/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.occupation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.dao.LIKE
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.WHERE
import com.uldskull.rolegameassistant.infrastructure.database_model.DbOccupation_id
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupationAndDbSkillCrossRef
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupationWithDbSkills

/**
 *   Interface "DbOccupationDbSkillDao" :
 *   TODO: Fill interface use.
 **/
@Dao
interface DbOccupationDbSkillDao {
    @Insert
    fun insertCross(dbOccupationAndDbSkillCrossRef: DbOccupationAndDbSkillCrossRef):Long

    @Transaction
    @Query("$SELECT_ALL_FROM ${DatabaseValues.TABLE_NAME_OCCUPATIONS}")
    fun getOccupationsWithSkills():List<DbOccupationWithDbSkills>

    @Transaction
    @Query("$SELECT_ALL_FROM ${DatabaseValues.TABLE_NAME_OCCUPATIONS} $WHERE $DbOccupation_id $LIKE :id")
    fun getOccupationWithSkills(id:Long?):DbOccupationWithDbSkills
}