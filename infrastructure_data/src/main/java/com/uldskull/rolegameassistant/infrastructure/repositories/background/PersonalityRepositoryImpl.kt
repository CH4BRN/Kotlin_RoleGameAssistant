// PersonalityRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.background

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.models.background.DomainPersonality
import com.uldskull.rolegameassistant.repository.background.PersonalityRepository

/**
Class "PersonalityRepositoryImpl"

Insert and get personality
 */
class PersonalityRepositoryImpl :
    PersonalityRepository<LiveData<List<DomainPersonality>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainPersonality>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun getOne(id: Long?): DomainPersonality {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainPersonality>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainPersonality): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}