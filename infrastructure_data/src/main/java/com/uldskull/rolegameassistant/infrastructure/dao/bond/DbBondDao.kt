// File DbBondDao.kt
// @Author pierre.antoine - 02/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.bond

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.db_bond.DbBond

/**
 *   Interface "DbBondDao" :
 *    DbBond database interactions.
 **/
@Dao
interface DbBondDao {
    //  CREATE
    @Insert
    fun inserBond(dbBond: DbBond): Long

    @Insert
    fun insertBonds(dbBonds: List<DbBond>): List<Long>

    //  READ
    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_BOND}")
    fun getBonds(): LiveData<List<DbBond>>

    @Query("SELECT * FROM ${DatabaseValues.TABLE_NAME_BOND} WHERE bondId LIKE :id")
    fun getBondById(id: Long?): DbBond

    //  UPDATE
    @Update
    fun updateBond(vararg bonds: DbBond): Int

    //  DELETE
    @Delete
    fun deleteBonds(vararg bonds: DbBond): Int

    @Query("DELETE FROM ${DatabaseValues.TABLE_NAME_BOND}")
    fun deleteAllBonds(): Int
}