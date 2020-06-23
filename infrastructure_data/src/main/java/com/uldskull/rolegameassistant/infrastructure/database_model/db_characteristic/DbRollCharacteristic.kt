// File DbRollCharacteristic.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_ROLL_CHARACTERISTICS
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollsCharacteristic

/**
 *   Class "DbRollCharacteristic" :
 *   Database model for roll characteristic.
 **/
@Entity(tableName = TABLE_NAME_ROLL_CHARACTERISTICS)
class DbRollCharacteristic(
    /**
     * Characteristic's identifier
     */
    @PrimaryKey(autoGenerate = true)
    val characteristicId: Long? = null,
    /**
     * Characteristic's name
     */
    val characteristicName: String?,
    /**
     * Characteristic's bonus
     */
    val characteristicBonus: Int? = 0,
    /**
     * Characteristic's max
     */
    val characteristicMax: Int? = 24,
    /**
     * Characteristic's roll
     */
    val characteristicRoll: Int? = 0,
    /**
     * Characteristic's total
     */
    val characteristicTotal: Int? = 0,
    /**
     * Characteristic's rule
     */
    val characteristicRollRule: String?
) :
    DbEntity<DomainRollsCharacteristic> {

    companion object : DbCompanion<DomainRollsCharacteristic, DbRollCharacteristic> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainRollsCharacteristic?): DbRollCharacteristic {

            return DbRollCharacteristic(
                characteristicId = domainModel?.characteristicId,
                characteristicRoll = domainModel?.characteristicRoll,
                characteristicTotal = domainModel?.characteristicTotal,
                characteristicBonus = domainModel?.characteristicBonus,
                characteristicName = domainModel?.characteristicName,
                characteristicMax = domainModel?.characteristicMax,
                characteristicRollRule = domainModel?.characteristicRollRule

            )
        }

    }

    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainRollsCharacteristic {
        return DomainRollsCharacteristic(
            characteristicId = this.characteristicId,
            characteristicName = this.characteristicName,
            characteristicBonus = this.characteristicBonus,
            characteristicRoll = this.characteristicRoll,
            characteristicTotal = this.characteristicTotal,
            characteristicMax = this.characteristicMax,
            characteristicRollRule = this.characteristicRollRule
        )
    }

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DbRollCharacteristic(characteristicId=$characteristicId, characteristicName=$characteristicName, characteristicBonus=$characteristicBonus, characteristicMax=$characteristicMax, characteristicRoll=$characteristicRoll, characteristicTotal=$characteristicTotal, characteristicRollRule=$characteristicRollRule)"
    }
}

