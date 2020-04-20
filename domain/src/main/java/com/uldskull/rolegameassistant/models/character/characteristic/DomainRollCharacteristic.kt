// File DomainRollCharacteristic.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.characteristic

/**
 *   Class "DomainRollCharacteristic" :
 *   TODO: Fill class use.
 **/
class DomainRollCharacteristic(
    val characteristicId: Long?,
    val characteristicName: String?,
    var characteristicBonus: Int?,
    var characteristicRoll: Int?,
    var characteristicTotal: Int?,
    val characteristicMax: Int?,
    val characteristicRollRule:String?
) {

    override fun toString(): String {
        return "RollCharacteristic : \n" +
                "\tname : $characteristicName\n" +
                "\tbonus : ${characteristicBonus.toString()}\n" +
                "\troll : ${characteristicRoll.toString()}\n"+
                "\troll rule : ${characteristicRollRule}\n"

    }
}