// RaceDao.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.dao.basic_info

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uldskull.rolegameassistant.infrastructure.ColumnNames.COLUMN_NAME_DB_RACE_ID
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.ALL
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.EQUAL
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.FROM
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.SELECT
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.WHERE
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_RACE
import com.uldskull.rolegameassistant.infrastructure.database_model.basic_info.DbRace

/**
Interface "RaceDao"

TODO : Describe interface utility.
 **/
@Dao
interface RaceDao {
    @Query(SELECT + ALL + FROM + TABLE_NAME_RACE)
    fun findAll(): LiveData<List<DbRace>>

    @Query(SELECT + ALL + FROM +  TABLE_NAME_RACE + WHERE + "dbRaceId " + EQUAL + COLUMN_NAME_DB_RACE_ID)
    fun findOneById(dbRaceId: Long?):DbRace

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dbRace: DbRace):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(dbRaces:List<DbRace>)
}