// DomainLevel.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.model

/**
Class "DomainLevel"

Domain model for level.
 */
data class DomainLevel(
    val pkLevelId: Long? = null,
    val value: Int? = 0,
    val hpModifier: Int? = 0
)