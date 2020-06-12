// File DbOccupationSkill.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_skill

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_OCCUPATION_SKILL
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill

/**
 *   Class "DbOccupationSkill" :
 *   TODO: Fill class use.
 **/
@Entity(tableName = TABLE_NAME_OCCUPATION_SKILL)
class DbOccupationSkill(
    @PrimaryKey(autoGenerate = true)
    var skillId: Long? = null,
    var skillName: String?,
    var skillDescription: String?,
    var skillIsChecked: Boolean = false,
    var skillBase: Int? = 0,
    var skillMax: Int? = 0
    ) : DbEntity<DomainOccupationSkill> {

    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainOccupationSkill {

        return DomainOccupationSkill(
            skillId = this.skillId,
            skillDescription = this.skillDescription,
            skillName = this.skillName,
            skillIsChecked = this.skillIsChecked,
            skillBase = this.skillBase,
            skillMax = this.skillMax
        )
    }

    override fun toString(): String {
        return "DbOccupationSkill(skillId=$skillId, skillName=$skillName, skillDescription=$skillDescription, skillIsChecked=$skillIsChecked, skillBase=$skillBase, skillMax=$skillMax)"
    }

    companion object : DbCompanion<DomainOccupationSkill, DbOccupationSkill> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainOccupationSkill?): DbOccupationSkill {
            if (domainModel != null) {
                return DbOccupationSkill(
                    skillId = domainModel.skillId,
                    skillName = domainModel.skillName,
                    skillDescription = domainModel.skillDescription,
                    skillIsChecked = domainModel.skillIsChecked,
                    skillMax = domainModel.skillMax,
                    skillBase = domainModel.skillBase

                )
            } else {
                throw Exception("domain model is null.")
            }

        }
    }
}