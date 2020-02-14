// DomainLevel.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.models.basic_info

/**
Class "DomainLevel"

Domain model for level.
 */
data class DomainLevel(
    val levelId: Long? = null,
    val levelValue: Int? = 0,
    val levelHpModifier: Int? = 0
)