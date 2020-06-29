// OccupationWithSkillsRepository.kt created by UldSkull - 29/06/2020

package com.uldskull.rolegameassistant.repository.occupations

import com.uldskull.rolegameassistant.models.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
Interface "OccupationWithSkillsRepository"

Class to manage DomainOccupationWithSkills persistence.
 **/
interface OccupationWithSkillsRepository<T> :
GenericRepository<T, DomainOccupationWithSkills>{
    /**
     * Inserts a relation between occupation and skills
     */
    suspend fun insertOccupationAndSkillCross(occupationId:Long?, skillId:Long):Long

}