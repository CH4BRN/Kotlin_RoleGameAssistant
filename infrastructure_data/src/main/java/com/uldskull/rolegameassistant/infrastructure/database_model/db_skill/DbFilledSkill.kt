// File DbFilledSkill.kt
// @Author pierre.antoine - 13/02/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_skill

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.skill.DomainFilledSkill


/**
 *   Class "DbFilledSkill" :
 *   TODO: Fill class use.
 **/
@Entity
class DbFilledSkill(
    @PrimaryKey(autoGenerate = true)
    val filledSkillId: Long?,
    val filledSkillName: String?,
    val filledSkillDescription: String?,
    val filledSkillBase: Int?,
    val filledSkillTensValue: Int?,
    val filledSkillUnitsValue: Int?,
    val filledSkillTotal: Int?,
    val filledSkillMax: Int?
) : DbEntity<DomainFilledSkill> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainFilledSkill {

        return DomainFilledSkill(
            filledSkillId = this.filledSkillId,
            filledSkillTensValue = this.filledSkillTensValue,
            filledSkillUnitsValue = this.filledSkillUnitsValue,
            filledSkillBase = this.filledSkillBase,
            filledSkillMax = this.filledSkillMax,
            filledSkillName = this.filledSkillName,
            filledSkillTotal = this.filledSkillTotal
        )
    }

    companion object :
        DbCompanion<DomainFilledSkill, DbFilledSkill> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainFilledSkill?): DbFilledSkill {
            return DbFilledSkill(
                filledSkillId = domainModel?.skillId,
                filledSkillTensValue = domainModel?.filledSkillTensValue,
                filledSkillUnitsValue = domainModel?.filledSkillUnitsValue,
                filledSkillBase = domainModel?.filledSkillBase,
                filledSkillDescription = domainModel?.skillDescription,
                filledSkillMax = domainModel?.filledSkillMax,
                filledSkillName = domainModel?.skillName,
                filledSkillTotal = domainModel?.filledSkillTotal

            )
        }
    }
}