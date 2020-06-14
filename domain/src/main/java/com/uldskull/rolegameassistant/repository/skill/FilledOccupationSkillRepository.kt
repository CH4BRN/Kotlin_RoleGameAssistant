// FilledOccupationSkillRepository.kt created by UldSkull - 14/06/2020

package com.uldskull.rolegameassistant.repository.skill

import com.uldskull.rolegameassistant.models.character.skill.DomainFilledSkill
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
Interface "FilledOccupationSkillRepository"

TODO : Describe interface utility.
 **/
interface FilledOccupationSkillRepository<T> :
    GenericRepository<T, DomainFilledSkill> {

    fun deleteAllByCharacterId(id:Long):Int
}