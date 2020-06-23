// File DomainBreedWithCharacteristics.kt
// @Author pierre.antoine - 02/04/2020 - No copyright.

package com.uldskull.rolegameassistant.models.breed

import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.characteristic.DomainBreedsCharacteristic

/**
 *   Class "DomainBreedWithCharacteristics" :
 *   Holds one breed with its characteristics.
 **/
class DomainDisplayedBreedWithCharacteristics(
    /**
     * Displayed breed
     */
    val displayedBreed: DomainDisplayedBreed,
    /**
     * Breed's characteristics
     */
    val characteristics: List<DomainBreedsCharacteristic>
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainDisplayedBreedWithCharacteristics(displayedBreed=$displayedBreed, characteristics=$characteristics)"
    }
}