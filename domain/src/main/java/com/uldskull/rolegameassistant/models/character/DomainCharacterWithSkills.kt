package com.uldskull.rolegameassistant.models.character

import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill
import java.util.*

/**
 * Holds character with its skills
 */
class DomainCharacterWithSkills(
    /**
     * The character
     */
    var character: DomainCharacter?,
    /**
     * The skills list
     */
    var skills: List<DomainSkillToFill>
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainCharacterWithSkills(character=${character?.characterName}".toUpperCase(Locale.ENGLISH)+"skills=$skills)"
    }
}
