// DomainBasicInfo.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.model

/**
Class "DomainBasicInfo"

Domain model for basic info.
 */
data class DomainBasicInfo(
    val pkBasicInfoId:Long?=null,
    val characterName:String?="name",
    val characterExperience:Int?=0,
    val fkCharacterId:Long?=null,
    val fkLevelId:Long?=null
)