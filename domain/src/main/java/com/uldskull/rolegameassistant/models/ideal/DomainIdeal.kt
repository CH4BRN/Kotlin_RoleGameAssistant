// File DomainIdeal.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.ideal

/**
 *   Class "DomainIdeal" :
 *   Domain model for ideal
 **/
data class DomainIdeal(
    val value: String,
    val goodPoints: Int,
    val evilPoints: Int
)