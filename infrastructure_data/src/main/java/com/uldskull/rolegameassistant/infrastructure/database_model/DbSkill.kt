// File DbSkill.kt
// @Author pierre.antoine - 13/02/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_SKILL

import com.uldskull.rolegameassistant.models.skill.DomainSkill

/**
 *   Class "DbSkill" :
 *   Database skill model.
 **/
@Entity(tableName = TABLE_NAME_SKILL)
data class DbSkill(
    @PrimaryKey(autoGenerate = true) val skillId: Long? = null,
    val skillName: String?,
    val skillAbility: String?,
    val skillModifier: Int?
) {
    companion object {
        /**
         * Converts to Database model
         */
        fun from(domainSkill: DomainSkill): DbSkill {
            return DbSkill(
                domainSkill.skillId,
                domainSkill.skillName,
                domainSkill.skillAbility,
                domainSkill.skillModifier
            )
        }
    }

    /**
     * Converts to domain model.
     */
    fun toDomain(): DomainSkill {
        return DomainSkill(
            skillId = skillId,
            skillName = skillName,
            skillAbility = skillAbility,
            skillModifier = skillModifier
        )
    }
}