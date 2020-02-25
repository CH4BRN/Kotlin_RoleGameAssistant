// File CharacterRepository.kt
// @Author pierre.antoine - 30/01/2020 - No copyright.

package com.uldskull.rolegameassistant.contracts.repository.character

import com.uldskull.rolegameassistant.models.character.DomainCharacter
<<<<<<< HEAD
import GenericRepository
=======
import com.uldskull.rolegameassistant.repository.GenericRepository
>>>>>>> e7ef618d5525040e5ea05d137966ac1ecc64df65

/**
 *   Interface "CharacterRepository" :
 *   Contract to allow character persistence.
 **/
<<<<<<< HEAD
interface CharacterRepository<T>: GenericRepository<T, DomainCharacter>
=======
interface CharacterRepository<T> :
    GenericRepository<T, DomainCharacter>
>>>>>>> e7ef618d5525040e5ea05d137966ac1ecc64df65
