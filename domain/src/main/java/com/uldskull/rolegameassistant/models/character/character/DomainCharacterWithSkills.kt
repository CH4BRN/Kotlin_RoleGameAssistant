package com.uldskull.rolegameassistant.models.character.character

import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill

class DomainCharacterWithSkills(
    var character: DomainCharacter?,
    var skills: List<DomainSkillToFill>
) {
    override fun toString(): String {
        return "DomainCharacterWithSkills(character=${character?.characterName}".toUpperCase()+"skills=$skills)"
    }
}
