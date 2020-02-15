// BasicInfoDao.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.infrastructure.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.TABLE_NAME_BASIC_INFO
import com.uldskull.rolegameassistant.infrastructure.database_model.basic_info.DbBasicInfo

/**
Interface "BasicInfoDao"

Database interactions for basic info.
 **/
@Dao
interface BasicInfoDao {
    @Query("SELECT * FROM $TABLE_NAME_BASIC_INFO")
    fun getAll(): LiveData<List<DbBasicInfo>>

    @Query("SELECT * FROM $TABLE_NAME_BASIC_INFO WHERE dbBasicInfoId = :dbBasicInfoId")
    fun getById(dbBasicInfoId: Long?):DbBasicInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg dbBasicInfos: DbBasicInfo)
}