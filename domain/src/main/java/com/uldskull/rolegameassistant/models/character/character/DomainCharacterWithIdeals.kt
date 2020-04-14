// File DomainCharacterWithIdeals.kt
// @Author pierre.antoine - 06/04/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.character

import com.uldskull.rolegameassistant.models.character.DomainIdeal

/**
 *   Class "DomainCharacterWithIdeals" :
 *   TODO: Fill class use.
 **/
class DomainCharacterWithIdeals(
    val character: DomainCharacter,
    val ideals: List<DomainIdeal>
) {
    override fun toString(): String =
        "character : ${character.characterName}\n ${ideals.size} ideals"

}