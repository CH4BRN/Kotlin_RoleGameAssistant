// BackgroundRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.background

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.models.background.DomainBackground
import com.uldskull.rolegameassistant.repository.background.BackgroundRepository


/**
    Class "BackgroundRepositoryImpl"

    Insert and get Background from database.
 */
class BackgroundRepositoryImpl : BackgroundRepository<LiveData<List<DomainBackground>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainBackground>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun getOne(id: Long?): DomainBackground {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainBackground>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainBackground): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}