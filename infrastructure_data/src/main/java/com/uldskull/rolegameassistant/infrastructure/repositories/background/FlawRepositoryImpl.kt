// FlawRepositoryImpl.ktt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.background

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.models.background.DomainFlaw
import com.uldskull.rolegameassistant.contracts.repository.background.FlawRepository

/**
Class "FlawRepositoryImpl"

Insert and get Flaw from database.
 */
class FlawRepositoryImpl :
    FlawRepository<LiveData<List<DomainFlaw>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainFlaw>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun getOneById(id: Long?): DomainFlaw {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainFlaw>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainFlaw): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}