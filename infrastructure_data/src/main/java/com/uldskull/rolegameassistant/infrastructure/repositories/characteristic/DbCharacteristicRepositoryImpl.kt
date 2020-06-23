// File DbCharacteristicRepositoryImpl.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.characteristic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbCharacteristic
import com.uldskull.rolegameassistant.models.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository

/**
 *   Class "DbCharacteristicRepositoryImpl" :
 *   Repository for database characteristic
 **/
class DbCharacteristicRepositoryImpl(
    /**
     * Characteristic Dao
     */
    private val dbCharacteristicDao: DbCharacteristicDao
) : CharacteristicRepository<LiveData<List<DomainCharacteristic>>> {

    companion object {
        private const val TAG = "DbCharacteristicRepositoryImpl"
    }
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainCharacteristic>> {
        Log.d(TAG, "getAll")
        return Transformations.map(dbCharacteristicDao.getCharacteristics()){
            it?.asDomainModel()
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainCharacteristic? {
        Log.d(TAG, "findOneById")
        return dbCharacteristicDao.getCharacteristicById(id).toDomain()
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainCharacteristic>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if ((all != null) && (all.isNotEmpty())) {
            try {
                val result =
                    dbCharacteristicDao.insert(all.map { domainCharacteristic ->
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
        return dbCharacteristicDao.insert(DbCharacteristic.from(one))
    }

    /** Delete all entities **/
    override fun deleteAll(): Int {
        Log.d(TAG, "deleteAll")
        return dbCharacteristicDao.deleteAllCharacteristics()
    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainCharacteristic?): Int? {
        Log.d(TAG, "updateOne")
       return dbCharacteristicDao.update(DbCharacteristic.from(one))
    }

    /**
     * Converts a list of database entities into domain entities
     */
    private fun List<DbCharacteristic>.asDomainModel():List<DomainCharacteristic>{
        Log.d(TAG, "asDomainModel")
        return map{
            DomainCharacteristic(
                characteristicId = it.characteristicId,
                characteristicName = it.characteristicName
            )
        }
    }
}