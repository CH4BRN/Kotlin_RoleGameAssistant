// File RaceRepositoryImpl.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.race

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.DbRaceDao
import com.uldskull.rolegameassistant.infrastructure.dao.DbRaceWithDbBonusCharacteristicsDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_race.DbRace
import com.uldskull.rolegameassistant.infrastructure.database_model.db_race.DbRaceWithCharacteristics
import com.uldskull.rolegameassistant.models.character.DomainRace
import com.uldskull.rolegameassistant.repository.race.RaceRepository

/**
 *   Class "RaceRepositoryImpl" :
 *   TODO: Fill class use.
 **/
class DbRaceRepositoryImpl(
    private val dbRaceDao: DbRaceDao,
    private val dbRaceWithDbBonusCharacteristicsDao: DbRaceWithDbBonusCharacteristicsDao
) :
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
        Log.d("findOneById", "id : $id")
        var result: DbRace
        try {
            result = dbRaceDao.getRaceById(id)
        } catch (e: Exception) {
            Log.e("findOneById", "FAILED")
            throw e
        }
        return result.toDomain()
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
        }
    }

    override fun findOneWithChildren(): DomainRace {

        Log.d("testInsert", "DbRaceRepositoryImpl findOneWithChildren START")
        val result: List<DbRaceWithCharacteristics> =
            dbRaceWithDbBonusCharacteristicsDao.getRaceWithCharacteristics()
        Log.d("testInsert", "DbRaceRepositoryImpl findOneWithChildren  count = " + result.count())

        result.forEach {
            Log.d("findOneWithChildren", "result type = " + it.javaClass)
            Log.d("findOneWithChildren", "race name = " + it.race.raceName)
            Log.d("findOneWithChildren", "characteristic count = " + it.characteristics.size)
            it.characteristics.forEach {
                Log.d("findOneWithChildren ", "Characteristic name =" + it.characteristicName)
                Log.d("findOneWithChildren ", "Characteristic bonus =" + it.characteristicBonus)
            }
        }
        return result.first().race.toDomain()
    }
}
// TODO : Fill class.
