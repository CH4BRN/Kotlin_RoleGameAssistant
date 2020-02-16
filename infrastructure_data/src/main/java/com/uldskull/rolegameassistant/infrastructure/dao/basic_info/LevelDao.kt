// LevelDao.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.dao.basic_info

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uldskull.rolegameassistant.infrastructure.ColumnNames.COLUMN_NAME_DB_LEVEL_ID
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.ALL
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.EQUAL
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.FROM
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.SELECT
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.WHERE
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_LEVEL
import com.uldskull.rolegameassistant.infrastructure.database_model.basic_info.DbLevel

/**
Interface "LevelDao"

TODO : Describe interface utility.
 **/
@Dao
interface LevelDao {
    @Query(SELECT + ALL + FROM + TABLE_NAME_LEVEL)
    fun findAll(): LiveData<List<DbLevel>>

    @Query(SELECT + ALL + FROM +  TABLE_NAME_LEVEL + WHERE + "dbLevelId " + EQUAL + COLUMN_NAME_DB_LEVEL_ID)
    fun findOneById(dbLevelId: Long?):DbLevel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dbLevel: DbLevel):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(dbLevels:List<DbLevel>)
}