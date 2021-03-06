// File DbBreedCharacteristicRepositoryImpl.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.characteristic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbBreedCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbBreedCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedCharacteristic
import com.uldskull.rolegameassistant.repository.characteristic.BreedCharacteristicRepository

/**
 *   Class "DbBreedCharacteristicRepositoryImpl" :
 *   TODO: Fill class use.
 **/
class DbBreedsCharacteristicRepositoryImpl(
    private val dbBreedCharacteristicDao: DbBreedCharacteristicDao
) :
    BreedCharacteristicRepository<LiveData<List<DomainBreedCharacteristic>>> {
    companion object {
        private const val TAG = "DbBreedsCharacteristicRepositoryImpl"
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainBreedCharacteristic>>? {
        Log.d(TAG, "getAll")
        try {
            return Transformations.map(dbBreedCharacteristicDao.getBreedCharacteristics()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getAll FAILED ! ")
            throw e
        }
    }

    private fun List<DbBreedCharacteristic>.asDomainModel(): List<DomainBreedCharacteristic> {
        Log.d(TAG, "asDomainModel")
        return map {
            DomainBreedCharacteristic(
                characteristicName = it.characteristicName,
                characteristicBreedId = it.characteristicBreedId,
                characteristicId = it.characteristicId,
                characteristicBonus = it.characteristicBonus
            )
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainBreedCharacteristic? {
        Log.d(TAG, "findOneById")
        var result: DbBreedCharacteristic
        try {
            result = dbBreedCharacteristicDao.getBreedCharacteristicById(id)
        } catch (e: Exception) {
            Log.e(TAG, "findOneById FAILED")
            throw e
        }
        return result.toDomain()
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainBreedCharacteristic>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if ((all != null) && (all.isNotEmpty())) {
            try {
                val result = dbBreedCharacteristicDao.insertBreedCharacteristics(all.map { b ->
                    DbBreedCharacteristic.from(
                        b
                    )
                })

                Log.d(TAG, "insertAll RESULT = ${result.size}")
                return result
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        } else {
            Log.d(TAG, "insertAll RESULT = 0")
            return emptyList()
        }
    }

    /** Insert one entity  -  it can return a long, which is the new rowId for the inserted item.*/
    override fun insertOne(one: DomainBreedCharacteristic?): Long? {
        Log.d(TAG, "insertOne $one")
        return if (one != null) {
            try {
                val result =
                    dbBreedCharacteristicDao.insertBreedCharacteristic(
                        DbBreedCharacteristic.from(
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
            return dbBreedCharacteristicDao.deleteAllBreedCharacteristics()
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainBreedCharacteristic?): Int? {
        Log.d(TAG, "updateOne")
        try {
            dbBreedCharacteristicDao.updateBreedCharacteristic(DbBreedCharacteristic.from(one))
        } catch (e: Exception) {
            Log.e(TAG, "updateOne FAILED")
            e.printStackTrace()
            throw e
        }
        return 1
    }
}