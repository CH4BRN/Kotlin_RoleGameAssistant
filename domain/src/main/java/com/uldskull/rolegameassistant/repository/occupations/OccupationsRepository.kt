// File JobsRepository.kt
// @Author pierre.antoine - 22/04/2020 - No copyright.

package com.uldskull.rolegameassistant.repository.occupations

import com.uldskull.rolegameassistant.models.character.DomainOccupation
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
 *   Interface "JobsRepository" :
 *   Interface to ensure that the class implementing it manage jobs persistence.
 **/
interface OccupationsRepository<T> :
GenericRepository<T, DomainOccupation>{
}