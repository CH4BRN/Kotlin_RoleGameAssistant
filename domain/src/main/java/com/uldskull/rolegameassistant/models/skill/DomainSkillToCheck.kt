// File DomainOccupationSkill.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.models.skill

import com.uldskull.rolegameassistant.models.DomainSkill

/**
 *   Class "DomainSkillToCheck" :
 *   Holds a skill that will be checked or not
 **/
class DomainSkillToCheck(
    /**
     * Skill's identifier
     */
    skillId: Long? = null,
    /**
     * Skill's name
     */
    skillName: String? = "",
    /**
     * Skill's description
     */
    skillDescription: String? = "",
    /**
     * Skill's base score
     */
    var skillBase: Int?,
    /**
     * Skill's max score
     */
    var skillMax: Int?,
    /**
     * Is the skill checked ?
     */
    var skillIsChecked: Boolean = false,
    /**
     * Is the skill a user skill
     */
    var skillIsUser: Boolean = false
) : DomainSkill(
    skillId = skillId,
    skillDescription = skillDescription,
    skillName = skillName
) {
    override fun toString(): String {
        return "DomainSkillToCheck(skillBase=$skillBase, skillMax=$skillMax, skillIsChecked=$skillIsChecked, skillIsUser=$skillIsUser)"
    }
}