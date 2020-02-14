// DomainBasicInfo.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.models.basic_info

/**
Class "DomainBasicInfo"

Domain model for basic info.
 */
data class DomainBasicInfo(
    val basicInfoId: Long? = null,
    val basicInfoCharacterName: String? = "name",
    val basicInfoCharacterExperience: Int? = 0,
    val basicInfoCharacterId: Long? = null,
    val basicInfoLevelId: Long? = null
)