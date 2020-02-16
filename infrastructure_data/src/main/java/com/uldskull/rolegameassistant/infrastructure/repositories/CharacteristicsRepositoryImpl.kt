// CharacteristicsRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.models.characteristics.DomainCharacteristics
import com.uldskull.rolegameassistant.contracts.repository.characteristics.CharacteristicsRepository

/**
    Class "CharacteristicsRepositoryImpl"

    Insert and get Characteristics from database.
 */
class CharacteristicsRepositoryImpl :
    CharacteristicsRepository<LiveData<List<DomainCharacteristics>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainCharacteristics>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun getOneById(id: Long?): DomainCharacteristics {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainCharacteristics>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainCharacteristics): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}