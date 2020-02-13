// SkillRepositoryImpl.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.models.skill.DomainSkill
import com.uldskull.rolegameassistant.repository.skill.SkillRepository

/**
    Class "SkillRepositoryImpl"

    Insert and get Skill from database.
 */
class SkillRepositoryImpl :
    SkillRepository<LiveData<List<DomainSkill>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainSkill>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Get one entity by its id    */
    override fun getOne(id: Long?): DomainSkill {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainSkill>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainSkill): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}