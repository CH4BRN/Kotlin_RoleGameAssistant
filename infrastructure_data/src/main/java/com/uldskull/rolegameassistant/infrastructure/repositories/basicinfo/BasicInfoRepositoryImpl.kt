// BasicInfoRepositoryImpl.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.basicinfo

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.infrastructure.dao.BasicInfoDao
import com.uldskull.rolegameassistant.models.basic_info.DomainBasicInfo
import com.uldskull.rolegameassistant.repository.basic_info.BasicInfoRepository

/**
    Class "BasicInfoRepositoryImpl"

    Insert and get BasicInfo from database.
 */
class BasicInfoRepositoryImpl(basicInfoDao: BasicInfoDao) : BasicInfoRepository<LiveData<List<DomainBasicInfo>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainBasicInfo>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun getOne(id: Long?): DomainBasicInfo {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainBasicInfo>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainBasicInfo): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}