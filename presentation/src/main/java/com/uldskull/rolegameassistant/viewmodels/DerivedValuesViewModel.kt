// File DerivedValuesViewModel.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollCharacteristic

/**
 *   Class "DerivedValuesViewModel" :
 *   TODO: Fill class use.
 **/
class DerivedValuesViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "DerivedValuesViewModel"
    }

    var baseHealth: Int = 0
    var breedHealthBonus: Int? = 0

    fun calculateBaseHealth(rollCharacteristics: List<DomainRollCharacteristic>): Int {
        var hp = 0
        rollCharacteristics.forEach {
            Log.d(TAG, "${it.characteristicName} - ${it.characteristicTotal}")

            if (it.characteristicTotal != null) {
                hp += it.characteristicTotal!!
            }
        }
        baseHealth = hp / 2
        return baseHealth

    }
// TODO : Fill class.
}