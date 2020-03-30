/** File DbRace.kt
 *   @Author pierre.antoine - 24/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.infrastructure.database_model.db_race

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.DomainRace



@Entity(tableName = DatabaseValues.TABLE_NAME_RACE)
class DbRace(
    @PrimaryKey(autoGenerate = true)
    val raceId: Long?,
    val raceName: String?,
    var raceDescription: String?
) : DbEntity<DomainRace> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainRace {
        return DomainRace(
            raceId = this.raceId,
            raceName = this.raceName,
            raceDescription = this.raceDescription
        )
    }

    companion object :
        DbCompanion<DomainRace, DbRace> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainRace?): DbRace {
            return DbRace(
                raceId = domainModel?.raceId,
                raceDescription = domainModel?.raceDescription,
                raceName = domainModel?.raceName
            )

        }

    }
}
