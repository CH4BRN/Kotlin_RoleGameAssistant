// File DbBiography.kt
// @Author pierre.antoine - 14/02/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.background

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_BIOGRAPHY
import com.uldskull.rolegameassistant.models.background.DomainBiography

/**
 *   Class "DbBiography" :
 *   Database model for biography.
 **/
@Entity(tableName = TABLE_NAME_BIOGRAPHY)
data class DbBiography(
    @PrimaryKey(autoGenerate = true) val dbBiographyId: Long?,
    val dbBiographyValue: String?
) {
    companion object {
        /**
         * Converts to database model
         */
        fun from(domainBiography: DomainBiography): DbBiography {
            return DbBiography(
                dbBiographyId = domainBiography.biographyId,
                dbBiographyValue = domainBiography.biographyValue
            )
        }
    }

    /**
     * Converts to domain model.
     */
    fun toDomain(): DomainBiography {
        return DomainBiography(
            biographyId = dbBiographyId,
            biographyValue = dbBiographyValue
        )
    }

}