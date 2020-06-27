// File BreedsRepositoryImpl.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.breed

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.breed.DbDisplayedBreedDao
import com.uldskull.rolegameassistant.infrastructure.dao.breed.DbBreedWithDbCharacteristicsDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds.DbDisplayedBreed
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds.DbDisplayedBreedWithCharacteristics
import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreedWithCharacteristics
import com.uldskull.rolegameassistant.models.characteristic.DomainBreedsCharacteristic
import com.uldskull.rolegameassistant.repository.breed.DisplayedBreedsRepository

/**
 *   Class "BreedsRepositoryImpl" :
 *   Repository for displayed breed.
 **/
class DbDisplayedBreedsRepositoryImpl(
    /**
     * Displayed breed dao
     */
    private val dbDisplayedBreedDao: DbDisplayedBreedDao,
    /**
     * Displayed breed with characteristics dao.
     */
    private val dbBreedWithDbCharacteristicsDao: DbBreedWithDbCharacteristicsDao
) :
    DisplayedBreedsRepository<LiveData<List<DomainDisplayedBreed?>>> {

    companion object {
        private const val TAG = "DbDisplayedBreedsRepositoryImpl"
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainDisplayedBreed?>>? {
        Log.d(TAG, "getAll")
        try {
            //  Transform the dbDisplayedBreeds into domain displayed breeds
            return Transformations.map(dbDisplayedBreedDao.getBreeds()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /** Get one entity by its id    */
    override suspend fun findOneById(id: Long?): DomainDisplayedBreed? {
        Log.d(TAG, "findOneById")
        Log.d("DEBUG$TAG", " Breed Id : ${id.toString()}")
        // Check if ID is null
        if (id == null) {
            return null
        }

        var result: DbDisplayedBreed? = null
        try {

            result = dbDisplayedBreedDao.getBreedById(id)

        } catch (e: Exception) {
            Log.e(TAG, "findOneById FAILED")
            e.printStackTrace()
            throw e
        }
        if (result == null) {
            return null
        }
        return result?.toDomain()
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override suspend fun insertAll(all: List<DomainDisplayedBreed>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if (all == null) {
            return emptyList()
        }
        if (all.isEmpty()) {
            return emptyList()
        }
        var result: List<Long> = emptyList()
        try {
            result = dbDisplayedBreedDao.insert(all.map { result ->
                DbDisplayedBreed.from(
                    result
                )
            })

        } catch (e: Exception) {
            Log.e(TAG, "insertAll FAILED")
            e.printStackTrace()
            throw e
        }
        return result

    }

    /** Insert one entity  -  it can return a long, which is the new rowId for the inserted item.*/
    override suspend fun insertOne(one: DomainDisplayedBreed?): Long? {
        Log.d(TAG, "insertOne")
        if (one == null) {
            return -1
        }
        var result: Long? = null
        try {

            result = dbDisplayedBreedDao.insert(DbDisplayedBreed.from(one))

        } catch (e: Exception) {
            Log.e(TAG, "insertOne FAILED")
            e.printStackTrace()
            throw  e
        }
        return result
    }

    /** Delete all entities **/
    override suspend fun deleteAll(): Int? {
        Log.d(TAG, "deleteAll")
        var result: Int? = null
        try {
            AsyncTask.execute {
                result = dbDisplayedBreedDao.deleteAllBreeds()
            }
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }
        return result
    }

    /**
     * Converts a list of database entities into domain entities
     */
    private fun List<DbDisplayedBreed>.asDomainModel(): List<DomainDisplayedBreed> {
        Log.d(TAG, "asDomainModel")
        return map {
            DomainDisplayedBreed(
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
    override fun findAllWithChildren(): List<DomainDisplayedBreedWithCharacteristics> {
        Log.d(TAG, "findAllWithChildren")

        val getBreedsWithCharacteristicsResultDisplayed: List<DbDisplayedBreedWithCharacteristics> =
            dbBreedWithDbCharacteristicsDao.getBreedsWithCharacteristics()

        val domainDisplayedBreedsWithCharacteristics: MutableList<DomainDisplayedBreedWithCharacteristics> =
            mutableListOf()

        if (getBreedsWithCharacteristicsResultDisplayed.isNotEmpty()) {
            var count = 0

            getBreedsWithCharacteristicsResultDisplayed.forEach { it ->
                val characteristics = mutableListOf<DomainBreedsCharacteristic>()
                val domainBreed = it.displayedBreed.toDomain()

                it.characteristics.forEach {
                    characteristics.add(it.toDomain())
                }
                val domainBreedWithCharacteristics =
                    DomainDisplayedBreedWithCharacteristics(
                        displayedBreed = domainBreed,
                        characteristics = characteristics
                    )
                domainDisplayedBreedsWithCharacteristics.add(domainBreedWithCharacteristics)
                count++
            }
            return domainDisplayedBreedsWithCharacteristics
        }
        return emptyList()
    }

    /**
     * Find one breed with all its associated characteristics.
     */
    override fun findOneWithChildren(id: Long?): DomainDisplayedBreedWithCharacteristics? {
        Log.d(TAG, "findOneWithChildren")
        val getDisplayedBreedWithCharacteristicResult: DbDisplayedBreedWithCharacteristics =
            dbBreedWithDbCharacteristicsDao.getBreedWithCharacteristics(id)

        if (getDisplayedBreedWithCharacteristicResult != null) {
            val characteristics = mutableListOf<DomainBreedsCharacteristic>()
            val domainBreed = getDisplayedBreedWithCharacteristicResult.displayedBreed.toDomain()


            getDisplayedBreedWithCharacteristicResult.characteristics.forEach {
                characteristics.add(it.toDomain())
            }

            return DomainDisplayedBreedWithCharacteristics(
                displayedBreed = domainBreed,
                characteristics = characteristics
            )


        }
        return null
    }

    /**  Update one entity  **/
    override suspend fun updateOne(one: DomainDisplayedBreed?): Int? {
        Log.d(TAG, "updateOne")
        return dbDisplayedBreedDao.update(DbDisplayedBreed.from(one))
    }
}
