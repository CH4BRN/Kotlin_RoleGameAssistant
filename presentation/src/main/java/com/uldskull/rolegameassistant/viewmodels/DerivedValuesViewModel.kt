// File DerivedValuesViewModel.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollsCharacteristic

/**
 *   Class "DerivedValuesViewModel" :
 *   Viewmodel for derived values
 **/
class DerivedValuesViewModel(application: Application) : AndroidViewModel(application) {
    companion object {

        private const val TAG = "DerivedValuesViewModel"
    }

    /**
     * Has know score edit text changed ?
     */
    var knowScoreEditTextHasChanged: Boolean = false

    /**
     * Has know edit text changed ?
     */
    var knowEditTextHasChanged: Boolean = false

    /**
     *  Has cthulhu myth edit text changed ?
     */
    var cthulhuMythScoreEditTextHasChanged: Boolean = false

    /**
     * Observable Cthulhu myth score
     */
    var cthulhuMythScore: MutableLiveData<Int> = MutableLiveData()

    /**
     * Enum for damage bonus
     */
    enum class DamageBonus(value: String) {
        Minus1D6("-1D6"),
        Minus1D4("-1D4"),
        None("nothing"),
        Plus1D4("+1D4"),
        Plus1D6("+1D6"),
        Plus2D6("+2D6"),
        Plus3D6("+3D6"),
        Plus4D6("+4D6"),
        Plus5D6("+5D6"),
        Plus6D6("+6D6"),
        Plus7D6("+7D6"),
        Plus8D6("+8D6"),
        Plus9D6("+9D6"),
        Plus10D6("+10D6")
    }

    /**
     *  Has base health edit text changed ?
     */
    var baseHealthEditTextHasChanged: Boolean = false

    /**
     *  Has breed bonus edit text changed ?
     */
    var breedBonusEditTextHasChanged: Boolean = false
        set(value) {
            Log.d(TAG, "totalHealthEditTextHasChanged = ${value}")
            field = value
        }

    /**
     *  Has idea edit text changed ?
     */
    var ideaEditTextHasChanged: Boolean = false
    /**
     *  Has sanity edit text changed ?
     */
    var sanityEditTextHasChanged: Boolean = false
    /**
     *  Has luck edit text changed ?
     */
    var luckEditTextHasChanged: Boolean = false
    /**
     *  Has energy edit text changed ?
     */
    var energyPointsEdiTextHasChanged: Boolean = false
    /**
     *  Has size plus strength edit text changed ?
     */
    var sizePlusStrengthEditTextHasChanged: Boolean = false
    /**
     *  Has damage bonus spinner selection changed ?
     */
    var damageBonusSpinnerSelectionHasChanged: Boolean = false
    /**
     *  Has alignment edit text changed ?
     */
    var alignmentEditTextHasChanged: Boolean = false

    /**
     * Character's base health
     */
    var baseHealth = MutableLiveData<Int>()

    /**
     * Character's breed's health bonus
     */
    var breedHealthBonus = MutableLiveData<Int>()

    /**
     * Character's total health
     */
    var totalHealth: MutableLiveData<Int> = MutableLiveData()

    /**
     * Character's idea score
     */
    var ideaScore: MutableLiveData<Int> = MutableLiveData()

    /**
     * Character's chance score
     */
    var sanityScore: MutableLiveData<Int?> = MutableLiveData()

    /**
     * Character's luck score.
     */
    var luckScore: MutableLiveData<Int?> = MutableLiveData()

    /**
     * Character's know score
     */
    var knowScore: MutableLiveData<Int?> = MutableLiveData()

    /**
     * Character's energy score.
     */
    var energyPoints: MutableLiveData<Int?> = MutableLiveData()

    /**
     * Character's damage bonus
     */
    var damageBonus: MutableLiveData<DamageBonus?> = MutableLiveData()

    /**
     * Character's Size + Strength score
     */
    var sizePlusStrengthScore: MutableLiveData<Int?> = MutableLiveData()

    /**
     * Character damage bonus index
     */
    var selectedDamageBonusIndex: MutableLiveData<Int?> = MutableLiveData()

    var alignmentScore: MutableLiveData<Int?> = MutableLiveData()

    init {
        if (cthulhuMythScore.value == 0 || cthulhuMythScore.value == null) {
            cthulhuMythScore.value = 99
        }
    }

    /**
     * Calcuate alignment score
     */
    fun calculateAlignmentScore(ideals: List<DomainIdeal?>) {
        var score = 0
        ideals.forEach {
            if (it != null) {
                if (it.idealGoodPoints != null) {
                    var goodPoints = it.idealGoodPoints
                    if (goodPoints != null) {
                        score += goodPoints
                    }
                }
                if (it.idealEvilPoints != null) {
                    var evilPoints = it.idealEvilPoints
                    if (evilPoints != null) {
                        score -= evilPoints
                    }
                }
            }
        }
        alignmentScore.value = score
    }

