// File BreedsRepository.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.repository.breed

import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreedWithCharacteristics
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
 *   Interface "BreedsRepository" :
 *   Class to manage breed persistence.
 **/
interface DisplayedBreedsRepository<T> :
    GenericRepository<T, DomainDisplayedBreed> {
    /**
     * Get the breeds with associated characteristics
     */
    fun findAllWithChildren(): List<DomainDisplayedBreedWithCharacteristics>

    /**
     * Find one breed with its associated characteristics
     */
    fun findOneWithChildren(id: Long?): DomainDisplayedBreedWithCharacteristics?
}