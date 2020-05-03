// File DbJob.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.TABLE_NAME_OCCUPATIONS
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation

/**
 *   Class "DbJob" :
 *   TODO: Fill class use.
 **/
@Entity(tableName = TABLE_NAME_OCCUPATIONS)
class DbOccupation(
    @PrimaryKey(autoGenerate = true)
    val occupationId: Long?= null,
    val occupationName: String? = "occupation name",
    var occupationIncome:String? = "occupation income",
    var occupationContacts:String? = "occupation contacts",
    var occupationSpecial:String? = "occupation specials"
) : DbEntity<DomainOccupation> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainOccupation {
        return DomainOccupation(
            occupationId = occupationId,
            occupationName = occupationName,
            occupationContacts = occupationContacts,
            occupationIncome = occupationIncome,
            occupationSpecial = occupationSpecial
        )
    }

    companion object :
        DbCompanion<DomainOccupation, DbOccupation> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainOccupation?): DbOccupation {
            return DbOccupation(
                occupationId = domainModel?.activityId,
                occupationName = domainModel?.activityName,
                occupationSpecial = domainModel?.occupationSpecial,
                occupationIncome = domainModel?.occupationIncome,
                occupationContacts = domainModel?.occupationContacts
            )
        }

    }
}