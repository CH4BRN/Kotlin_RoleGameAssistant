// DomainAbilityScore.ktre.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.models.ability_score

/**
Class "DomainAbilityScore"

Domain model for ability score.
 */
class DomainAbilityScore(
    val abilityScoreId: Long? = null,
    val ability: Ability = Ability.STRENGTH,
    val roll: Int? = 0,
    val bonus: Int? = 0,
    var total: Int? = 0
)