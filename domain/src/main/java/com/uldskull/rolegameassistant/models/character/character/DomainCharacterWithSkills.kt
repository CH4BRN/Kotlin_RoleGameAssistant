package com.uldskull.rolegameassistant.models.character.character

import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill

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
        return "DomainCharacterWithSkills(character=${character?.characterName}".toUpperCase()+"skills=$skills)"
    }
}
