// ClassRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.basicinfo

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.infrastructure.dao.basic_info.ClassDao
import com.uldskull.rolegameassistant.models.basic_info.DomainClass
import com.uldskull.rolegameassistant.contracts.repository.basic_info.ClassRepository


/**
Class "ClassRepositoryImpl"

Insert and get Class from database.
 */
class ClassRepositoryImpl(private val  classDao: ClassDao) :
    ClassRepository<LiveData<List<DomainClass>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainClass>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun getOneById(id: Long?): DomainClass {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainClass>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainClass): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}