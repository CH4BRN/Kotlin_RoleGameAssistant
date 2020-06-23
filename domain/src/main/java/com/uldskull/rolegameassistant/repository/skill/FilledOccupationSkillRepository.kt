// FilledOccupationSkillRepository.kt created by UldSkull - 14/06/2020

package com.uldskull.rolegameassistant.repository.skill

import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
Interface "FilledOccupationSkillRepository"

Class to manage DomainSkillToFill persistence.
 **/
interface FilledOccupationSkillRepository<T> :
    GenericRepository<T, DomainSkillToFill> {

    /**
     * Deletes all skills corresponding to a character id
     */
    fun deleteAllByCharacterId(id: Long): Int

    /**
     * Find the same skill
     */
    fun findTheSame(skill: DomainSkillToFill): DomainSkillToFill?

    /**
     * Update the corresponding skill's tens value
     */
    fun updateTensValues(skill: DomainSkillToFill, tensValues: Int): Int

    /**
     * Update the corresponding skill's units value
     */
    fun updateUnitsValues(skill: DomainSkillToFill, unitsValues: Int): Int

}