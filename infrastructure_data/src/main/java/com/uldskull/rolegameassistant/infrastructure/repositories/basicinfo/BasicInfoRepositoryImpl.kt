// BasicInfoRepositoryImpl.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.basicinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.base.GetAllRequest
import com.uldskull.rolegameassistant.base.GetCounterRequest
import com.uldskull.rolegameassistant.base.Response
import com.uldskull.rolegameassistant.base.Success
import com.uldskull.rolegameassistant.infrastructure.dao.basic_info.BasicInfoDao
import com.uldskull.rolegameassistant.infrastructure.database_model.basic_info.DbBasicInfo
import com.uldskull.rolegameassistant.models.basic_info.DomainBasicInfo
<<<<<<< HEAD
import com.uldskull.rolegameassistant.models.basic_info.BasicInfoRepository
=======
import com.uldskull.rolegameassistant.repository.basic_info.BasicInfoRepository
>>>>>>> e7ef618d5525040e5ea05d137966ac1ecc64df65

/**
Class "BasicInfoRepositoryImpl"

Insert and get BasicInfo from database.
 */
/*
class BasicInfoRepositoryImpl(private val basicInfoDao: BasicInfoDao) :
    BasicInfoRepository<LiveData<List<DomainBasicInfo>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainBasicInfo>> {
        return
        Transformations.map(basicInfoDao.findAll()) { it ->
            it.map {
                it.toDomain()
            }
        }
    }

    /** Get one entity by its id    */
    override fun getOneById(id: Long?): DomainBasicInfo {
        return basicInfoDao.findOneById(id).toDomain()
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainBasicInfo>) {
        basicInfoDao.insertAll(all.map {
            DbBasicInfo.from(it)
        })
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainBasicInfo): Long {
        return basicInfoDao.insert(DbBasicInfo.from(one))
    }
<<<<<<< HEAD
}*/
=======

    override suspend fun getCounter(request: GetCounterRequest)
            : Response<Int?> {
        return Success(getAll().value?.size)
    }

    override suspend fun getAll(request: GetAllRequest)
            : Response<LiveData<List<DomainBasicInfo>>> {
        return Success(getAll())
    }

}
>>>>>>> e7ef618d5525040e5ea05d137966ac1ecc64df65
