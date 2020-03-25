// File DomainRace.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character

import com.uldskull.rolegameassistant.models.character.characteristic.DomainBonusCharacteristic

/**
 *   Class "DomainRace" :
 *   TODO: Fill class use.
 **/
class DomainRace(
    val raceId: Long,
    val raceName: String,
    val raceCharacteristics: List<DomainBonusCharacteristic>
)
