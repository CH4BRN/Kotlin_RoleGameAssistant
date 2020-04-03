// File DomainBreedWithCharacteristics.kt
// @Author pierre.antoine - 02/04/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.breed

import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedCharacteristic

/**
 *   Class "DomainBreedWithCharacteristics" :
 *   Holds one breed with its characteristics.
 **/
class DomainBreedWithCharacteristics(
    val breed: DomainBreed,
    val characteristics: List<DomainBreedCharacteristic>
) {
    override fun toString(): String =
        "breed : ${breed.breedName}\n ${characteristics.size} characteristics."
}