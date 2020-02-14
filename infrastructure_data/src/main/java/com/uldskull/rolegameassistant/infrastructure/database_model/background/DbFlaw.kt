// File DbBond.kt
// @Author pierre.antoine - 14/02/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.background

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.TABLE_NAME_BOND
import com.uldskull.rolegameassistant.models.background.DomainFlaw

/**
 * //TODO("Comment")
 *   Class "DbBond" :
 *   Database model for bond .
 **/
@Entity(tableName = TABLE_NAME_BOND)
data class DbFlaw(
    @PrimaryKey(autoGenerate = true) val dbFlawId: Long?,
    val dbFlawValue: String?
) {
    companion object {
        fun from(domainFlaw: DomainFlaw): DbFlaw {
            return DbFlaw(
                dbFlawId = domainFlaw.flawId,
                dbFlawValue = domainFlaw.flawValue
            )
        }
    }

    fun toDomain(): DomainFlaw {
        return DomainFlaw(
            flawId = dbFlawId,
            flawValue = dbFlawValue
        )
    }
}