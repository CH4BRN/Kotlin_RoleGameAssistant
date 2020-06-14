// GenericDao.kt created by UldSkull - 14/06/2020

package com.uldskull.rolegameassistant.infrastructure.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

/**
Interface "GenericDao"

TODO : Describe interface utility.
 **/
@Dao
open interface GenericDao<T> {
    @Insert
    fun insert(obj: T):Long

    @Insert
    fun insert(obj:List<T>):List<Long>

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)
}


