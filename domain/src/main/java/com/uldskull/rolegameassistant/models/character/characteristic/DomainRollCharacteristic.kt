// File DomainRollCharacteristic.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.characteristic

/**
 *   Class "DomainRollCharacteristic" :
 *   TODO: Fill class use.
 **/
class DomainRollCharacteristic(
    characteristicId: Long,
    characteristicName: String,
    characteristicBonus: Int,
    characteristicRaceId: Long,
    val characteristicRoll: Int,
    val characteristicTotal: Int

) : DomainRaceCharacteristic(
    characteristicId = characteristicId,
    characteristicName = characteristicName,
    characteristicBonus = characteristicBonus,
    characteristicRaceId = characteristicRaceId
)