// File DomainCharacteristic.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.characteristic

/**
 *   Class "DomainBonusCharacteristic" :
 *   Characteristic with bonus and max values
 **/
open class DomainBonusCharacteristic(
    characteristicId: Long?,
    characteristicName: String,
    val characteristicBonus: Int,
    val characteristicMax: Int
) : DomainCharacteristic(
    characteristicId,
    characteristicName
)

