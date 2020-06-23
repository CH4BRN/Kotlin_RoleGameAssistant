// File DomainCharacterWithIdeals.kt
// @Author pierre.antoine - 06/04/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.character

import com.uldskull.rolegameassistant.models.character.DomainIdeal

/**
 *   Class "DomainCharacterWithIdeals" :
 *   Holds character with its ideals
 **/
class DomainCharacterWithIdeals(
    /**
     * Character
     */
    val character: DomainCharacter,
    /**
     * Ideals list
     */
    val ideals: List<DomainIdeal>
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainCharacterWithIdeals(character=$character, ideals=$ideals)"
    }

}