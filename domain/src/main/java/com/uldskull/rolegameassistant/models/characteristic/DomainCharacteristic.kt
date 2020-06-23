// File DomainCharacteristic.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.characteristic

/**
 *   Class "DomainCharacteristic" :
 *   Characteristic domain model.
 **/
open class DomainCharacteristic(
    /**
     * Characteristic identifier
     */
    val characteristicId: Long?,
    /**
     * Characteristic name.
     */
    val characteristicName: String?
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainCharacteristic(characteristicId=$characteristicId, characteristicName=$characteristicName)"
    }
}