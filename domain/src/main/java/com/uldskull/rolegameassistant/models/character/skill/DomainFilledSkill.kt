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
    val filledSkillMax: Int? = 0
) : DomainSkill(
    skillId = filledSkillId,
    skillName = filledSkillName
) {
    var skillIsSelected: Boolean = false

    override fun toString(): String {
        return "FILLED SKILL : \n" +
                "\t id : $skillId\n" +
                "\t name : $skillName\n" +
                "\t skillIsSelected : $skillIsSelected\n" +
                "\t filledSkillTensValue : $filledSkillTensValue\n" +
                "\t filledSkillUnitsValue : $filledSkillUnitsValue"
    }
}