    /**
     * Calculate energy points
     */
    fun calculateEnergyPoints(power: DomainRollsCharacteristic?) {
        if (power != null) {
            if (power.characteristicTotal != null) {
                energyPoints.value = power.characteristicTotal
            }
        }
    }

    /**
     * Calculate damage bonus
     */
    fun calculateDamageBonus(size: DomainRollsCharacteristic, strength: DomainRollsCharacteristic) {
        Log.d(TAG, "calculateDamageBonus")
        Log.d(TAG, "Selected damage bonus index $selectedDamageBonusIndex")

        if (size.characteristicTotal != null && strength.characteristicTotal != null) {
            var score =
                size.characteristicTotal!! + strength.characteristicTotal!!

            sizePlusStrengthScore.value = score


            when (score) {
                in 2..12 -> selectedDamageBonusIndex.value = 0
                in 13..16 -> selectedDamageBonusIndex.value = 1
                in 17..24 -> selectedDamageBonusIndex.value = 2
                in 25..32 -> selectedDamageBonusIndex.value = 3
                in 33..40 -> selectedDamageBonusIndex.value = 4
                in 41..56 -> selectedDamageBonusIndex.value = 5
                in 57..72 -> selectedDamageBonusIndex.value = 6
                in 73..88 -> selectedDamageBonusIndex.value = 7
                in 89..104 -> selectedDamageBonusIndex.value = 8
                in 105..120 -> selectedDamageBonusIndex.value = 9
                in 121..136 -> selectedDamageBonusIndex.value = 10
                in 137..152 -> selectedDamageBonusIndex.value = 11
                in 153..168 -> selectedDamageBonusIndex.value = 12
                in 169..184 -> selectedDamageBonusIndex.value = 13
                else -> selectedDamageBonusIndex.value = 2
            }
            damageBonus.value = DamageBonus.values()[selectedDamageBonusIndex.value!!]
        }
    }

    /**
     * Calculate character's idea score
     */
    fun calculateIdeaScore(intelligence: DomainRollsCharacteristic?) {
        if (intelligence?.characteristicTotal != null) {
            ideaScore.value = intelligence.characteristicTotal!! * 5
        } else {
            ideaScore.value = 0
        }
    }

    /**
     * Calculate character's chance score
     */
    fun calculateSanityScore(power: DomainRollsCharacteristic?) {
        if (power?.characteristicTotal != null) {
            var score = power.characteristicTotal!! * 5
            sanityScore.value = score
        }
    }
    /**
     * Calculate character's luck score
     */
    fun calculateLuckScore(power: DomainRollsCharacteristic?) {
        if (power?.characteristicTotal != null) {
            var score = power.characteristicTotal!! * 5
            luckScore.value = score
        }
    }
    /**
     * Calculate character's know score
     */
    fun calculateKnowScore(education: DomainRollsCharacteristic) {
        Log.d("DEBUG$TAG", "calculateKnowPoints")
        if (education.characteristicTotal != null) {
            Log.d("DEBUG$TAG", "education?.characteristicTotal : ${education.characteristicTotal}")
            var score = education.characteristicTotal!! * 5
            knowScore.value = score
        }
    }

    /**
     * Calculate character's base health
     */
    fun calculateBaseHealth(rollsCharacteristics: List<DomainRollsCharacteristic>) {
        Log.d(TAG, "calculate base health")
        var hp = 0
        rollsCharacteristics.forEach {
            Log.d("DEBUG$TAG", "Roll characteristic : $it")
            if (it.characteristicTotal != null) {
                hp += it.characteristicTotal!!
            }
        }
        baseHealth.value = hp / 2

    }

    /**
     * Calculate character's total health.
     */
    fun calculateTotalHealth() {
        var baseHealthValue = baseHealth.value
        var breedHealthBonusValue = breedHealthBonus.value

        if (baseHealthValue != null && breedHealthBonusValue != null) {
            Log.d(
                TAG,
                "base health : ${baseHealthValue}\nbreed health bonus : ${breedHealthBonusValue}"
            )
            totalHealth.value = baseHealthValue + breedHealthBonusValue
        }
    }
    /**
     * Calculate character's breed health bonus score
     */
    fun calculateBreedsHealthBonus(displayedBreeds: List<DomainDisplayedBreed>): Int {
        Log.d(TAG, "calculateBreedsHealthBonus")
        var bonus: Int = 0
        displayedBreeds.forEach {
            if (it.breedHealthBonus != null) {
                bonus += it.breedHealthBonus!!
            }
        }
        breedHealthBonus.value = bonus
        return breedHealthBonus.value!!
    }


}