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

    enum class DamageBonus(value: String) {
        minus1D6("-1D6"),
        minus1D4("-1D4"),
        none("nothing"),
        plus1D4("+1D4"),
        plus1D6("+1D6"),
        plus2D6("+2D6"),
        plus3D6("+3D6"),
        plus4D6("+4D6"),
        plus5D6("+5D6"),
        plus6D6("+6D6"),
        plus7D6("+7D6"),
        plus8D6("+8D6"),
        plus9D6("+9D6"),
        plus10D6("+10D6")
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

    var energyPoints: Int = 0

    var sizePlusStrengthScore: Int = 0

    var damageBonus: DamageBonus? = DamageBonus.minus1D6


    /**
     * Calculate energy points
     */
    fun calculateEnergyPoints(power: DomainRollCharacteristic?): Int {
        energyPoints = if (power?.characteristicTotal != null) {
            Log.d(TAG, "power = ${power.characteristicTotal}")
            power.characteristicTotal!!
        } else 0
        return energyPoints
    }

    /**
     * Calculate size plus strength for damage bonus
     */
    fun calculateSizePlusStrength(characteristics: List<DomainRollCharacteristic>): Int {

        characteristics.forEach {
            if (it.characteristicTotal != null) {
                sizePlusStrengthScore += it.characteristicTotal!!
            }
        }
        damageBonus = calculateDamageBonus()
        return sizePlusStrengthScore
    }


    fun calculateDamageBonus(): DamageBonus {
        when (sizePlusStrengthScore) {
            in 2..12 -> return DamageBonus.minus1D6
            in 13..16 -> return DamageBonus.minus1D4
            in 17..24 -> return DamageBonus.none
            in 25..32 -> return DamageBonus.plus1D4
            in 33..40 -> return DamageBonus.plus1D6
            in 41..56 -> return DamageBonus.plus2D6
            in 57..72 -> return DamageBonus.plus3D6
            in 73..88 -> return DamageBonus.plus4D6
            in 89..104 -> return DamageBonus.plus5D6
            in 105..120 -> return DamageBonus.plus6D6
            in 121..136 -> return DamageBonus.plus7D6
            in 137..152 -> return DamageBonus.plus8D6
            in 153..168 -> return DamageBonus.plus9D6
            in 169..184 -> return DamageBonus.plus10D6
            else -> return DamageBonus.none
        }
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