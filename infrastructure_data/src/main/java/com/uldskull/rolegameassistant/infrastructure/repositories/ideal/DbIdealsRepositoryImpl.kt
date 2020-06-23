// File IdealsRepositoryImpl.kt
// @Author pierre.antoine - 06/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.ideal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.ideal.DbIdealsDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal.DbIdeal
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.repository.ideal.IdealsRepository

/**
 *   Class "IdealsRepositoryImpl" :
 *   Repository for ideal
 **/
class DbIdealsRepositoryImpl(
    /**
     * ideal dao
     */
    private val dbIdealDao: DbIdealsDao
) : IdealsRepository<LiveData<List<DomainIdeal>>> {
    companion object {
        private const val TAG = "DbIdealsRepositoryImpl"
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainIdeal>> {
        Log.d(TAG, "getAll")
        try {
            var ideals = dbIdealDao.getIdealsLiveData()
            Log.d(TAG, "ideals = " + ideals.value?.size.toString())
            //  Transform the dbIdeals into domain ideals
            return Transformations.map(ideals) {
                Log.d(TAG, "ideals size = ${it.size}")
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getAll FAILED")
            e.printStackTrace()
            throw  e
        }
    }

    /**
     * get ideals.
     */
    override fun getIdeals(): List<DomainIdeal> {
        Log.d(TAG, "getAll")
        try {
            var ideals: List<DbIdeal> = dbIdealDao?.getIdeals()
            return ideals?.map { ideal -> ideal.toDomain() }

        } catch (e: Exception) {
            Log.e("ERROR", "Get ideals failed")
            throw e
        }
    }
    /**
     * Converts a list of database entities into domain entities
     */
    private fun List<DbIdeal>.asDomainModel(): List<DomainIdeal> {
        Log.d(TAG, "asDomainModel")
        return map {
            DomainIdeal(
                idealId = it.idealId,
                idealGoodPoints = it.idealGoodPoints,
                idealEvilPoints = it.idealEvilPoints,
                idealName = it.idealName,
                isChecked = false
            )
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainIdeal? {
        Log.d(TAG, "findOneById : $id")
        var result: DbIdeal
        try {
            result = dbIdealDao.getIdealById(id)
        } catch (e: Exception) {
            Log.e(TAG, "findOneById FAILED")
            e.printStackTrace()
            throw e
        }
        if (result != null) {
            return result.toDomain()
        } else {
            return null
        }

    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainIdeal>?): List<Long>? {
        Log.d(TAG, "insertAll")
        if ((all != null) && (all.isNotEmpty())) {
            try {
                val result = dbIdealDao.insert(all.map { i ->
                    DbIdeal.from(
                        i
                    )
                })
                return result
            } catch (e: Exception) {
                Log.e(TAG, "insertAll FAILED")
                e.printStackTrace()
                throw e
            }
        } else {
            return emptyList()
        }
    }

    /**
     * delete one ideal
     */
    override fun deleteOne(ideal: DomainIdeal): Int {
        Log.d(TAG, "deleteOne")
        if (ideal == null) {
            throw Exception("ERROR : Ideal is null.")
        }
        return dbIdealDao?.delete(DbIdeal.from(ideal))
    }

    /** Insert one entity  -  it can return a long, which is the new rowId for the inserted item.*/
    override fun insertOne(one: DomainIdeal?): Long? {
        Log.d(TAG, "insertOne")
        if (one == null) {
            throw Exception("ERROR : Ideal is null.")
        }
        try {
            return dbIdealDao.insert(DbIdeal.from(one))
        } catch (e: Exception) {
            Log.e(TAG, "insertOne FAILED")
            e.printStackTrace()
            throw  e
        }

    }

    /** Delete all entities **/
    override fun deleteAll(): Int {
        Log.d(TAG, "deleteAll")
        try {
            return dbIdealDao.deleteAllIdeals()
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainIdeal?): Int? {
        Log.d(TAG, "updateOne")
        try {
            return dbIdealDao.update(DbIdeal.from(one))
        } catch (e: Exception) {
            Log.e(TAG, "updateOne FAILED")
            e.printStackTrace()
            throw e
        }
    }


}