// File DomainOccupationSkill.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.skill

import com.uldskull.rolegameassistant.models.character.DomainSkill

/**
 *   Class "DomainOccupationSkill" :
 *   TODO: Fill class use.
 **/
class DomainSkillToCheck(
    skillId: Long? = null,
    skillName: String? = "",
    skillDescription: String? = "",
    var skillBase:Int? = 0,
    var skillMax:Int? = 150,
    var skillIsChecked: Boolean = false
) : DomainSkill(
    skillId = skillId,
    skillDescription = skillDescription,
    skillName = skillName
) {
    override fun toString(): String {
        return "DomainOccupationSkill(skillBase=$skillBase, skillMax=$skillMax, skillIsChecked=$skillIsChecked)"
    }
}