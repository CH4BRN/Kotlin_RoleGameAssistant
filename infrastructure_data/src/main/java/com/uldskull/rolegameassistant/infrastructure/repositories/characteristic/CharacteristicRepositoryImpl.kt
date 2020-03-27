// File CharacteristicRepositoryImpl.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.characteristic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.DbCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository

/**
 *   Class "CharacteristicRepositoryImpl" :
 *   TODO: Fill class use.
 **/
class CharacteristicRepositoryImpl(private val dbCharacteristicDao: DbCharacteristicDao) :
    CharacteristicRepository<LiveData<List<DomainCharacteristic>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainCharacteristic>>? {
        try {
            return Transformations.map(dbCharacteristicDao.getCharacteristics()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            throw e
        }

    }

    private fun List<DbCharacteristic>.asDomainModel(): List<DomainCharacteristic> {
        return map {
            DomainCharacteristic(
                characteristicId = it.characteristicId,
                characteristicName = it.characteristicName
            )
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainCharacteristic? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainCharacteristic>?): List<Long> {
        Log.d("CharacteristicRepositoryImpl", "insertAll")
        if ((all != null) && (all.isNotEmpty())) {
            try {
                val result = dbCharacteristicDao.insertCharacteristics(all.map { c ->
                    DbCharacteristic.from(
                        c
                    )
                })
                Log.d("CharacteristicRepositoryImpl", "insertAll RESULT = ${result.size}")
                return result
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        } else {
            Log.d("CharacteristicRepositoryImpl", "insertOne RESULT = 0")
            return emptyList()
        }
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainCharacteristic?): Long {
        Log.d("CharacteristicRepositoryImpl", "insertOne")
        return if (one != null) {
            try {
                val result = dbCharacteristicDao.insertCharacteristic(DbCharacteristic.from(one))
                Log.d("CharacteristicRepositoryImpl", "insertOne RESULT = $result")
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
        Log.d("CharacteristicRepositoryImpl", "deleteAll")
        try {
            return dbCharacteristicDao.deleteAllCharacteristics()
        } catch (e: Exception) {
            throw e
        }

    }
}