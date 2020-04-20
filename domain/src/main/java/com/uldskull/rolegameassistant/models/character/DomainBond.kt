// File DomainBond.kt
// @Author pierre.antoine - 02/04/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character

/**
 *   Class "DomainBond" :
 *   TODO: Fill class use.
 **/
class DomainBond(
    val bondId: Long?,
    val bondTitle: String?,
    val bondValue: String?
) {
    override fun toString(): String = "Id : $bondId\nTitle : $bondTitle\nValue : $bondValue"

}