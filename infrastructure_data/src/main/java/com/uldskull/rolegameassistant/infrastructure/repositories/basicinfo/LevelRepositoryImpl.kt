// LevelRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.basicinfo

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.infrastructure.dao.basic_info.LevelDao
import com.uldskull.rolegameassistant.models.basic_info.DomainLevel
import com.uldskull.rolegameassistant.contracts.repository.basic_info.LevelRepository

/**
Class "LevelRepositoryImpl"

Insert and get Level.
 */
class LevelRepositoryImpl(private val levelDao: LevelDao) : LevelRepository<LiveData<List<DomainLevel>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainLevel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun getOneById(id: Long?): DomainLevel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainLevel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainLevel): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}