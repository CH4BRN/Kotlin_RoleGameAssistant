// File DerivedValuesViewModel.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.DomainIdeal
import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.useCases.alignment.GetAlignmentScoreUseCase
import com.uldskull.rolegameassistant.useCases.damageBonus.DamageBonus
import com.uldskull.rolegameassistant.useCases.damageBonus.GetDamageBonusScoreUseCase
import com.uldskull.rolegameassistant.useCases.damageBonus.GetDamageBonusUseCase
import com.uldskull.rolegameassistant.useCases.damageBonus.GetSizePlusStrengthUseCase
import com.uldskull.rolegameassistant.useCases.energyPoints.GetEnergyPointsUseCase
import com.uldskull.rolegameassistant.useCases.health.GetBaseHealthUseCase
import com.uldskull.rolegameassistant.useCases.health.GetBreedsHealthBonusUseCase
import com.uldskull.rolegameassistant.useCases.health.GetTotalHealthUseCase
import com.uldskull.rolegameassistant.useCases.ideaScore.GetIdeaScoreUseCase
import com.uldskull.rolegameassistant.useCases.know.GetKnowScoreUseCase
import com.uldskull.rolegameassistant.useCases.luck.GetLuckScoreUseCase
import com.uldskull.rolegameassistant.useCases.sanityScore.GetSanityScoreUseCase

/**
 *   Class "DerivedValuesViewModel" :
 *   Viewmodel for derived values
 **/
class DerivedValuesViewModel(
    application: Application,
    /**
     * Get base health use case
     */
    private val getBaseHealthUseCase: GetBaseHealthUseCase,
    /**
     * Get alignment score use case
     */
    private val getAlignmentScoreUseCase: GetAlignmentScoreUseCase,
    /**
     * get size plus strength score use case
     */
    private val getSizePlusStrengthUseCase: GetSizePlusStrengthUseCase,
    /**
     * get damage bonus score use case
     */
    private val getDamageBonusScoreUseCase: GetDamageBonusScoreUseCase,
    /**
     * Get damage bonus use case
     */
    private val getDamageBonusUseCase: GetDamageBonusUseCase,
    /**
     * Get idea score use case
     */
    private val getIdeaScoreUseCase: GetIdeaScoreUseCase,
    /**
     * get energy points use case
     */
    private val getEnergyPointsUseCase: GetEnergyPointsUseCase,
    /**
     * get sanity score use case
     */
    private val getSanityScoreUseCase: GetSanityScoreUseCase,
    /**
     * get luck score use case
     */
    private val getLuckScoreUseCase: GetLuckScoreUseCase,
    /**
     * get know score use case
     */
    private val getKnowScoreUseCase: GetKnowScoreUseCase,
    /**
     * get breed health bonus use case
     */
    private val getBreedsHealthBonusUseCase: GetBreedsHealthBonusUseCase,
    /**
     * get total health use case
     */
    private val getTotalHealthUseCase: GetTotalHealthUseCase
) : AndroidViewModel(application) {
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
     *  Has base health edit text changed ?
     */
    var baseHealthEditTextHasChanged: Boolean = false

    /**
     *  Has breed bonus edit text changed ?
     */
    var breedBonusEditTextHasChanged: Boolean = false

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

    /**
     * Observable alignment score
     */
    var alignmentScore: MutableLiveData<Int?> = MutableLiveData()

    init {
        if (cthulhuMythScore.value == 0 || cthulhuMythScore.value == null) {
            cthulhuMythScore.value = 99
        }
    }

    /**
     * Calculate alignment score
     */
    fun calculateAlignmentScore(ideals: List<DomainIdeal?>) {
        alignmentScore.value = getAlignmentScoreUseCase?.execute(ideals)
    }

    /**
     * Calculate energy points
     */
    fun calculateEnergyPoints(power: DomainRollsCharacteristic?) {
        energyPoints.value = getEnergyPointsUseCase.execute(power)
    }

    /**
     * Calculate damage bonus
     */
    fun calculateDamageBonus(size: DomainRollsCharacteristic, strength: DomainRollsCharacteristic) {
        if (size.characteristicTotal != null && strength.characteristicTotal != null) {
            var score = getSizePlusStrengthUseCase?.execute(listOf(size, strength))
            sizePlusStrengthScore.value = score

            var value = getDamageBonusScoreUseCase?.execute(score)
            damageBonus.value = getDamageBonusUseCase.execute(value)
        }
    }

    /**
     * Calculate character's idea score
     */
    fun calculateIdeaScore(intelligence: DomainRollsCharacteristic?) {
        ideaScore.value = getIdeaScoreUseCase.execute(intelligence)
    }

    /**
     * Calculate character's chance score
     */
    fun calculateSanityScore(power: DomainRollsCharacteristic?) {
        sanityScore.value = getSanityScoreUseCase.execute(power)
    }

    /**
     * Calculate character's luck score
     */
    fun calculateLuckScore(power: DomainRollsCharacteristic?) {
        luckScore.value = getLuckScoreUseCase.execute(power)
    }

    /**
     * Calculate character's know score
     */
    fun calculateKnowScore(education: DomainRollsCharacteristic?) {
        knowScore.value = getKnowScoreUseCase.execute(education)
    }

    /**
     * Calculate character's base health
     */
    fun calculateBaseHealth(rollsCharacteristics: List<DomainRollsCharacteristic?>) {
        baseHealth.value = getBaseHealthUseCase?.execute(rollsCharacteristics)
    }

    /**
     * Calculate character's total health.
     */
    fun calculateTotalHealth() {
        val baseHealthValue = baseHealth.value
        val breedHealthBonusValue = breedHealthBonus.value
        totalHealth.value = getTotalHealthUseCase?.execute(listOf(baseHealthValue, breedHealthBonusValue))
    }

    /**
     * Calculate character's breed health bonus score
     */
    fun calculateBreedsHealthBonus(displayedBreeds: List<DomainDisplayedBreed>) {
        Log.d(TAG, "calculateBreedsHealthBonus")
        var bonus = getBreedsHealthBonusUseCase.execute(displayedBreeds)
        breedHealthBonus.value = bonus
    }


}