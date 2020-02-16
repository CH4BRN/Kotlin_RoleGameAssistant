// File DbHealth.kt
// @Author pierre.antoine - 14/02/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.health

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_HEALTH

import com.uldskull.rolegameassistant.models.health.DomainHealth

/**
 *   Class "DbHealth" :
 *   Database health model
 **/
@Entity(tableName = TABLE_NAME_HEALTH)
data class DbHealth(
    @PrimaryKey(autoGenerate = true) val dbHealthId: Long? = null,
    val dbHealthHpMod: Int?,
    val dbHealthUseMax: Boolean?,
    val dbHealthMaxHealth: Int?
) {
    companion object {
        /**
         * Converts to Database model
         */
        fun from(domainHealth: DomainHealth): DbHealth {
            return DbHealth(
                dbHealthId = domainHealth.healthId,
                dbHealthHpMod = domainHealth.healthHpMod,
                dbHealthMaxHealth = domainHealth.healthMaxHealth,
                dbHealthUseMax = domainHealth.healthUseMax
            )
        }
    }

    /**
     * Converts to domain model.
     */
    fun toDomain(): DomainHealth {
        return DomainHealth(
            dbHealthId,
            dbHealthHpMod,
            dbHealthUseMax,
            dbHealthMaxHealth
        )
    }

}