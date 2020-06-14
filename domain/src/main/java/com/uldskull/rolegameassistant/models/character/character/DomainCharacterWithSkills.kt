package com.uldskull.rolegameassistant.models.character.character

import com.uldskull.rolegameassistant.models.character.skill.DomainFilledSkill

class DomainCharacterWithSkills(
    var character: DomainCharacter?,
    var skills: List<DomainFilledSkill>
) {
    override fun toString(): String {
        return "DomainCharacterWithSkills(character=${character?.characterName}".toUpperCase()+"skills=$skills)"
    }
}
