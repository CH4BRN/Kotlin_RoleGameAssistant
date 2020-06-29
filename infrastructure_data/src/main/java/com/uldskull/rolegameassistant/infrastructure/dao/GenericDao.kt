// GenericDao.kt created by UldSkull - 14/06/2020

package com.uldskull.rolegameassistant.infrastructure.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

/**
 * Interface "GenericDao"
 **/
@Dao
interface GenericDao<T> {
    @Insert
    suspend fun insert(obj: T): Long

    @Insert
    suspend fun insert(obj: List<T>): List<Long>

    @Update
    suspend fun update(obj: T): Int

    @Delete
    suspend fun delete(obj: T): Int
}


