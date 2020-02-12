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
            DomainAbilityScore(null, Ability.STRENGTH, roll = 1, bonus = 5, total = 6),
            DomainAbilityScore(null, Ability.SIZE, roll = 2, bonus = 5, total = 6),
            DomainAbilityScore(null, Ability.POWER, roll = 3, bonus = 5, total = 6),
            DomainAbilityScore(null, Ability.INTELLIGENCE, roll = 41, bonus = 5, total = 6),
            DomainAbilityScore(null, Ability.DEXTERITY, roll = 5, bonus = 5, total = 6),
            DomainAbilityScore(null, Ability.CONSTITUTION, roll = 6, bonus = 5, total = 6),
            DomainAbilityScore(null, Ability.CHARISMA, roll = 7, bonus = 5, total = 6),
            DomainAbilityScore(null, Ability.APPEARANCE, roll = 8, bonus = 5, total = 6)
        )
    }
}