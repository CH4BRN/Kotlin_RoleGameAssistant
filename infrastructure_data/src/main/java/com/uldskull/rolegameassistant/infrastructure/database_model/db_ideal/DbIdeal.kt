// File DbIdeal.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_IDEAL
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.DomainIdeal

/**
 *   Class "DbIdeal" :
 *   Database model for ideal
 **/
@Entity(tableName = TABLE_NAME_IDEAL)
class DbIdeal(
    /**
     * Ideal's identifier
     */
    @PrimaryKey(autoGenerate = true)
    val idealId: Long?,
    /**
     * Ideal's name
     */
    val idealName: String?,
    /**
     * Ideal's good points
     */
    val idealGoodPoints: Int?,
    /**
     * Ideal's evil points
     */
    val idealEvilPoints: Int?,
    /**
     * Is ideal checked ?
     */
    var isChecked: Boolean? = false
) : DbEntity<DomainIdeal> {

    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainIdeal {
        return DomainIdeal(
            idealId = this.idealId,
            idealGoodPoints = this.idealGoodPoints,
            idealEvilPoints = this.idealEvilPoints,
            isChecked = this.isChecked,
            idealName = this.idealName
        )
    }

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DbIdeal(idealId=$idealId, idealName=$idealName, idealGoodPoints=$idealGoodPoints, idealEvilPoints=$idealEvilPoints, isChecked=$isChecked)"
    }


    companion object :
        DbCompanion<DomainIdeal, DbIdeal> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainIdeal?): DbIdeal {


                return DbIdeal(
                    idealId = domainModel?.idealId,
                    idealName = domainModel?.idealName,
                    idealEvilPoints = domainModel?.idealEvilPoints,
                    idealGoodPoints = domainModel?.idealGoodPoints,
                    isChecked =  domainModel?.isChecked

                )

        }

    }
}