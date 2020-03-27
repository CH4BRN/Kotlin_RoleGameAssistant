// CharacterRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.repository.character.CharacterRepository

/**
Class "CharacterRepositoryImpl"

Insert and get Character from database.
 */
class CharacterRepositoryImpl :
    CharacterRepository<LiveData<List<DomainCharacter>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainCharacter>>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainCharacter? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainCharacter>?): List<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainCharacter?): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Delete all entities **/
    override fun deleteAll(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}