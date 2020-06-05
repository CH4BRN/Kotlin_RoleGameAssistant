// DbCharacterWithBreeds.kt created by UldSkull - 29/05/2020

package com.uldskull.rolegameassistant.infrastructure.database_model.db_character

import androidx.room.Embedded
import androidx.room.Relation
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_CHARACTER_ID
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.characterBreeds.DbCharactersBreed

/**
Class "DbCharacterWithBreeds"

TODO: Describe class utility.
 */
data class DbCharacterWithBreeds(
    @Embedded
    val parentCharacter: DbCharacter,
    @Relation(
        parentColumn = "$FIELD_CHARACTER_ID",
        entityColumn = "$FIELD_CHARACTER_ID"
    )
    val childrenBreeds: List<DbCharactersBreed>

) {
    override fun toString(): String {
        return "DbCharacterWithBreeds(parentCharacter=$parentCharacter, childrenBreeds=$childrenBreeds)"
    }
}