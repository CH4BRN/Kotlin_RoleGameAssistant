// ClassDao.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.dao.basic_info

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uldskull.rolegameassistant.infrastructure.ColumnNames.COLUMN_NAME_DB_CLASS_ID
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.ALL
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.EQUAL
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.FROM
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.SELECT
import com.uldskull.rolegameassistant.infrastructure.QueriesWords.WHERE
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_CLASS
import com.uldskull.rolegameassistant.infrastructure.database_model.basic_info.DbClass

/**
Interface "ClassDao"

TODO : Describe interface utility.
 **/
@Dao
interface ClassDao {
    @Query(SELECT + ALL + FROM + TABLE_NAME_CLASS)
    fun findAll(): LiveData<List<DbClass>>

    @Query(SELECT + ALL + FROM + TABLE_NAME_CLASS + WHERE + "dbClassId " + EQUAL + COLUMN_NAME_DB_CLASS_ID)
    fun findOneById(dbClassId: Long?):DbClass

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dbClass: DbClass):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(dbClasses:List<DbClass>)
}