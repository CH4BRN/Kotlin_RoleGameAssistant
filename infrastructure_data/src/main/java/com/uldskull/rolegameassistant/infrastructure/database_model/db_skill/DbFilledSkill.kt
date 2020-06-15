// File DbFilledSkill.kt
// @Author pierre.antoine - 13/02/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_skill

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill


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
    val filledSkillMax: Int?,
    val filledSkillCharacterId:Long?
) : DbEntity<DomainSkillToFill> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainSkillToFill {

        return DomainSkillToFill(
            filledSkillId = this.filledSkillId,
            filledSkillTensValue = this.filledSkillTensValue,
            filledSkillUnitsValue = this.filledSkillUnitsValue,
            filledSkillBase = this.filledSkillBase,
            filledSkillMax = this.filledSkillMax,
            filledSkillName = this.filledSkillName,
            filledSkillTotal = this.filledSkillTotal,
            filledSkillCharacterId = this.filledSkillCharacterId
        )
    }

    override fun toString(): String {
        return "DbFilledSkill(filledSkillId=$filledSkillId, filledSkillName=$filledSkillName, filledSkillDescription=$filledSkillDescription, filledSkillBase=$filledSkillBase, filledSkillTensValue=$filledSkillTensValue, filledSkillUnitsValue=$filledSkillUnitsValue, filledSkillTotal=$filledSkillTotal, filledSkillMax=$filledSkillMax, filledSkillCharacterId=$filledSkillCharacterId)"
    }

    companion object :
        DbCompanion<DomainSkillToFill, DbFilledSkill> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainSkillToFill?): DbFilledSkill {
            return DbFilledSkill(
                filledSkillId = domainModel?.skillId,
                filledSkillTensValue = domainModel?.filledSkillTensValue,
                filledSkillUnitsValue = domainModel?.filledSkillUnitsValue,
                filledSkillBase = domainModel?.filledSkillBase,
                filledSkillDescription = domainModel?.skillDescription,
                filledSkillMax = domainModel?.filledSkillMax,
                filledSkillName = domainModel?.skillName,
                filledSkillTotal = domainModel?.filledSkillTotal,
                filledSkillCharacterId = domainModel?.filledSkillCharacterId

            )
        }
    }
}