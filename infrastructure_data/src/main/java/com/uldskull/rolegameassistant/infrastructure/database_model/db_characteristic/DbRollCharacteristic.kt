// File DbRollCharacteristic.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollCharacteristic

/**
 *   Class "DbRollCharacteristic" :
 *   TODO: Fill class use.
 **/
@Entity(tableName = DatabaseValues.TABLE_NAME_ROLL_CHARACTERISTICS)
class DbRollCharacteristic(
    @PrimaryKey(autoGenerate = true)
    val characteristicId: Long?,
    val characteristicName: String?,
    val characteristicBonus: Int?,
    val characteristicMax: Int?,
    val characteristicRoll: Int?,
    val characteristicTotal: Int?
) :
    DbEntity<DomainRollCharacteristic> {

    companion object : DbCompanion<DomainRollCharacteristic, DbRollCharacteristic> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainRollCharacteristic?): DbRollCharacteristic {

            return DbRollCharacteristic(
                characteristicId = domainModel?.characteristicId,
                characteristicRoll = domainModel?.characteristicRoll,
                characteristicTotal = domainModel?.characteristicTotal,
                characteristicBonus = domainModel?.characteristicBonus,
                characteristicName = domainModel?.characteristicName,
                characteristicMax = domainModel?.characteristicMax

            )
        }

    }

    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainRollCharacteristic {
        return DomainRollCharacteristic(
            characteristicId = this.characteristicId,
            characteristicName = this.characteristicName,
            characteristicBonus = this.characteristicBonus,
            characteristicRoll = this.characteristicRoll,
            characteristicTotal = this.characteristicTotal,
            characteristicMax = this.characteristicMax
        )
    }
}

