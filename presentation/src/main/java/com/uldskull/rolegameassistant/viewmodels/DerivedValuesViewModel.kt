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

    /**
     * Character's base health
     */
    var baseHealth: Int = 0
    /**
     * Character's breed's health bonus
     */
    var breedHealthBonus: Int? = 0
        set(value) {
            Log.d(TAG, "Health bonus = $value")
            field = value
        }
    /**
     * Character's total health
     */
    var totalHealth: Int = 0
    /**
     * Character's idea score
     */
    var ideaScore: Int = 0
    /**
     * Character's chance score
     */
    var chanceScore: Int = 0

    /**
     * Calculate energy points
     */
    fun calculateEnergyPoints(power: DomainRollCharacteristic?): Int {
        return if (power?.characteristicTotal != null) {
            Log.d(TAG, "power = ${power.characteristicTotal}")
            power.characteristicTotal!!
        } else 0

    }

    /**
     * Calculate character's idea score
     */
    fun calculateIdeaScore(intelligence: DomainRollCharacteristic?): Int {
        if (intelligence?.characteristicTotal != null) {
            ideaScore = intelligence.characteristicTotal!! * 5
        }
        return ideaScore

    }

    /**
     * Calculate character's chance score
     */
    fun calculateChanceScore(power: DomainRollCharacteristic?): Int {
        if (power?.characteristicTotal != null) {
            chanceScore = power.characteristicTotal!! * 5
        }
        return chanceScore
    }

    /**
     * Calculate character's base health
     */
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

    /**
     * Calculate character's total health.
     */
    fun calculateTotalHealth(): Int {
        if (baseHealth != null && breedHealthBonus != null) {
            totalHealth = baseHealth + breedHealthBonus!!
        }
        return totalHealth
    }
}