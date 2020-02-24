// File CharacterRepository.kt
// @Author pierre.antoine - 30/01/2020 - No copyright.

package com.uldskull.rolegameassistant.contracts.repository.character

import com.uldskull.rolegameassistant.models.character.DomainCharacter
import GenericRepository

/**
 *   Interface "CharacterRepository" :
 *   Contract to allow character persistence.
 **/
interface CharacterRepository<T>: GenericRepository<T, DomainCharacter>