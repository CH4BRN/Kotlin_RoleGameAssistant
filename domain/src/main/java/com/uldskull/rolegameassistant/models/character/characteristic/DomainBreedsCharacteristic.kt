// File DomainCharacteristic.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.characteristic

/**
 *   Class "DomainBonusCharacteristic" :
 *   Characteristic with bonus and max values
 **/
open class DomainBreedsCharacteristic(
    /**
     * Characteristic identifier
     */
    characteristicId: Long?,
    /**
     * Characteristic name
     */
    characteristicName: String?,
    /**
     * Characteristic bonus
     */
    val characteristicBonus: Int? = 0,
    /**
     * Characteristic breed identifier
     */
    val characteristicBreedId: Long?
) : DomainCharacteristic(
    characteristicId,
    characteristicName
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainBreedsCharacteristic(characteristicBonus=$characteristicBonus, characteristicBreedId=$characteristicBreedId)"
    }
}

