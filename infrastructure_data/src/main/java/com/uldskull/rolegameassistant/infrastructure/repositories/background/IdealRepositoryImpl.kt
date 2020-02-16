// IdealRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.background

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.models.background.DomainIdeal
import com.uldskull.rolegameassistant.contracts.repository.background.IdealRepository


/**
Class "IdealRepositoryImpl"

Insert and get Ideal from database.
 */
class IdealRepositoryImpl : IdealRepository<LiveData<List<DomainIdeal>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainIdeal>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun getOneById(id: Long?): DomainIdeal {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainIdeal>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainIdeal): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}