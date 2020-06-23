// File BreedsService.kt
// @Author pierre.antoine - 31/03/2020 - No copyright.

package com.uldskull.rolegameassistant.services

import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreed

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