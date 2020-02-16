// BasicInfoDao.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.infrastructure.dao.basic_info

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uldskull.rolegameassistant.infrastructure.ColumnNames.COLUMN_NAME_DB_BASIC_INFO_ID
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.ALL
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.EQUAL
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.FROM
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.SELECT
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.WHERE
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_BASIC_INFO
import com.uldskull.rolegameassistant.infrastructure.database_model.basic_info.DbBasicInfo

/**
Interface "BasicInfoDao"

Database interactions for basic info.
 **/
@Dao
interface BasicInfoDao {
    @Query(SELECT + ALL + FROM + TABLE_NAME_BASIC_INFO)
    fun findAll(): LiveData<List<DbBasicInfo>>

    @Query(SELECT + ALL + FROM +  TABLE_NAME_BASIC_INFO + WHERE + "dbBasicInfoId " + EQUAL + COLUMN_NAME_DB_BASIC_INFO_ID)
    fun findOneById(dbBasicInfoId: Long?):DbBasicInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dbBasicInfo: DbBasicInfo):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(dbBasicInfos:List<DbBasicInfo>)
}