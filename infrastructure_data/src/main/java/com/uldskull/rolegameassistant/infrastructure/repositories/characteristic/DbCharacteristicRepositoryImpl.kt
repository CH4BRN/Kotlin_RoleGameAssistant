// File DbCharacteristicRepositoryImpl.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.characteristic

import android.util.Log
import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository

/**
 *   Class "DbCharacteristicRepositoryImpl" :
 *   TODO: Fill class use.
 **/
class DbCharacteristicRepositoryImpl(
    private val dbCharacteristicDao: DbCharacteristicDao
) : CharacteristicRepository<LiveData<List<DomainCharacteristic>>> {

    companion object {
        private const val TAG = "DbCharacteristicRepositoryImpl"
    }
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainCharacteristic>>? {
        Log.d(TAG, "getAll")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainCharacteristic? {
        Log.d(TAG, "findOneById")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainCharacteristic>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if ((all != null) && (all.isNotEmpty())) {
            try {
                val result =
                    dbCharacteristicDao.insertCharacteristics(all.map { domainCharacteristic ->
                        DbCharacteristic.from(
                            domainCharacteristic
                        )
                    })
                Log.d(TAG, "insertAll RESULT = ${result.size}")
                return result
            } catch (e: Exception) {
                Log.e(TAG, "insertAll FAILED")
                e.printStackTrace()
                throw e
            }
        } else {
            Log.d(TAG, "insertAll RESULT = 0")
            return emptyList()
        }
    }

    /** Insert one entity  -  it can return a long, which is the new rowId for the inserted item.*/
    override fun insertOne(one: DomainCharacteristic?): Long? {
        Log.d(TAG, "insertOne")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Delete all entities **/
    override fun deleteAll(): Int {
        Log.d(TAG, "deleteAll")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainCharacteristic?): Int? {
        Log.d(TAG, "updateOne")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
// TODO : Fill class.
}