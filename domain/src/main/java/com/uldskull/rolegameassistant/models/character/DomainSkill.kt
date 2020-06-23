// File DomainSkill.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character

/**
 *   Class "DomainSkill" :
 *   Holds a character's skill
 **/
open class DomainSkill(
    /**
     * Skill's identifier
     */
    val skillId: Long?,
    /**
     * Skill's name
     */
    val skillName: String?,
    /**
     * Skill's description
     */
    val skillDescription: String? = ""
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainSkill(skillId=$skillId, skillName=$skillName, skillDescription=$skillDescription)"
    }
}

