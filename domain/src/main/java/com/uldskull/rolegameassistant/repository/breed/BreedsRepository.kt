// File BreedsRepository.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.repository.breed

import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.models.character.breed.DomainBreedWithCharacteristics
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
 *   Interface "BreedsRepository" :
 *   Class to manage breed persistence.
 **/
interface BreedsRepository<T> :
    GenericRepository<T, DomainBreed> {
    /**
     * Get the breeds with associated characteristics
     */
    fun findAllWithChildren(): List<DomainBreedWithCharacteristics>

    fun findOneWithChildren(id: Long?): DomainBreedWithCharacteristics?
}