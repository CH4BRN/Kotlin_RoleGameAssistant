// File DbBond.kt
// @Author pierre.antoine - 02/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_bond

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_BOND
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.DomainBond

/**
 *   Class "DbBond" :
 *   Holds a Bond
 **/
@Entity(tableName = TABLE_NAME_BOND)
class DbBond(
    /**
     * Bond identifier
     */
    @PrimaryKey(autoGenerate = true)
    val bondId: Long? = null,
    /**
     * Bond title
     */
    val bondTitle: String?,
    /**
     * Bond value
     */
    val bondValue: String?
) : DbEntity<DomainBond> {

    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainBond {
        return DomainBond(
            bondId = this.bondId,
            bondTitle = this.bondTitle,
            bondValue = this.bondValue
        )
    }

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DbBond(bondId=$bondId, bondTitle=$bondTitle, bondValue=$bondValue)"
    }

    companion object :
        DbCompanion<DomainBond, DbBond> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainBond?): DbBond {
            return DbBond(
                bondId = domainModel?.bondId,
                bondValue = domainModel?.bondValue,
                bondTitle = domainModel?.bondTitle
            )
        }

    }
}

