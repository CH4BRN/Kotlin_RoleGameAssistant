// File DbBonusCharacteristic.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRaceCharacteristic

/**
 *   Class "DbBonusCharacteristic" :
 *   TODO: Fill class use.
 **/
@Entity(tableName = DatabaseValues.TABLE_NAME_RACE_CHARACTERISTICS)
class DbRaceCharacteristic(
    @PrimaryKey(autoGenerate = true)
    val characteristicId: Long?,
    val characteristicName: String?,
    val characteristicBonus: Int?,
    val characteristicRaceId: Long?
) : DbEntity<DomainRaceCharacteristic> {


    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainRaceCharacteristic {
        return DomainRaceCharacteristic(
            characteristicId = this.characteristicId,
            characteristicName = this.characteristicName,
            characteristicBonus = this.characteristicBonus,
            characteristicRaceId = this.characteristicRaceId
        )
    }

    companion object : DbCompanion<DomainRaceCharacteristic, DbRaceCharacteristic> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainRaceCharacteristic?): DbRaceCharacteristic {
            return DbRaceCharacteristic(
                characteristicId = domainModel?.characteristicId,
                characteristicBonus = domainModel?.characteristicBonus,
                characteristicName = domainModel?.characteristicName,
                characteristicRaceId = domainModel?.characteristicRaceId
            )

        }
    }
}