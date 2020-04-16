// File DbIdeal.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.DomainIdeal

/**
 *   Class "DbIdeal" :
 *   TODO: Fill class use.
 **/
@Entity(tableName = DatabaseValues.TABLE_NAME_IDEAL)
class DbIdeal(
    @PrimaryKey(autoGenerate = true)
    val idealId: Long?,
    val idealName: String?,
    val idealGoodPoints: Int?,
    val idealEvilPoints: Int?
) : DbEntity<DomainIdeal> {

    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainIdeal {
        return DomainIdeal(
            idealId = this.idealId,
            idealGoodPoints = this.idealGoodPoints,
            idealEvilPoints = this.idealEvilPoints,
            isChecked = false,
            idealName = this.idealName
        )
    }

    override fun toString(): String {
        return "id : $idealId\nName : $idealName\nGood : ${idealGoodPoints.toString()}\nEvil : ${idealEvilPoints.toString()}"
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
                idealGoodPoints = domainModel?.idealGoodPoints

            )
        }

    }
}