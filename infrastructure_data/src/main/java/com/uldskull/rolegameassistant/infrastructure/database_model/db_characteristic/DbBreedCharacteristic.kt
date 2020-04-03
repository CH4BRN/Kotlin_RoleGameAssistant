// File DbBonusCharacteristic.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedCharacteristic

/**
 *   Class "DbBonusCharacteristic" :
 *   TODO: Fill class use.
 **/
@Entity(tableName = DatabaseValues.TABLE_NAME_BREED_CHARACTERISTICS)
class DbBreedCharacteristic(
    @PrimaryKey(autoGenerate = true)
    val characteristicId: Long?,
    val characteristicName: String?,
    val characteristicBonus: Int?,
    val characteristicBreedId: Long?
) : DbEntity<DomainBreedCharacteristic> {


    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainBreedCharacteristic {
        return DomainBreedCharacteristic(
            characteristicId = this.characteristicId,
            characteristicName = this.characteristicName,
            characteristicBonus = this.characteristicBonus,
            characteristicBreedId = this.characteristicBreedId
        )
    }

    companion object : DbCompanion<DomainBreedCharacteristic, DbBreedCharacteristic> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainBreedCharacteristic?): DbBreedCharacteristic {
            return DbBreedCharacteristic(
                characteristicId = domainModel?.characteristicId,
                characteristicBonus = domainModel?.characteristicBonus,
                characteristicName = domainModel?.characteristicName,
                characteristicBreedId = domainModel?.characteristicBreedId
            )

        }
    }
}