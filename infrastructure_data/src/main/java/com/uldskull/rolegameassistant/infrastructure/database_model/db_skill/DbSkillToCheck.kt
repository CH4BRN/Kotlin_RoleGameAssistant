// File DbOccupationSkill.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_skill

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_SKILL_TO_CHECK
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.skill.DomainSkillToCheck

/**
 *   Class "DbOccupationSkill" :
 *   TODO: Fill class use.
 **/
@Entity(tableName = TABLE_NAME_SKILL_TO_CHECK)
class DbSkillToCheck(
    @PrimaryKey(autoGenerate = true)
    var skillId: Long? = null,
    var skillName: String?,
    var skillDescription: String?,
    var skillIsChecked: Boolean = false,
    var skillBase: Int? = 0,
    var skillMax: Int? = 0,
    var isUserSkill:Boolean? = false
    ) : DbEntity<DomainSkillToCheck> {

    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainSkillToCheck {

        return DomainSkillToCheck(
            skillId = this.skillId,
            skillDescription = this.skillDescription,
            skillName = this.skillName,
            skillIsChecked = this.skillIsChecked,
            skillBase = this.skillBase,
            skillMax = this.skillMax,
            skillIsUser = this.isUserSkill!!
        )
    }

    override fun toString(): String {
        return "DbSkillToCheck(skillId=$skillId, skillName=$skillName, skillDescription=$skillDescription, skillIsChecked=$skillIsChecked, skillBase=$skillBase, skillMax=$skillMax, isUserSkill=$isUserSkill)"
    }


    companion object : DbCompanion<DomainSkillToCheck, DbSkillToCheck> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainSkillToCheck?): DbSkillToCheck {
            if (domainModel != null) {
                return DbSkillToCheck(
                    skillId = domainModel.skillId,
                    skillName = domainModel.skillName,
                    skillDescription = domainModel.skillDescription,
                    skillIsChecked = domainModel.skillIsChecked,
                    skillMax = domainModel.skillMax,
                    skillBase = domainModel.skillBase,
                    isUserSkill = domainModel.skillIsUser

                )
            } else {
                throw Exception("domain model is null.")
            }

        }
    }
}