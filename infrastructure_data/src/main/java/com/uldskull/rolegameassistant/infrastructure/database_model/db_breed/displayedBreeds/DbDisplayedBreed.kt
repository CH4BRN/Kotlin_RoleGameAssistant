/** File DbRace.kt
 *   @Author pierre.antoine - 24/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_DISPLAYED_BREED
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed

/**
 * Database model for a breed
 */
@Entity(tableName = TABLE_NAME_DISPLAYED_BREED)
class DbDisplayedBreed(
    @PrimaryKey(autoGenerate = true)
    var breedId: Long? = null,
    val breedName: String?,
    var breedDescription: String?,
    var breedHealthBonus: Int?,
    var breedIsChecked: Boolean = false
) : DbEntity<DomainDisplayedBreed> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainDisplayedBreed {
        return DomainDisplayedBreed(
            breedId = this.breedId,
            breedName = this.breedName,
            breedDescription = this.breedDescription,
            breedHealthBonus = this.breedHealthBonus,
            breedChecked = this.breedIsChecked
        )
    }

    override fun toString(): String {
        return "DbDisplayedBreed(breedId=$breedId, breedName=$breedName, breedDescription=$breedDescription, breedHealthBonus=$breedHealthBonus, breedIsChecked=$breedIsChecked)"
    }


    companion object :
        DbCompanion<DomainDisplayedBreed, DbDisplayedBreed> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainDisplayedModel: DomainDisplayedBreed?): DbDisplayedBreed {
            if (domainDisplayedModel != null) {
                return DbDisplayedBreed(
                    breedId = domainDisplayedModel.breedId,
                    breedDescription = domainDisplayedModel.breedDescription,
                    breedName = domainDisplayedModel.breedName,
                    breedHealthBonus = domainDisplayedModel.breedHealthBonus,
                    breedIsChecked = domainDisplayedModel.breedChecked
                )
            } else {
                throw Exception("Breed is null")
            }
        }
    }
}
