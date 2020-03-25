/** File DbRace.kt
 *   @Author pierre.antoine - 24/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.infrastructure.database_model

import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRollCharacteristic
import com.uldskull.rolegameassistant.models.character.DomainRace


class DbRace(
    val raceId: Long,
    val raceName: String,
    val raceCharacteristics: List<DbRollCharacteristic>

) : DbEntity<DomainRace> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainRace {
        return DomainRace(
            raceId = this.raceId,
            raceName = this.raceName,
            raceCharacteristics = this.raceCharacteristics.map { characteristic -> characteristic.toDomain() }
        )
    }
}
