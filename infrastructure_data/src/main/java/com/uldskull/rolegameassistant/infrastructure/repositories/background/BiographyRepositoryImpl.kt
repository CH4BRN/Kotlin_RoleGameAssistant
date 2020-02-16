// BiographyRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.background

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.models.background.DomainBiography
import com.uldskull.rolegameassistant.contracts.repository.background.BiographyRepository

/**
Class "BiographyRepositoryImpl"

Insert and get Biography from database.
 */
class BiographyRepositoryImpl : BiographyRepository<LiveData<List<DomainBiography>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainBiography>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun getOneById(id: Long?): DomainBiography {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainBiography>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainBiography): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}