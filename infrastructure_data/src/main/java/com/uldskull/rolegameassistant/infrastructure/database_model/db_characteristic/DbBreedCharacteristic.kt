// File DbBonusCharacteristic.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_BREED_CHARACTERISTICS
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedsCharacteristic

/**
 *   Class "DbBonusCharacteristic" :
 *   TODO: Fill class use.
 **/
@Entity(tableName = TABLE_NAME_BREED_CHARACTERISTICS)
class DbBreedCharacteristic(
    @PrimaryKey(autoGenerate = true)
    val characteristicId: Long? = null,
    val characteristicName: String?,
    var characteristicBonus: Int = 0,
    val characteristicBreedId: Long?
) : DbEntity<DomainBreedsCharacteristic> {


    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainBreedsCharacteristic {
        return DomainBreedsCharacteristic(
            characteristicId = this.characteristicId,
            characteristicName = this.characteristicName,
            characteristicBonus = this.characteristicBonus,
            characteristicBreedId = this.characteristicBreedId
        )
    }

    override fun toString(): String {
        return "DbBreedCharacteristic(characteristicId=$characteristicId, characteristicName=$characteristicName, characteristicBonus=$characteristicBonus, characteristicBreedId=$characteristicBreedId)"
    }

    companion object : DbCompanion<DomainBreedsCharacteristic, DbBreedCharacteristic> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainBreedsCharacteristic?): DbBreedCharacteristic {
            return DbBreedCharacteristic(
                characteristicId = domainModel?.characteristicId,
                characteristicBonus = domainModel?.characteristicBonus!!,
                characteristicName = domainModel.characteristicName,
                characteristicBreedId = domainModel.characteristicBreedId
            )
        }
    }
}