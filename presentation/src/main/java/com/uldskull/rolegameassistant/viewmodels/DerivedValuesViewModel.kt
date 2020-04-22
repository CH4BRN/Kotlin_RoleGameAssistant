// File DerivedValuesViewModel.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollCharacteristic

/**
 *   Class "DerivedValuesViewModel" :
 *   TODO: Fill class use.
 **/
class DerivedValuesViewModel(application: Application) : AndroidViewModel(application) {
    companion object {

        private const val TAG = "DerivedValuesViewModel"
    }
    var selectedDamageBonusIndex: Int? = 0
    enum class DamageBonus(value: String) {
        Minus1D6("-1D6"),
        Minus1D4("-1D4"),
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

    var healthEditTextHasChanged: Boolean = false
    var breedBonusEditTextHasChanged: Boolean = false
    var totalHealthEditTextHasChanged: Boolean = false
    var ideaEditTextHasChanged: Boolean = false
    var chanceEditTextHasChanged: Boolean = false
    var energyPointsEdiTextHasChanged:Boolean = false
    var sizePlusStrengthEditTextHasChanged:Boolean = false
    var damageBonusSpinnerSelectionHasChanged:Boolean = false
    var alignmentEditTextHasChanged:Boolean = false
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

    var damageBonus: DamageBonus? = DamageBonus.none


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
    fun calculateSizePlusStrength(characteristics: List<DomainRollCharacteristic?>): Int {
        Log.d(TAG, "calculateSizePlusStrength")
        characteristics.forEach {
            if (it?.characteristicTotal != null) {
                sizePlusStrengthScore += it?.characteristicTotal!!
            }
        }
        calculateDamageBonus()
        return sizePlusStrengthScore
    }

    fun calculateDamageBonus(): Int {
        Log.d(TAG, "calculateDamageBonus")
        Log.d(TAG, "Selected damage bonus index $selectedDamageBonusIndex")
        Log.d(TAG, "sizePlusStrengthScore = $sizePlusStrengthScore")

        for (index in 0..DamageBonus.values().indices.last) {
            Log.d(TAG, "$index ${DamageBonus.values()[index].name}")
        }

        when (sizePlusStrengthScore) {
            in 2..12 -> selectedDamageBonusIndex = 0
            in 13..16 -> selectedDamageBonusIndex = 1
            in 17..24 -> selectedDamageBonusIndex = 2
            in 25..32 -> selectedDamageBonusIndex = 3
            in 33..40 -> selectedDamageBonusIndex = 4
            in 41..56 -> selectedDamageBonusIndex = 5
            in 57..72 -> selectedDamageBonusIndex = 6
            in 73..88 -> selectedDamageBonusIndex = 7
            in 89..104 -> selectedDamageBonusIndex = 8
            in 105..120 -> selectedDamageBonusIndex = 9
            in 121..136 -> selectedDamageBonusIndex = 10
            in 137..152 -> selectedDamageBonusIndex = 11
            in 153..168 -> selectedDamageBonusIndex = 12
            in 169..184 -> selectedDamageBonusIndex = 13
            else -> selectedDamageBonusIndex = 2
        }
        Log.d(TAG, "Selected damage bonus index ${selectedDamageBonusIndex}")
        damageBonus = DamageBonus.values()[selectedDamageBonusIndex!!]
        return selectedDamageBonusIndex!!
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
        Log.d(TAG, "calculate base health")
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
        if (breedHealthBonus != null) {
            totalHealth = baseHealth + breedHealthBonus!!
        }
        return totalHealth
    }

    fun calculateBreedsHealthBonus(breeds: List<DomainBreed>): Int {
        Log.d(TAG, "calculateBreedsHealthBonus")
        var bonus: Int = 0
        breeds.forEach {
            if (it.breedHealthBonus != null) {
                bonus += it.breedHealthBonus!!
            }
        }
        breedHealthBonus = bonus
        return breedHealthBonus!!
    }


}