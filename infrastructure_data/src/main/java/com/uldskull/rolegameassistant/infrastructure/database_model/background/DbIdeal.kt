// File DbIdeal.kt
// @Author pierre.antoine - 14/02/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.background

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.TABLE_NAME_IDEAL
import com.uldskull.rolegameassistant.models.background.DomainIdeal

/**
 * //TODO("Comment")
 *   Class "DbIdeal" :
 *   Database model for ideal .
 **/
@Entity(tableName = TABLE_NAME_IDEAL)
data class DbIdeal(
    @PrimaryKey(autoGenerate = true) val dbIdealId: Long?,
    val dbIdealValue: String?
) {
    companion object {
        fun from(domainIdeal: DomainIdeal): DbIdeal {
            return DbIdeal(
                dbIdealId = domainIdeal.idealId,
                dbIdealValue = domainIdeal.idealValue
            )
        }
    }

    fun toDomain(): DomainIdeal {
        return DomainIdeal(
            idealId = dbIdealId,
            idealValue = dbIdealValue
        )
    }
}