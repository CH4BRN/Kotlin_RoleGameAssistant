// BasicInfoRepositoryImpl.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.basicinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.BasicInfoDao
import com.uldskull.rolegameassistant.infrastructure.database_model.basic_info.DbBasicInformation
import com.uldskull.rolegameassistant.models.basic_info.DomainBasicInfo
import com.uldskull.rolegameassistant.repository.basic_info.BasicInfoRepository

/**
Class "BasicInfoRepositoryImpl"

Insert and get BasicInfo from database.
 */
class BasicInfoRepositoryImpl(private val basicInfoDao: BasicInfoDao) :
    BasicInfoRepository<LiveData<List<DomainBasicInfo>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainBasicInfo>> {
        return Transformations.map(basicInfoDao.getAll()) { it ->
            it.map {
                it.toDomain()
            }
        }
    }

    /** Get one entity by its id    */
    override fun getOne(id: Long?): DomainBasicInfo {
        return basicInfoDao.getById(id).toDomain()
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainBasicInfo>) {
        basicInfoDao.insertAll(all.map {
            DbBasicInformation.from(it)
        })
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainBasicInfo): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}