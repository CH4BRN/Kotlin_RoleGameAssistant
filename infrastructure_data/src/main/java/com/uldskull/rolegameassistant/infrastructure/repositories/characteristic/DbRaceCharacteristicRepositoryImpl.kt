// File DbRaceCharacteristicRepositoryImpl.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.characteristic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbRaceCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRaceCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRaceCharacteristic
import com.uldskull.rolegameassistant.repository.characteristic.RaceCharacteristicRepository

/**
 *   Class "DbRaceCharacteristicRepositoryImpl" :
 *   TODO: Fill class use.
 **/
class DbRaceCharacteristicRepositoryImpl(private val dbRaceCharacteristicDao: DbRaceCharacteristicDao) :
    RaceCharacteristicRepository<LiveData<List<DomainRaceCharacteristic>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainRaceCharacteristic>>? {
        try {
            return Transformations.map(dbRaceCharacteristicDao.getRaceCharacteristics()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e("DbRaceCharacteristicRepositoryImpl", "Get all failed ! ")
            throw e
        }
    }

    private fun List<DbRaceCharacteristic>.asDomainModel(): List<DomainRaceCharacteristic> {
        return map {
            DomainRaceCharacteristic(
                characteristicName = it.characteristicName,
                characteristicRaceId = it.characteristicRaceId,
                characteristicId = it.characteristicId,
                characteristicBonus = it.characteristicBonus
            )
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainRaceCharacteristic? {
        Log.d("testInsert", "findOneById")
        var result: DbRaceCharacteristic
        try {
            result = dbRaceCharacteristicDao.getRaceCharacteristicById(id)
        } catch (e: Exception) {
            Log.e("testInsert", "FAILED")
            throw e
        }
        return result.toDomain()
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainRaceCharacteristic>?): List<Long>? {
        Log.d("DbRaceCharacteristicRepositoryImpl", "insertAll")
        if ((all != null) && (all.isNotEmpty())) {
            try {
                val result = dbRaceCharacteristicDao.insertRaceCharacteristics(all.map { rc ->
                    DbRaceCharacteristic.from(
                        rc
                    )
                })

                Log.d("DbRaceCharacteristicRepositoryImpl", "insertAll RESULT = ${result.size}")
                return result
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        } else {
            Log.d("DbRaceCharacteristicRepositoryImpl", "insertAll RESULT = 0")
            return emptyList()
        }
    }

    /** Insert one entity  -  it can return a long, which is the new rowId for the inserted item.*/
    override fun insertOne(one: DomainRaceCharacteristic?): Long? {
        Log.d("DbRaceCharacteristicRepositoryImpl", "insertOne")
        return if (one != null) {
            try {
                val result =
                    dbRaceCharacteristicDao.insertRaceCharacteristic(DbRaceCharacteristic.from(one))
                Log.d("DbRaceCharacteristicRepositoryImpl", "insertOne RESULT = $result")
                result
            } catch (e: Exception) {
                throw e
            }
        } else {
            -1
        }
    }

    /** Delete all entities **/
    override fun deleteAll(): Int {
        Log.d("DbRaceCharacteristicRepositoryImpl", "deleteAll")
        try {
            return dbRaceCharacteristicDao.deleteAllRaceCharacteristics()
        } catch (e: Exception) {
            throw e
        }
    }
}