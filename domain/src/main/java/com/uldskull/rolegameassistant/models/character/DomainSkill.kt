// File DomainSkill.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character

/**
 *   Class "DomainSkill" :
 *   TODO: Fill class use.
 **/
open class DomainSkill(
    val skillId: Long?,
    val skillName: String?,
    val skillDescription: String? = ""
) {
    override fun toString(): String {
        return "DomainSkill(skillId=$skillId, skillName=$skillName, skillDescription=$skillDescription)"
    }
}

