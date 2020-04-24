// File DbOccupationSkill.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_skill

import androidx.room.Entity
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill

/**
 *   Class "DbOccupationSkill" :
 *   TODO: Fill class use.
 **/
@Entity(tableName = DatabaseValues.TABLE_NAME_OCCUPATION_SKILL)
class DbOccupationSkill(
    var skillId: Long? = null,
    var skillName: String?,
    var skillDescription: String?,
    var skillOccupationId: Long? = null
) : DbEntity<DomainOccupationSkill> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainOccupationSkill {
        return DomainOccupationSkill(
            skillId = this.skillId,
            skillOccupationId = this.skillOccupationId,
            skillDescription = this.skillDescription,
            skillName = this.skillName
        )
    }

    companion object : DbCompanion<DomainOccupationSkill, DbOccupationSkill> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainOccupationSkill?): DbOccupationSkill {
            return DbOccupationSkill(
                skillId = domainModel?.skillId,
                skillName = domainModel?.skillName,
                skillDescription = domainModel?.skillDescription,
                skillOccupationId = domainModel?.skillOccupationId
            )
        }
    }
}