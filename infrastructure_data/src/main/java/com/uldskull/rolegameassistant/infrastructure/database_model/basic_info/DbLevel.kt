// DbLevel.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database_model.basic_info

import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.basic_info.DomainLevel

/**
Class "DbLevel"

Database model for level
 */
class DbLevel(
    val dbLevelId: Long? = null,
    val dbLevelValue: Int? = 0,
    val dbLevelHpModifier: Int? = 0
) : DbEntity<DomainLevel> {
    /**
     * Converts into domain model
     */
    override fun toDomain(): DomainLevel {
        return DomainLevel(
            levelId = dbLevelId,
            levelHpModifier = dbLevelHpModifier,
            levelValue = dbLevelValue
        )
    }

    companion object {
        /**
         * Converts into database model
         */
        fun from(domainLevel: DomainLevel): DbLevel {
            return DbLevel(
                dbLevelId = domainLevel.levelId,
                dbLevelHpModifier = domainLevel.levelHpModifier,
                dbLevelValue = domainLevel.levelValue
            )
        }
    }
}