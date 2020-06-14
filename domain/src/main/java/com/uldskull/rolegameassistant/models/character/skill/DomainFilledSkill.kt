// File DomainFilledSkill.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.skill

import com.uldskull.rolegameassistant.models.character.DomainSkill

/**
 *   Class "DomainFilledSkill" :
 *   TODO: Fill class use.
 **/
class DomainFilledSkill(
    filledSkillId: Long? = null,
    filledSkillName: String? = "",
    val filledSkillBase: Int? = 0,
    var filledSkillTensValue: Int? = 0,
    var filledSkillUnitsValue: Int? = 0,
    val filledSkillTotal: Int? = 0,
    val filledSkillMax: Int? = 0,
    var filledSkillCharacterId:Long?
) : DomainSkill(
    skillId = filledSkillId,
    skillName = filledSkillName
) {
    var skillIsSelected: Boolean = false
    override fun toString(): String {
        return "DomainFilledSkill(filledSkillBase=$filledSkillBase, filledSkillTensValue=$filledSkillTensValue, filledSkillUnitsValue=$filledSkillUnitsValue, filledSkillTotal=$filledSkillTotal, filledSkillMax=$filledSkillMax, filledSkillCharacterId=$filledSkillCharacterId, skillIsSelected=$skillIsSelected)"
    }

}