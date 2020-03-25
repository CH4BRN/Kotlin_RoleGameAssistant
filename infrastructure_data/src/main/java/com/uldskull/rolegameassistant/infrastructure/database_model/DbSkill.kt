// File DbSkill.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.models.character.DomainSkill

/**
 *   Class "DbSkill" :
 *   TODO: Fill class use.
 **/

/**
 *   Class "DbSkill" :
 *   Database skill model.
 */
@Entity
open class DbSkill(
    @PrimaryKey(autoGenerate = true)
    val skillId: Long,
    val skillName: String,
    val skillDescription: String
) : DbEntity<DomainSkill> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainSkill {
        return DomainSkill(
            skillId = this.skillId,
            skillName = this.skillName,
            skillDescription = this.skillDescription
        )
    }

    companion object :
        DbCompanion<DomainSkill, DbSkill> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainSkill): DbSkill {
            return DbSkill(
                skillId = domainModel.skillId,
                skillName = domainModel.skillName,
                skillDescription = domainModel.skillDescription
            )
        }

    }
}