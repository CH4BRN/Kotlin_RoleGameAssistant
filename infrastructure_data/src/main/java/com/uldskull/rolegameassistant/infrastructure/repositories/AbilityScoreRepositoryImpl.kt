// AbilityScoreRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.infrastructure.dao.AbilityScoreDao
import com.uldskull.rolegameassistant.models.ability_score.DomainAbilityScore
import com.uldskull.rolegameassistant.contracts.repository.ability_score.AbilityScoreRepository


/**
    Class "AbilityScoreRepositoryImpl"

    Insert and get AbilityScore from database.
 */
class AbilityScoreRepositoryImpl(private val abilityScoreDao: AbilityScoreDao) :
    AbilityScoreRepository<LiveData<DomainAbilityScore>> {
    override fun getAll(): LiveData<DomainAbilityScore> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOneById(id: Long?): DomainAbilityScore {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertAll(all: List<DomainAbilityScore>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertOne(one: DomainAbilityScore): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}