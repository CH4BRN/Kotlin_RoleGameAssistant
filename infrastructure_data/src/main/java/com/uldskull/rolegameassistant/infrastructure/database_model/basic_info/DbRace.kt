// DbRace.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database_model.basic_info

import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.basic_info.DomainRace

/**
Class "DbRace"

Database model for race.
 */
class DbRace(
    val dbRaceId: Long? = null,
    val dbRaceName: String? = "name"
) : DbEntity<DomainRace, DbRace> {
    /**
     * Converts into domain model.
     */
    override fun toDomain(): DomainRace {
        return DomainRace(
            raceId = dbRaceId,
            raceName = dbRaceName
        )
    }

    companion object {
        /**
         * Converts into database model
         */
        fun from(domainRace: DomainRace): DbRace {
            return DbRace(
                dbRaceId = domainRace.raceId,
                dbRaceName = domainRace.raceName
            )
        }
    }

    override fun List<DbRace>.asDomainModel(): List<DomainRace> {
        return map{
            toDomain()
        }
    }
}