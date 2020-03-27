/** File DbRace.kt
 *   @Author pierre.antoine - 24/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.infrastructure.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.models.character.DomainRace


@Entity(tableName = DatabaseValues.TABLE_NAME_RACE)
class DbRace(
    @PrimaryKey(autoGenerate = true)
    val raceId: Long?,
    val raceName: String,
    var raceDescription: String
    /*  @Embedded
      val raceCharacteristics: List<DbBonusCharacteristic>*/
) : DbEntity<DomainRace> {


    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainRace {
        return DomainRace(
            raceId = this.raceId,
            raceName = this.raceName,
            raceDescription = this.raceDescription
            //,raceCharacteristics = this.raceCharacteristics.map { characteristic -> characteristic.toDomain() }
        )
    }

    companion object : DbCompanion<DomainRace, DbRace> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainRace): DbRace {
            return DbRace(
                raceId = domainModel.raceId
                /* raceCharacteristics = domainModel.raceCharacteristics.map { characteristic ->
                     DbBonusCharacteristic(
                         characteristicId = characteristic.characteristicId,
                         characteristicName = characteristic.characteristicName,
                         characteristicMax = characteristic.characteristicMax,
                         characteristicBonus = characteristic.characteristicBonus
                     )
                 }*/,
                raceDescription = domainModel.raceDescription,
                raceName = domainModel.raceName
            )

        }

    }
}
