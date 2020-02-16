// DbRace.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database_model.basic_info

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_RACE
import com.uldskull.rolegameassistant.infrastructure.database_model.contracts.AsDomainModels
import com.uldskull.rolegameassistant.infrastructure.database_model.contracts.ToDomain
import com.uldskull.rolegameassistant.models.basic_info.DomainRace

/**
Class "DbRace"

Database model for race.
 */
@Entity(tableName = TABLE_NAME_RACE)
class DbRace(
    @PrimaryKey(autoGenerate = true) val dbRaceId: Long? = null,
    val dbRaceName: String? = "name"
) :
    AsDomainModels<DomainRace, DbRace>,
    ToDomain<DomainRace> {
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
        return map {
            toDomain()
        }
    }
}