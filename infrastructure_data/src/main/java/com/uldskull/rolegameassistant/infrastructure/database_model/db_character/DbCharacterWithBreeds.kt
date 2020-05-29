// DbCharacterWithBreeds.kt created by UldSkull - 29/05/2020

package com.uldskull.rolegameassistant.infrastructure.database_model.db_character

import androidx.room.Embedded
import androidx.room.Relation
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.characterBreeds.DbCharactersBreed

/**
Class "DbCharacterWithBreeds"

TODO: Describe class utility.
 */
data class DbCharacterWithBreeds(
    @Embedded
    val parentCharacter: DbCharacter,
    @Relation(
        parentColumn = "characterId",
        entityColumn = "characterId"
    )
    val childrenBreeds: List<DbCharactersBreed>

)