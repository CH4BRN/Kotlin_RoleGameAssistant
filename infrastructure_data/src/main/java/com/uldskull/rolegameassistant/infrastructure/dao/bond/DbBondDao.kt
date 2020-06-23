// File DbBondDao.kt
// @Author pierre.antoine - 02/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.bond

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_BOND_ID
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_BOND
import com.uldskull.rolegameassistant.infrastructure.dao.*
import com.uldskull.rolegameassistant.infrastructure.database_model.db_bond.DbBond

/**
 *   Interface "DbBondDao" :
 *    DbBond database interactions.
 **/
@Dao
abstract class DbBondDao : GenericDao<DbBond> {

    //  READ
    @Query("$SELECT_ALL_FROM $TABLE_NAME_BOND")
    abstract fun getBonds(): LiveData<List<DbBond>>

    @Query("$SELECT_ALL_FROM $TABLE_NAME_BOND $WHERE $FIELD_BOND_ID $LIKE :id")
    abstract fun getBondById(id: Long?): DbBond

    //  DELETE
    @Query("$DELETE_FROM $TABLE_NAME_BOND")
    abstract fun deleteAllBonds(): Int
}