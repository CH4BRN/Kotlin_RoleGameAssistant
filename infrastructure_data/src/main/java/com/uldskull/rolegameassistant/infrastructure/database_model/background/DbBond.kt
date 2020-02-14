// File DbBond.kt
// @Author pierre.antoine - 14/02/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.background

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.TABLE_NAME_BOND
import com.uldskull.rolegameassistant.models.background.DomainBond

/**
 * //TODO("Comment")
 *   Class "DbBond" :
 *   Database model for bond .
 **/
@Entity(tableName = TABLE_NAME_BOND)
data class DbBond(
    @PrimaryKey(autoGenerate = true) val dbBondId: Long?,
    val dbBondValue: String?
) {
    companion object {
        fun from(domainBond: DomainBond): DbBond {
            return DbBond(
                dbBondId = domainBond.bondId,
                dbBondValue = domainBond.bondValue
            )
        }
    }

    fun toDomain(): DomainBond {
        return DomainBond(
            bondId = dbBondId,
            bondValue = dbBondValue
        )
    }
}