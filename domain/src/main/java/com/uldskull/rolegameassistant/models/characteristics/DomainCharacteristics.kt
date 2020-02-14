// DomainCharacteristics.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.models.characteristics

/**
Class "DomainCharacteristics"

Domain model for characteristics.
 */
data class DomainCharacteristics(
    val characteristicsId: Long? = null,
    val characteristicsHeight: Int? = 0,
    val characteristicsWeight: Int? = 0,
    val characteristicsAge: Int? = 0,
    val characteristicsCharacterId: Long? = null,
    val characteristicsGenderId: Long? = null
)