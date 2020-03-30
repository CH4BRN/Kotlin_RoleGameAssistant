// File DbCharacteristicRepositoryImpl.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.repositories.characteristic

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbCharacteristicDao
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository

/**
 *   Class "DbCharacteristicRepositoryImpl" :
 *   TODO: Fill class use.
 **/
class DbCharacteristicRepositoryImpl(
    private val dbCharacteristicDao: DbCharacteristicDao
) : CharacteristicRepository<LiveData<List<DomainCharacteristic>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainCharacteristic>>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainCharacteristic? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainCharacteristic>?): List<Long>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity  -  it can return a long, which is the new rowId for the inserted item.*/
    override fun insertOne(one: DomainCharacteristic?): Long? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Delete all entities **/
    override fun deleteAll(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
// TODO : Fill class.
}