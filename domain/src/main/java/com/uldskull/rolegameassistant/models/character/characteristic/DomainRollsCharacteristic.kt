// File DomainRollCharacteristic.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.characteristic

/**
 *   Class "DomainRollCharacteristic" :
 *   TODO: Fill class use.
 **/
class DomainRollsCharacteristic(
    val characteristicId: Long?,
    val characteristicName: String?,
    var characteristicBonus: Int?,
    var characteristicRoll: Int?,
    var characteristicTotal: Int?,
    val characteristicMax: Int?,
    val characteristicRollRule:String?
) {
    override fun toString(): String {
        return "DomainRollsCharacteristic(characteristicId=$characteristicId, characteristicName=$characteristicName, characteristicBonus=$characteristicBonus, characteristicRoll=$characteristicRoll, characteristicTotal=$characteristicTotal, characteristicMax=$characteristicMax, characteristicRollRule=$characteristicRollRule)"
    }

}