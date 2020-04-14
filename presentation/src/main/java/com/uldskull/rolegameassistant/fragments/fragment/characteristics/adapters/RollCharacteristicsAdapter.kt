// File RollCharacteristicsAdapter.kt
// @Author pierre.antoine - 12/04/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.characteristics.adapters

import android.content.Context
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollCharacteristic

/**
 *   Class "RollCharacteristicsAdapter" :
 *   TODO: Fill class use.
 **/
class RollCharacteristicsAdapter(
    context: Context,
    val givenCharacteristics: List<DomainRollCharacteristic>?
) : CharacteristicsAdapter(context) {

    init {
        givenCharacteristics?.forEach {

        }
        this.setCharacteristics(givenCharacteristics)
    }
}