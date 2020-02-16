// DbCharacteristics.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database_model.characteristics

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_CHARACTERISTICS
import com.uldskull.rolegameassistant.infrastructure.database_model.contracts.AsDomainModels
import com.uldskull.rolegameassistant.infrastructure.database_model.contracts.From
import com.uldskull.rolegameassistant.infrastructure.database_model.contracts.ToDomain
import com.uldskull.rolegameassistant.models.characteristics.DomainCharacteristics

/**
Class "DbCharacteristics"

TODO: Describe class utility.
 */
@Entity(tableName = TABLE_NAME_CHARACTERISTICS)
data class DbCharacteristics(
    @PrimaryKey(autoGenerate = true) val dbCharacteristicsId: Long? = null,
    val dbCharacteristicsHeight: Int? = 0,
    val dbCharacteristicsWeight: Int? = 0,
    val dbCharacteristicsAge: Int? = 0,
    val dbCharacteristicsCharacterId: Long? = null,
    val dbCharacteristicsGenderId: Long? = null
) : AsDomainModels<DomainCharacteristics, DbCharacteristics>,
    ToDomain<DomainCharacteristics> {
    companion object : From<DomainCharacteristics, DbCharacteristics> {
        override fun from(domainCharacteristics: DomainCharacteristics): DbCharacteristics {
            return DbCharacteristics(
                dbCharacteristicsAge = domainCharacteristics.characteristicsAge,
                dbCharacteristicsCharacterId = domainCharacteristics.characteristicsCharacterId,
                dbCharacteristicsGenderId = domainCharacteristics.characteristicsGenderId,
                dbCharacteristicsHeight = domainCharacteristics.characteristicsHeight,
                dbCharacteristicsId = domainCharacteristics.characteristicsId,
                dbCharacteristicsWeight = domainCharacteristics.characteristicsWeight
            )
        }

    }

    override fun List<DbCharacteristics>.asDomainModel(): List<DomainCharacteristics> {
        return map {
            toDomain()
        }
    }

    override fun toDomain(): DomainCharacteristics {
        return DomainCharacteristics(
            characteristicsAge = dbCharacteristicsAge,
            characteristicsCharacterId = dbCharacteristicsCharacterId,
            characteristicsGenderId = dbCharacteristicsGenderId,
            characteristicsHeight = dbCharacteristicsHeight,
            characteristicsId = dbCharacteristicsId,
            characteristicsWeight = dbCharacteristicsWeight

        )
    }
}