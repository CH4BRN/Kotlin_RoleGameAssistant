// DomainSkill.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.models.skill


val SKILL_MAX_VALUE: Int = 99
/**
Class "DomainSkill"

Domain model for skill.
 */
data class DomainSkill(
    val skillBase: Int? = 15,
    val skillId: Long? = null,
    val skillName: String? = "",
    val skillValue: Int? = 0
)