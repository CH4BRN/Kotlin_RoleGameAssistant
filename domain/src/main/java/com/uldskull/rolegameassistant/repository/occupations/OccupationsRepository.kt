// File JobsRepository.kt
// @Author pierre.antoine - 22/04/2020 - No copyright.

package com.uldskull.rolegameassistant.repository.occupations

import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
 *   Interface "JobsRepository" :
 *   Interface to ensure that the class implementing it manage jobs persistence.
 **/
interface OccupationsRepository<T> :
GenericRepository<T, DomainOccupation>{
    fun insertOccupationAndSkillCross(occupationId:Long?, skillId:Long):Long
    fun findOneWithChildren(occupationId: Long?):DomainOccupationWithSkills
    fun deleteOne(currentOccupationToEdit: DomainOccupation):Int

}