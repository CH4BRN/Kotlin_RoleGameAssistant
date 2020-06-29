// File JobsRepository.kt
// @Author pierre.antoine - 22/04/2020 - No copyright.

package com.uldskull.rolegameassistant.repository.occupations

import com.uldskull.rolegameassistant.models.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
 *   Interface "JobsRepository" :
 *   Class to manage DomainOccupation persistence.
 **/
interface OccupationsRepository<T> :
GenericRepository<T, DomainOccupation>{




    /**
     * Delete one occupation
     */
    suspend fun deleteOne(currentOccupationToEdit: DomainOccupation):Int

}