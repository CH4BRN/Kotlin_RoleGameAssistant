// BondRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.background

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.models.background.DomainBond
import com.uldskull.rolegameassistant.contracts.repository.background.BondRepository


/**
Class "BondRepositoryImpl"

Insert and get Bond from database.
 */
class BondRepositoryImpl :
    BondRepository<LiveData<List<DomainBond>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainBond>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun getOneById(id: Long?): DomainBond {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainBond>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainBond): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}