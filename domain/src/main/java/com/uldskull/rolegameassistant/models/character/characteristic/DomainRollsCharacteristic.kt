// File DomainRollCharacteristic.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.characteristic

/**
 *   Class "DomainRollCharacteristic" :
 *   Holds a "dice rolled" characteristic
 **/
class DomainRollsCharacteristic(
    /**
     * Characteristic identifier
     */
    val characteristicId: Long?,
    /**
     * Characteristic name
     */
    val characteristicName: String?,
    /**
     * Characteristic bonus
     */
    var characteristicBonus: Int?,
    /**
     * Characteristic roll
     */
    var characteristicRoll: Int?,
    /**
     * Characteristic total
     */
    var characteristicTotal: Int?,
    /**
     * Characteristic max
     */
    val characteristicMax: Int?,
    /**
     * Characteristic roll rule
     */
    val characteristicRollRule: String?
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainRollsCharacteristic(\n" +
                "characteristicId=$characteristicId,\n " +
                "characteristicName=$characteristicName,\n" +
                "characteristicBonus=$characteristicBonus,\n" +
                "characteristicRoll=$characteristicRoll,\n" +
                "characteristicTotal=$characteristicTotal,\n" +
                "characteristicMax=$characteristicMax,\n" +
                "characteristicRollRule=$characteristicRollRule)"
    }

}