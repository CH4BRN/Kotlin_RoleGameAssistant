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
class DbBreedsCharacteristicRepositoryImpl(private val dbBreedCharacteristicDao: DbBreedCharacteristicDao) :
    BreedCharacteristicRepository<LiveData<List<DomainBreedCharacteristic>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainBreedCharacteristic>>? {
        try {
            return Transformations.map(dbBreedCharacteristicDao.getBreedCharacteristics()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e("DbBreedCharacteristicRepositoryImpl", "Get all failed ! ")
            throw e
        }
    }

    private fun List<DbBreedCharacteristic>.asDomainModel(): List<DomainBreedCharacteristic> {
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
        Log.d("test", "findOneById")
        var result: DbBreedCharacteristic
        try {
            result = dbBreedCharacteristicDao.getBreedCharacteristicById(id)
        } catch (e: Exception) {
            Log.e("test", "FAILED")
            throw e
        }
        return result.toDomain()
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainBreedCharacteristic>?): List<Long>? {
        Log.d("DbBreedCharacteristicRepositoryImpl", "insertAll")
        if ((all != null) && (all.isNotEmpty())) {
            try {
                val result = dbBreedCharacteristicDao.insertBreedCharacteristics(all.map { b ->
                    DbBreedCharacteristic.from(
                        b
                    )
                })

                Log.d("DbBreedsCharacteristicRepositoryImpl", "insertAll RESULT = ${result.size}")
                return result
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        } else {
            Log.d("DbBreedsCharacteristicRepositoryImpl", "insertAll RESULT = 0")
            return emptyList()
        }
    }

    /** Insert one entity  -  it can return a long, which is the new rowId for the inserted item.*/
    override fun insertOne(one: DomainBreedCharacteristic?): Long? {
        Log.d("test", "insertOne $one")
        return if (one != null) {
            try {
                val result =
                    dbBreedCharacteristicDao.insertBreedCharacteristic(
                        DbBreedCharacteristic.from(
                            one
                        )
                    )
                Log.d("test", "insertOne RESULT = $result")
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
        Log.d("DbBreedCharacteristicRepositoryImpl", "deleteAll")
        try {
            return dbBreedCharacteristicDao.deleteAllBreedCharacteristics()
        } catch (e: Exception) {
            throw e
        }
    }
}