// File DbRaceWithDbBonusCharacteristicsDao.kt
// @Author pierre.antoine - 29/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.db_race.DbRaceWithCharacteristics

/**
 *   Class "DbRaceWithDbBonusCharacteristicsDao" :
 *   TODO: Fill class use.
 **/
@Dao
interface DbRaceWithDbBonusCharacteristicsDao {

    @Transaction
    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_RACE}")
    fun getRaceWithCharacteristics(): List<DbRaceWithCharacteristics>
}