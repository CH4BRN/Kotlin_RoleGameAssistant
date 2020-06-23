// FilledHobbySkillRepository.kt created by UldSkull - 15/06/2020

package com.uldskull.rolegameassistant.repository.skill

import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
Interface "FilledHobbySkillRepository"

Class to manage DomainSkillToFill persistence.
 **/
interface FilledHobbySkillRepository<T>:
GenericRepository<T, DomainSkillToFill>{

    /**
     * Deletes skills by character's id
     */
    fun deleteAllByCharacterId(id:Long):Int
}