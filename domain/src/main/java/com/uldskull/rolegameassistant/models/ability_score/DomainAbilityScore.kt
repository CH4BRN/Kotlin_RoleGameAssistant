// DomainAbilityScore.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.models.ability_score

/**
Class "DomainAbilityScore"

Domain model for ability score.
 */
class DomainAbilityScore(
    val abilityScoreId: Long? = null,
    val abilityScoreAbility: Ability? = null,
    val abilityScoreRoll: Int? = 0,
    val abilityScoreBonus: Int? = 0,
    var abilityScoreTotal: Int? = 0
)