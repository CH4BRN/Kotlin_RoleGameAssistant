// File DomainBreedWithCharacteristics.kt
// @Author pierre.antoine - 02/04/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.breed.displayedBreed

import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedsCharacteristic

/**
 *   Class "DomainBreedWithCharacteristics" :
 *   Holds one breed with its characteristics.
 **/
class DomainDisplayedBreedWithCharacteristics(
    val displayedBreed: DomainDisplayedBreed,
    val characteristics: List<DomainBreedsCharacteristic>
) {
    override fun toString(): String =
        "breed : ${displayedBreed.breedName}\n ${characteristics.size} characteristics."
}