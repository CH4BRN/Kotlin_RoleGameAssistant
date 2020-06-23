// File DbRollCharacteristicRepositoryImpl.kt
// @Author pierre.antoine - 10/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.characteristic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbRollCharacteristicsDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRollCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.repository.characteristic.RollsCharacteristicRepository

/**
 *   Class "DbRollCharacteristicRepositoryImpl" :
 *   Repository for dbRoll characteristics
 **/
class DbRollsCharacteristicRepositoryImpl(
    private val dbRollCharacteristicsDao: DbRollCharacteristicsDao
) :
    RollsCharacteristicRepository<LiveData<List<DomainRollsCharacteristic>>> {

    companion object {
        private const val TAG = "DbRollCharacteristicRepositoryImpl"
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainRollsCharacteristic>> {
        Log.d(TAG, "getAll")
        try {
            return Transformations.map(dbRollCharacteristicsDao.getRollCharacteristics()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getAll FAILED")
            e.printStackTrace()
            throw e
        }
    }
    /**
     * Converts a list of database entities into domain entities
     */
    private fun List<DbRollCharacteristic>.asDomainModel(): List<DomainRollsCharacteristic> {
        Log.d(TAG, "asDomainModel")
        return map {
            DomainRollsCharacteristic(
                characteristicId = it.characteristicId,
                characteristicName = it.characteristicName,
                characteristicBonus = it.characteristicBonus,
                characteristicTotal = it.characteristicTotal,
                characteristicRoll = it.characteristicRoll,
                characteristicMax = it.characteristicMax,
                characteristicRollRule = it.characteristicRollRule
            )
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainRollsCharacteristic? {
        Log.d(TAG, "findOndById")
        var result: DbRollCharacteristic
        try {
            result = dbRollCharacteristicsDao.getRollCharacteristicById(id)
        } catch (e: Exception) {
            Log.e(TAG, "findOneById FAILED")
            throw e
        }
        return result.toDomain()
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainRollsCharacteristic>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if ((all != null) && (all.isNotEmpty())) {
            try {
                val result = dbRollCharacteristicsDao.insert(all.map { rc ->
                    DbRollCharacteristic.from(
                        rc
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
    override fun insertOne(one: DomainRollsCharacteristic?): Long? {
        Log.d(TAG, "insertOne")
        return if (one != null) {
            try {
                val result =
                    dbRollCharacteristicsDao.insert(
                        DbRollCharacteristic.from(
                            one
                        )
                    )
                Log.d(TAG, "insertOne RESULT = $result")
                result
            } catch (e: Exception) {
                Log.e(TAG, "insertOne FAILED")
                e.printStackTrace()
                throw e
            }
        } else {
            -1
        }
    }

    /** Delete all entities **/
    override fun deleteAll(): Int {
        Log.d(TAG, "deleteAll")
        try {
            return dbRollCharacteristicsDao.deleteAllRollCharacteristics()
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }

    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainRollsCharacteristic?): Int? {
        Log.d(TAG, "updateOne")
        try {
            dbRollCharacteristicsDao.update(DbRollCharacteristic.from(one))
        } catch (e: Exception) {
            Log.e(TAG, "updateOne FAILED")
            e.printStackTrace()
            throw e
        }
        return 1
    }
}