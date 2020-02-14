// File DbAbilityScore.kt
// @Author pierre.antoine - 14/02/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.ability_score

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.TABLE_NAME_ABILITY_SCORE
import com.uldskull.rolegameassistant.models.ability_score.Ability
import com.uldskull.rolegameassistant.models.ability_score.DomainAbilityScore

/**
 *   Class "DbAbilityScore" :
 *   Database model class for ability score
 **/
@Entity(tableName = TABLE_NAME_ABILITY_SCORE)
class DbAbilityScore(
    @PrimaryKey(autoGenerate = true) val dbAbilityScoreId: Long?,
    val dbAbilityScoreAbility: Ability?,
    val dbAbilityScoreRoll: Int?,
    val dbAbilityScoreBonus: Int?,
    val dbAbilityScoreTotal: Int?
) {
    companion object {
        /**
         * Converts to database model
         */
        fun from(domainAbilityScore: DomainAbilityScore): DbAbilityScore {
            return DbAbilityScore(
                dbAbilityScoreId = domainAbilityScore.abilityScoreId,
                dbAbilityScoreAbility = domainAbilityScore.abilityScoreAbility,
                dbAbilityScoreBonus = domainAbilityScore.abilityScoreBonus,
                dbAbilityScoreRoll = domainAbilityScore.abilityScoreRoll,
                dbAbilityScoreTotal = domainAbilityScore.abilityScoreTotal
            )
        }
    }

    /**
     * Converts to domain model.
     */
    fun toDomain(): DomainAbilityScore {
        return DomainAbilityScore(
            abilityScoreId = dbAbilityScoreId,
            abilityScoreAbility = dbAbilityScoreAbility,
            abilityScoreBonus = dbAbilityScoreBonus,
            abilityScoreRoll = dbAbilityScoreRoll,
            abilityScoreTotal = dbAbilityScoreTotal
        )
    }
}