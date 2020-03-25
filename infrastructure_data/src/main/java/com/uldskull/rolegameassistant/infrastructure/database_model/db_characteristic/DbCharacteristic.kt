// File DbCharacteristic.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic

/**
 *   Class "DbCharacteristic" :
 *   Database model to persist Characteristic data.
 **/

@Entity(tableName = DatabaseValues.TABLE_NAME_CHARACTERISTICS)
open class DbCharacteristic(
    @PrimaryKey(autoGenerate = true)
    val characteristicId: Long?,
    val characteristicName: String
) : DbEntity<DomainCharacteristic> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainCharacteristic {
        return DomainCharacteristic(
            characteristicId = this.characteristicId,
            characteristicName = this.characteristicName
        )
    }

    companion object : DbCompanion<DomainCharacteristic, DbCharacteristic> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainCharacteristic): DbCharacteristic {
            return DbCharacteristic(
                characteristicId = domainModel.characteristicId,
                characteristicName = domainModel.characteristicName
            )
        }
    }


}