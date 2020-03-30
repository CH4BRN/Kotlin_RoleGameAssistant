// File RaceRepository.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.repository.race

import com.uldskull.rolegameassistant.models.character.DomainRace
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
 *   Interface "RaceRepository" :
 *   Class to manage race persistence.
 **/
interface RaceRepository<T> :
    GenericRepository<T, DomainRace> {
    fun findOneWithChildren(): DomainRace
}