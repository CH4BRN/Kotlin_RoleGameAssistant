// CharactersBreed.kt created by UldSkull - 29/05/2020

package com.uldskull.rolegameassistant.models.character.breed.charactersBreed

/**
Class "CharactersBreed"

Save the selected displayed breeds
 */
class DomainCharactersBreed(
    var charactersBreedId: Long? = null,
    val displayedBreedId: Long?,
    var characterId:Long? = null
) {
    override fun toString(): String {
        return "DomainCharactersBreed(charactersBreedId=$charactersBreedId, displayedBreedId=$displayedBreedId, characterId=$characterId)"
    }
}