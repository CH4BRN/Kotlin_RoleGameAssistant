// File RaceRepositoryImpl.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.race

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.DbRaceDao
import com.uldskull.rolegameassistant.infrastructure.database_model.DbRace
import com.uldskull.rolegameassistant.models.character.DomainRace
import com.uldskull.rolegameassistant.repository.race.RaceRepository

/**
 *   Class "RaceRepositoryImpl" :
 *   TODO: Fill class use.
 **/
class RaceRepositoryImpl(private val dbRaceDao: DbRaceDao) :
    RaceRepository<LiveData<List<DomainRace>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainRace>>? {
        try {
            //  Transform the dbRaces into domain races
            return Transformations.map(dbRaceDao.getRaces()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            throw e
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainRace? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainRace>?): List<Long>? {
        Log.d("RaceRepositoryImpl", "insertAll")
        if ((all != null) && (all.isNotEmpty())) {
            try {
                val result = dbRaceDao.insertRaces(all.map { r ->
                    DbRace.from(
                        r
                    )
                })
                Log.d("RaceRepositoryImpl", "insertAll RESULT = ${result.size}")
                return result
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        } else {
            Log.d("RaceRepositoryImpl", "insertAll RESULT = 0")
            return emptyList()
        }
    }

    /** Insert one entity  -  it can return a long, which is the new rowId for the inserted item.*/
    override fun insertOne(one: DomainRace?): Long {
        Log.d("RaceRepositoryImpl", "insertOne")
        return if (one != null) {
            try {
                val result = dbRaceDao.insertRace(DbRace.from(one))
                Log.d("RaceRepositoryImpl", "insertOne RESULT = $result")
                result
            } catch (e: Exception) {
                throw  e
            }
        } else {
            -1
        }
    }

    /** Delete all entities **/
    override fun deleteAll(): Int {
        Log.d("RaceRepositoryImpl", "deleteAll")
        try {
            return dbRaceDao.deleteAllRaces()
        } catch (e: Exception) {
            throw e
        }
    }

    private fun List<DbRace>.asDomainModel(): List<DomainRace> {
        return map {
            DomainRace(
                raceId = it.raceId,
                raceName = it.raceName,
                raceDescription = it.raceDescription
            )
            //,                raceCharacteristics = it.raceCharacteristics.map { characteristic -> characteristic.toDomain() })
        }

    }
}
// TODO : Fill class.
