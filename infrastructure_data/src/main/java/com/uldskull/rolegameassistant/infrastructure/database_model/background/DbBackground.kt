// File DbBackground.kt
// @Author pierre.antoine - 14/02/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.background

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_BACKGROUND
import com.uldskull.rolegameassistant.infrastructure.database_model.contracts.AsDomainModels
import com.uldskull.rolegameassistant.infrastructure.database_model.contracts.ToDomain
import com.uldskull.rolegameassistant.models.background.DomainBackground

/**
 *   Class "DbBackground" :
 *   Database model for background
 **/
@Entity(tableName = TABLE_NAME_BACKGROUND)
data class DbBackground (
    @PrimaryKey(autoGenerate = true) val dbBackgroundId: Long?
):
AsDomainModels<DomainBackground, DbBackground>,
    ToDomain<DomainBackground>{
    companion object {
        /**
         * Converts to database model.
         */
        fun from(domainBackground: DomainBackground): DbBackground {
            return DbBackground(
                dbBackgroundId = domainBackground.backggroundId
            )
        }
    }

    /**
     * Converts to domain model.
     */
   override fun toDomain(): DomainBackground {
        return DomainBackground(
            backggroundId = dbBackgroundId
        )
    }

    override fun List<DbBackground>.asDomainModel(): List<DomainBackground> {
        return map{
            toDomain()
        }
    }
}


