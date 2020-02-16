// RaceRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.basicinfo

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.infrastructure.dao.basic_info.RaceDao
import com.uldskull.rolegameassistant.models.basic_info.DomainRace
import com.uldskull.rolegameassistant.contracts.repository.basic_info.RaceRepository

/**
Class "RaceRepositoryImpl"

Insert and get Race.
 */
class RaceRepositoryImpl (private val raceDao: RaceDao) :
    RaceRepository<LiveData<List<DomainRace>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainRace>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun getOneById(id: Long?): DomainRace {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainRace>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainRace): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}