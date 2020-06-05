// DomainCharacterWithBreeds.kt created by UldSkull - 29/05/2020

package com.uldskull.rolegameassistant.models.character.character

import com.uldskull.rolegameassistant.models.character.breed.charactersBreed.DomainCharactersBreed

/**
Class "DomainCharacterWithBreeds"

TODO: Describe class utility.
 */
class DomainCharacterWithBreeds(
    val character: DomainCharacter,
    val breeds: List<DomainCharactersBreed>

) {
    override fun toString(): String {
        return "DomainCharacterWithBreeds(character=$character, breeds=$breeds)"
    }
}