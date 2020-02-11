// DomainCharacteristics.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.model

/**
    Class "DomainCharacteristics"

    Domain model for characteristics.
 */
data class DomainCharacteristics(
    val pkCharacteristicsId:Long?=null,
    val height:Int?=0,
    val weight:Int?=0,
    val age:Int?=0,
    val fkCharacterId:Long?=null,
    val fkGenderId:Long?=null
)