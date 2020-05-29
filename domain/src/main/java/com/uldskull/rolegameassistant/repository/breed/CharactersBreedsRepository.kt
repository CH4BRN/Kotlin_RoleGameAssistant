// CharactersBreedsRepository.kt created by UldSkull - 29/05/2020

package com.uldskull.rolegameassistant.repository.breed

import com.uldskull.rolegameassistant.models.character.breed.charactersBreed.DomainCharactersBreed
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
Interface "CharactersBreedsRepository"

TODO : Describe interface utility.
 **/
interface CharactersBreedsRepository<T> :
    GenericRepository<T, DomainCharactersBreed> {
}