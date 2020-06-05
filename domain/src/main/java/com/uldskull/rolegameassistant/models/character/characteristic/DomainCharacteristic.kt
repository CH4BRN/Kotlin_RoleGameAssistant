// File DomainCharacteristic.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.characteristic

/**
 *   Class "DomainCharacteristic" :
 *   Characteristic domain model, the simplest.
 **/
open class DomainCharacteristic(
    val characteristicId: Long?,
    val characteristicName: String?
) {
    override fun toString(): String {
        return "DomainCharacteristic(characteristicId=$characteristicId, characteristicName=$characteristicName)"
    }
}