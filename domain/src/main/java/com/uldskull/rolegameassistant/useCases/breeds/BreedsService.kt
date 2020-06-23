// File BreedsService.kt
// @Author pierre.antoine - 31/03/2020 - No copyright.

package com.uldskull.rolegameassistant.useCases.breeds

import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed

/**
 *   Interface "BreedsService" :
 *   Service to manage breeds
 **/
interface BreedsService {

    /**
     * Get all breeds
     */
    fun getAllBreeds(): List<DomainDisplayedBreed>
}