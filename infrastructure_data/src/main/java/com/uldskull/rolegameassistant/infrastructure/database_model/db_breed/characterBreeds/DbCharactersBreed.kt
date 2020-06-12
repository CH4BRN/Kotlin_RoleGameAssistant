// DbCharacterBreed.kt created by UldSkull - 29/05/2020

package com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.characterBreeds

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_CHARACTERS_BREED
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.character.breed.charactersBreed.DomainCharactersBreed

/**
Class "DbCharacterBreed"

TODO: Describe class utility.
 */
@Entity(tableName = TABLE_NAME_CHARACTERS_BREED)
class DbCharactersBreed(
    @PrimaryKey(autoGenerate = true)
    var characterBreedId: Long? = null,
    var displayedBreedId: Long? = null,
    var characterId:Long? = null
) : DbEntity<DomainCharactersBreed> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainCharactersBreed {
        return DomainCharactersBreed(
            charactersBreedId = this.characterBreedId,
            displayedBreedId = this.displayedBreedId,
            characterId = this.characterId
        )
    }

    override fun toString(): String {
        return "DbCharactersBreed(characterBreedId=$characterBreedId, displayedBreedId=$displayedBreedId, characterId=$characterId)"
    }

    /**
     * Returns a string representation of the DbCharacterBreed
     */


    companion object :
        DbCompanion<DomainCharactersBreed, DbCharactersBreed> {

        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainCharactersBreed?): DbCharactersBreed {
            if (domainModel != null) {
                return DbCharactersBreed(
                    characterBreedId = domainModel.charactersBreedId,
                    displayedBreedId = domainModel.displayedBreedId,
                    characterId = domainModel.characterId
                )
            } else {
                throw Exception("Breed is null")
            }
        }
    }
}