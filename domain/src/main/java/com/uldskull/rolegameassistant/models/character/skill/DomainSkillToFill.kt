// File DomainFilledSkill.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.skill

import com.uldskull.rolegameassistant.models.character.DomainSkill

/**
 *   Class "DomainFilledSkill" :
 *   TODO: Fill class use.
 **/
class DomainSkillToFill(
    filledSkillId: Long? = null,
    filledSkillName: String? = "",
    val filledSkillBase: Int? = 0,
    var filledSkillTensValue: Int? = 0,
    var filledSkillUnitsValue: Int? = 0,
    val filledSkillTotal: Int? = 0,
    val filledSkillMax: Int? = 0,
    var filledSkillCharacterId:Long?,
    var filledSkillType:Long?
) : DomainSkill(
    skillId = filledSkillId,
    skillName = filledSkillName
) {
    var skillIsSelected: Boolean = false
    override fun toString(): String {
        return "DomainSkillToFill(filledSkillName:${this.skillName}filledSkillBase=$filledSkillBase, filledSkillTensValue=$filledSkillTensValue, filledSkillUnitsValue=$filledSkillUnitsValue, filledSkillTotal=$filledSkillTotal, filledSkillMax=$filledSkillMax, filledSkillCharacterId=$filledSkillCharacterId, filledSkillType=$filledSkillType, skillIsSelected=$skillIsSelected)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DomainSkillToFill

        if (filledSkillBase != other.filledSkillBase) return false
        if (filledSkillMax != other.filledSkillMax) return false
        if (filledSkillCharacterId != other.filledSkillCharacterId) return false
        if (filledSkillType != other.filledSkillType) return false
        if (skillIsSelected != other.skillIsSelected) return false

        return true
    }

    override fun hashCode(): Int {
        var result = filledSkillCharacterId?.hashCode() ?: 0
        result = 31 * result + (filledSkillType?.hashCode() ?: 0)
        return result
    }

}