// File DomainFilledSkill.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.skill

import com.uldskull.rolegameassistant.models.character.DomainSkill

/**
 *   Class "DomainFilledSkill" :
 *   Holds a skill that will be filled (once selected)
 **/
class DomainSkillToFill(
    /**
     * skill's identifier
     */
    filledSkillId: Long? = null,
    /**
     * skill's name
     */
    filledSkillName: String? = "",
    /**
     * skill's base score
     */
    val filledSkillBase: Int? = 0,
    /**
     * skill's tens value
     */
    var filledSkillTensValue: Int? = 0,
    /**
     * skill's units value
     */
    var filledSkillUnitsValue: Int? = 0,
    /**
     * skill's total score
     */
    val filledSkillTotal: Int? = 0,
    /**
     * skill's max score
     */
    val filledSkillMax: Int? = 0,
    /**
     * skill's corresponding character id
     */
    var filledSkillCharacterId: Long?,
    /**
     * skill's type
     * <ul>
     *     <li>
     *         0 -> occupation
     *     </li>
     *     <li>
     *         1 -> hobby
     *     </li>
     * </ul>
     *
     */
    var filledSkillType: Long?
) : DomainSkill(
    /**
     * Skill's identifier
     */
    skillId = filledSkillId,
    /**
     * Skill's name
     */
    skillName = filledSkillName
) {
    /**
     * Is the skill selected ?
     */
    var skillIsSelected: Boolean = false

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainSkillToFill(filledSkillName:${this.skillName}filledSkillBase=$filledSkillBase, filledSkillTensValue=$filledSkillTensValue, filledSkillUnitsValue=$filledSkillUnitsValue, filledSkillTotal=$filledSkillTotal, filledSkillMax=$filledSkillMax, filledSkillCharacterId=$filledSkillCharacterId, filledSkillType=$filledSkillType, skillIsSelected=$skillIsSelected)"
    }

    /**
     * Checks for equality between this Any object and the given Any object.
     */
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

    /**
     * Returns a hash code value for the object.
     */
    override fun hashCode(): Int {
        var result = filledSkillCharacterId?.hashCode() ?: 0
        result = 31 * result + (filledSkillType?.hashCode() ?: 0)
        return result
    }

}