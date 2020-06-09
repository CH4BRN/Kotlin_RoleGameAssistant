// File DbBondDao.kt
// @Author pierre.antoine - 02/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.bond

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_BOND_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_BOND
import com.uldskull.rolegameassistant.infrastructure.dao.DELETE_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.LIKE
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.WHERE
import com.uldskull.rolegameassistant.infrastructure.database_model.db_bond.DbBond

/**
 *   Interface "DbBondDao" :
 *    DbBond database interactions.
 **/
@Dao
interface DbBondDao {
    //  CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBond(dbBond: DbBond): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBonds(dbBonds: List<DbBond>): List<Long>

    //  READ
    @Query("$SELECT_ALL_FROM $TABLE_NAME_BOND")
    fun getBonds(): LiveData<List<DbBond>>

    @Query("$SELECT_ALL_FROM $TABLE_NAME_BOND $WHERE $FIELD_BOND_ID $LIKE :id")
    fun getBondById(id: Long?): DbBond

    //  UPDATE
    @Update
    fun updateBond(vararg bonds: DbBond): Int

    //  DELETE
    @Delete
    fun deleteBonds(vararg bonds: DbBond): Int

    @Query("$DELETE_FROM $TABLE_NAME_BOND")
    fun deleteAllBonds(): Int
}