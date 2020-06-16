// FilledHobbySkillRepository.kt created by UldSkull - 15/06/2020

package com.uldskull.rolegameassistant.repository.skill

import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
Interface "FilledHobbySkillRepository"

TODO : Describe interface utility.
 **/
interface FilledHobbySkillRepository<T>:
GenericRepository<T, DomainSkillToFill>{

    fun deleteAllByCharacterId(id:Long):Int
}