// File CharacterRepository.kt
// @Author pierre.antoine - 30/01/2020 - No copyright.

package com.uldskull.rolegameassistant.repository.character

import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.repository.Repository

/**
 *   Interface "CharacterRepository" :
 *   Contract to allow character persistence.
 **/
interface CharacterRepository<T>:Repository<T, DomainCharacter>