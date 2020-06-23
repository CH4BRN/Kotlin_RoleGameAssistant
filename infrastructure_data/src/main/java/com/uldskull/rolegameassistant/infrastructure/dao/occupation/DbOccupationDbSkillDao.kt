// File DbOccupationDbSkillDao.kt
// @Author pierre.antoine - 24/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.occupation

import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_OCCUPATION_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_OCCUPATIONS
import com.uldskull.rolegameassistant.infrastructure.dao.LIKE
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.WHERE
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupationAndDbSkillCrossRef
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupationWithDbSkills

/**
 *   Interface "DbOccupationDbSkillDao" :
 *   DbOccupation with DbSkill database interactions.
 **/
@Dao
interface DbOccupationDbSkillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCross(dbOccupationAndDbSkillCrossRef: DbOccupationAndDbSkillCrossRef):Long

    @Transaction
    @Query("$SELECT_ALL_FROM $TABLE_NAME_OCCUPATIONS")
    fun getOccupationsWithSkills():List<DbOccupationWithDbSkills>

    @Transaction
    @Query("$SELECT_ALL_FROM $TABLE_NAME_OCCUPATIONS $WHERE $FIELD_OCCUPATION_ID $LIKE :id")
    fun getOccupationWithSkills(id:Long?):DbOccupationWithDbSkills
}