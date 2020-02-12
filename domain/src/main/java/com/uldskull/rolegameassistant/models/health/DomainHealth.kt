// DomainHealth.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.models.health

/**
    Class "DomainHealth"

    Domain model for health
 */
class DomainHealth(
    val healthId:Long?=null,
    val hpMod:Int?=0,
    val useMax:Boolean?=false,
    val maxHealth:Int?=0
)