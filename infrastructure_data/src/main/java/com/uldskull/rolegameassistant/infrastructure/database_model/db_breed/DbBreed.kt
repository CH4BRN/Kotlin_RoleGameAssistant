/** File DbRace.kt
 *   @Author pierre.antoine - 24/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.infrastructure.database_model.db_breed

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed


@Entity(tableName = DatabaseValues.TABLE_NAME_BREED)
class DbBreed(
    @PrimaryKey(autoGenerate = true)
    val breedId: Long?,
    val breedName: String?,
    var breedDescription: String?,
    var breedHealthBonus: Int?
) : DbEntity<DomainBreed> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainBreed {
        return DomainBreed(
            breedId = this.breedId,
            breedName = this.breedName,
            breedDescription = this.breedDescription,
            breedHealthBonus = this.breedHealthBonus
        )
    }

    override fun toString(): String = "\nBreed : $breedName\nid : $breedId"


    companion object :
        DbCompanion<DomainBreed, DbBreed> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainBreed?): DbBreed {
            return DbBreed(
                breedId = domainModel?.breedId,
                breedDescription = domainModel?.breedDescription,
                breedName = domainModel?.breedName,
                breedHealthBonus = domainModel?.breedHealthBonus
            )

        }

    }
}
