// File DbJob.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.TABLE_NAME_OCCUPATIONS
import com.uldskull.rolegameassistant.models.character.DomainOccupation

/**
 *   Class "DbJob" :
 *   TODO: Fill class use.
 **/
@Entity(tableName = TABLE_NAME_OCCUPATIONS)
class DbOccupation(
    @PrimaryKey(autoGenerate = true)
    val jobId: Long?,
    val jobName: String?,
    val jobDescription: String?
) : DbEntity<DomainOccupation> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainOccupation {
        return DomainOccupation(
            occupationId = jobId,
            occupationDescription = jobDescription,
            occupationName = jobName
        )
    }

    companion object : DbCompanion<DomainOccupation, DbOccupation> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainOccupation?): DbOccupation {
            return DbOccupation(
                jobId = domainModel?.activityId,
                jobName = domainModel?.activityName,
                jobDescription = domainModel?.activityDescription
            )
        }

    }
}