// HealthRepositoryImpl.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.models.health.DomainHealth
import com.uldskull.rolegameassistant.contracts.repository.health.HealthRepository


/**
    Class "HealthRepositoryImpl"

    Insert and get health from database.
 */
class HealthRepositoryImpl :
    HealthRepository<LiveData<List<DomainHealth>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainHealth>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun getOneById(id: Long?): DomainHealth {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainHealth>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainHealth): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}