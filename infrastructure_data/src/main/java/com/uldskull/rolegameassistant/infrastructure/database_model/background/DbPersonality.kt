// File DbPersonality.kt
// @Author pierre.antoine - 14/02/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.background

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_PERSONALITY

import com.uldskull.rolegameassistant.models.background.DomainPersonality

/**
 *   Class "DbPersonality" :
 *   Database model for personality
 **/
@Entity(tableName = TABLE_NAME_PERSONALITY)
data class DbPersonality(
    @PrimaryKey(autoGenerate = true) val dbPersonalityId: Long? = null,
    val dbPersonalityValue: String?
) {
    companion object {
        /**
         * Converts to database model
         */
        fun from(domainPersonality: DomainPersonality): DbPersonality {
            return DbPersonality(
                dbPersonalityId = domainPersonality.personalityId,
                dbPersonalityValue = domainPersonality.personalityValue
            )
        }
    }

    /**
     * Converts to domain model
     */
    fun toDomain(): DomainPersonality {
        return DomainPersonality(
            personalityId = dbPersonalityId,
            personalityValue = dbPersonalityValue
        )
    }
}