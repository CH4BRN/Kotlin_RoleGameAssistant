// DbLevel.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database_model.basic_info

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_LEVEL
import com.uldskull.rolegameassistant.infrastructure.database_model.contracts.AsDomainModels
import com.uldskull.rolegameassistant.infrastructure.database_model.contracts.ToDomain
import com.uldskull.rolegameassistant.models.basic_info.DomainLevel

/**
Class "DbLevel"

Database model for level
 */
@Entity(tableName = TABLE_NAME_LEVEL)
class DbLevel(
    @PrimaryKey(autoGenerate = true) val dbLevelId: Long? = null,
    val dbLevelValue: Int? = 0,
    val dbLevelHpModifier: Int? = 0
) :
    AsDomainModels<DomainLevel, DbLevel>,
    ToDomain<DomainLevel> {
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

    override fun List<DbLevel>.asDomainModel(): List<DomainLevel> {
        return map {
            toDomain()
        }
    }
}