// File DomainFilledSkill.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.skill

import com.uldskull.rolegameassistant.models.character.DomainSkill

/**
 *   Class "DomainFilledSkill" :
 *   TODO: Fill class use.
 **/
class DomainFilledSkill(
    filledSkillId: Long?,
    filledSkillName: String?,
    filledSkillDescription: String?,
    val filledSkillBase: Int?,
    val filledSkillTensValue: Int? = 0,
    val filledSkillUnitsValue: Int? = 0,
    val filledSkillTotal: Int?,
    val filledSkillMax: Int?
) : DomainSkill(
    skillId = filledSkillId,
    skillName = filledSkillName,
    skillDescription = filledSkillDescription
)