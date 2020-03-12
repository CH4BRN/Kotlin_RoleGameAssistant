// File AbilitiesViewModel.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.abilities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.ability_score.Ability
import com.uldskull.rolegameassistant.models.ability_score.DomainAbilityScore

/**
 *   Class "AbilitiesViewModel" :
 *   Designed to store and manage UI-related data in a lifecycle conscious way.
 *   The ViewModel class allows data to survive configuration changes such as
 *   screen rotations.
 **/
class AbilitiesViewModel(application: Application) : AndroidViewModel(application) {

    /** Abilities to display    **/
    var abilities = MutableLiveData<List<DomainAbilityScore>>()

    init {
        abilities.value = listOf(
            DomainAbilityScore(
                null,
                Ability.STRENGTH,
                abilityScoreRoll = 1,
                abilityScoreBonus = 5,
                abilityScoreTotal = 6
            ),
            DomainAbilityScore(
                null,
                Ability.SIZE,
                abilityScoreRoll = 2,
                abilityScoreBonus = 5,
                abilityScoreTotal = 6
            ),
            DomainAbilityScore(
                null,
                Ability.POWER,
                abilityScoreRoll = 3,
                abilityScoreBonus = 5,
                abilityScoreTotal = 6
            ),
            DomainAbilityScore(
                null,
                Ability.INTELLIGENCE,
                abilityScoreRoll = 41,
                abilityScoreBonus = 5,
                abilityScoreTotal = 6
            ),
            DomainAbilityScore(
                null,
                Ability.DEXTERITY,
                abilityScoreRoll = 5,
                abilityScoreBonus = 5,
                abilityScoreTotal = 6
            ),
            DomainAbilityScore(
                null,
                Ability.CONSTITUTION,
                abilityScoreRoll = 6,
                abilityScoreBonus = 5,
                abilityScoreTotal = 6
            ),
            DomainAbilityScore(
                null,
                Ability.CHARISMA,
                abilityScoreRoll = 7,
                abilityScoreBonus = 5,
                abilityScoreTotal = 6
            ),
            DomainAbilityScore(
                null,
                Ability.APPEARANCE,
                abilityScoreRoll = 8,
                abilityScoreBonus = 5,
                abilityScoreTotal = 6
            )
        )

    }
}