// File BreedsRepositoryImpl.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.breed

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
    override fun findOneById(id: Long?): DomainDisplayedBreed? {
        Log.d(TAG, "findOneById")
        Log.d("DEBUG$TAG"," Breed Id : ${id.toString()}")
        val result: DbDisplayedBreed
        try {
            result = dbDisplayedBreedDao.getBreedById(id)
        } catch (e: Exception) {
            Log.e(TAG, "findOneById FAILED")
            e.printStackTrace()
            throw e
        }
        if(result != null){
            return result.toDomain()
        }
        return null

    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainDisplayedBreed>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if ((all != null) && (all.isNotEmpty())) {
            try {
                val result = dbDisplayedBreedDao.insert(all.map { result ->
                    DbDisplayedBreed.from(
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
    override fun insertOne(one: DomainDisplayedBreed?): Long {
        Log.d(TAG, "insertOne")
        return if (one != null) {
            try {
                val result = dbDisplayedBreedDao.insert(DbDisplayedBreed.from(one))
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
            return dbDisplayedBreedDao.deleteAllBreeds()
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }
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
    override fun updateOne(one: DomainDisplayedBreed?): Int? {
        Log.d(TAG, "updateOne")
        return dbDisplayedBreedDao.update(DbDisplayedBreed.from(one))
    }
}
