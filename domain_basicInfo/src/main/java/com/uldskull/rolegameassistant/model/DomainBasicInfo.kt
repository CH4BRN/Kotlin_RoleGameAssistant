// DomainBasicInfo.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.model

/**
Class "DomainBasicInfo"

Domain model for basic info.
 */
data class DomainBasicInfo(
    val pk_basicInfoId:Long?=null,
    val characterName:String?="name",
    val characterExperience:Int?=0,
    val fk_characterId:Long?=null,
    val fk_levelId:Long?=null
)