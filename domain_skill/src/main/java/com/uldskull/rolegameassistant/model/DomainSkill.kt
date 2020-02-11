// DomainSkill.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.model

/**
Class "DomainSkill"

Domain model for skill.
 */
data class DomainSkill(
    val skillId:Long?=null,
    val skillName:String?="",
    val skillAbility:String,
    val skillModifier:Int
)