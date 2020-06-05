// File DomainCharacteristic.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.characteristic

/**
 *   Class "DomainBonusCharacteristic" :
 *   Characteristic with bonus and max values
 **/
open class DomainBreedsCharacteristic(
    characteristicId: Long?,
    characteristicName: String?,
    val characteristicBonus: Int? = 0,
    val characteristicBreedId: Long?
) : DomainCharacteristic(
    characteristicId,
    characteristicName
) {
    override fun toString(): String {
        return "DomainBreedsCharacteristic(characteristicBonus=$characteristicBonus, characteristicBreedId=$characteristicBreedId)"
    }
}

