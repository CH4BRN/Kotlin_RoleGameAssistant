// File CharacterRepository.kt
// @Author pierre.antoine - 30/01/2020 - No copyright.

package com.uldskull.rolegameassistant.repository.character

import com.uldskull.rolegameassistant.models.character.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.character.DomainCharacterWithIdeals
import com.uldskull.rolegameassistant.models.character.character.DomainCharacterWithSkills
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
 *   Interface "CharacterRepository" :
 *   Contract to allow character persistence.
 **/
interface CharacterRepository<T> : GenericRepository<T, DomainCharacter> {
    /**
     * Find the corresponding character with all its ideals
     */
    fun findOneWithIdeals(id: Long?): DomainCharacterWithIdeals?

    /**
     * Find the corresponding character with all its skills.
     */
    fun findOneWithOccupationSkills(id:Long?) : DomainCharacterWithSkills?
}