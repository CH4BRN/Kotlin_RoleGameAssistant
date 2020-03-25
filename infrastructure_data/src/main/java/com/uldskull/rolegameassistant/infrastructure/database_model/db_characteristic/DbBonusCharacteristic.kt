// File DbBonusCharacteristic.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBonusCharacteristic

/**
 *   Class "DbBonusCharacteristic" :
 *   TODO: Fill class use.
 **/
@Entity
class DbBonusCharacteristic(
    @PrimaryKey(autoGenerate = true)
    val characteristicId: Long?,
    val characteristicName: String,
    val characteristicBonus: Int,
    val characteristicMax: Int
) : DbEntity<DomainBonusCharacteristic> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainBonusCharacteristic {
        return DomainBonusCharacteristic(
            characteristicId = this.characteristicId,
            characteristicName = this.characteristicName,
            characteristicBonus = this.characteristicBonus,
            characteristicMax = this.characteristicMax
        )
    }

    companion object : DbCompanion<DomainBonusCharacteristic, DbBonusCharacteristic> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainBonusCharacteristic): DbBonusCharacteristic {
            return DbBonusCharacteristic(
                characteristicId = domainModel.characteristicId,
                characteristicMax = domainModel.characteristicMax,
                characteristicBonus = domainModel.characteristicBonus,
                characteristicName = domainModel.characteristicName
            )

        }
    }
}