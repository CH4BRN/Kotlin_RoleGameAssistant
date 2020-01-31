// File CharacterDao.kt
// @Author pierre.antoine - 30/01/2020 - No copyright.

package com.uldskull.rolegameassistant.framework.infrastructure

import androidx.room.Dao
import androidx.room.Insert

/**
 *   Interface "CharacterDao" :
 *   Allow interaction with SqLite database
 **/
@Dao
interface CharacterDao {

    @Insert
    suspend fun insert()
}