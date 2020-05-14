// File BreedsRepositoryImpl.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.breed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.breed.DbBreedDao
import com.uldskull.rolegameassistant.infrastructure.dao.breed.DbBreedWithDbCharacteristicsDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.DbBreed
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.DbBreedWithCharacteristics
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.models.character.breed.DomainBreedWithCharacteristics
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedCharacteristic
import com.uldskull.rolegameassistant.repository.breed.BreedsRepository

/**
 *   Class "BreedsRepositoryImpl" :
 *   TODO: Fill class use.
 **/
class DbBreedsRepositoryImpl(
    private val dbBreedDao: DbBreedDao,
    private val dbBreedWithDbCharacteristicsDao: DbBreedWithDbCharacteristicsDao
) :
    BreedsRepository<LiveData<List<DomainBreed>>> {

    companion object {
        private const val TAG = "DbBreedsRepositoryImpl"
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainBreed>>? {
        Log.d(TAG, "getAll")
        try {
            //  Transform the dbBreeds into domain breeds
            return Transformations.map(dbBreedDao.getBreeds()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainBreed? {
        Log.d(TAG, "findOneById")
        var result: DbBreed
        try {
            result = dbBreedDao.getBreedById(id)
        } catch (e: Exception) {
            Log.e(TAG, "findOneById FAILED")
            e.printStackTrace()
            throw e
        }
        return result.toDomain()
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainBreed>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if ((all != null) && (all.isNotEmpty())) {
            try {
                val result = dbBreedDao.insertBreeds(all.map { result ->
                    DbBreed.from(
                        result
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
    override fun insertOne(one: DomainBreed?): Long {
        Log.d(TAG, "insertOne")
        return if (one != null) {
            try {
                val result = dbBreedDao.insertBreed(DbBreed.from(one))
                Log.d(TAG, "insertOne RESULT = $result")
                result
            } catch (e: Exception) {
                Log.e(TAG, "insertOne FAILED")
                e.printStackTrace()
                throw  e
            }
        } else {
            -1
        }
    }

    /** Delete all entities **/
    override fun deleteAll(): Int {
        Log.d(TAG, "deleteAll")
        try {
            return dbBreedDao.deleteAllBreeds()
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    private fun List<DbBreed>.asDomainModel(): List<DomainBreed> {
        Log.d(TAG, "asDomainModel")
        return map {
            DomainBreed(
                breedId = it.breedId,
                breedName = it.breedName,
                breedDescription = it.breedDescription,
                breedHealthBonus = it.breedHealthBonus
            )
        }
    }

    /**
     * Find all breeds with its characteristics
     */
    override fun findAllWithChildren(): List<DomainBreedWithCharacteristics> {
        Log.d(TAG, "findAllWithChildren")

        val getBreedsWithCharacteristicsResult: List<DbBreedWithCharacteristics> =
            dbBreedWithDbCharacteristicsDao.getBreedsWithCharacteristics()

        var domainBreedsWithCharacteristics: MutableList<DomainBreedWithCharacteristics> =
            mutableListOf()

        if (getBreedsWithCharacteristicsResult.isNotEmpty()) {
            var count = 0

            getBreedsWithCharacteristicsResult.forEach {
                var characteristics = mutableListOf<DomainBreedCharacteristic>()
                var domainBreed = it.breed.toDomain()

                it.characteristics.forEach {
                    characteristics.add(it.toDomain())
                }
                var domainBreedWithCharacteristics =
                    DomainBreedWithCharacteristics(
                        breed = domainBreed,
                        characteristics = characteristics
                    )
                domainBreedsWithCharacteristics.add(domainBreedWithCharacteristics)
                count++
            }
            return domainBreedsWithCharacteristics
        }
        return emptyList()
    }

    override fun findOneWithChildren(id: Long?): DomainBreedWithCharacteristics? {
        Log.d(TAG, "findOneWithChildren")
        val getBreedWithCharacteristicResult: DbBreedWithCharacteristics =
            dbBreedWithDbCharacteristicsDao.getBreedWithCharacteristics(id)

        if (getBreedWithCharacteristicResult != null) {
            var characteristics = mutableListOf<DomainBreedCharacteristic>()
            var domainBreed = getBreedWithCharacteristicResult.breed.toDomain()


            getBreedWithCharacteristicResult.characteristics.forEach {
                characteristics.add(it.toDomain())
            }

            return DomainBreedWithCharacteristics(
                breed = domainBreed,
                characteristics = characteristics
            )


        }

        return null
    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainBreed?): Int? {
        Log.d(TAG, "updateOne")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
