// File DomainBond.kt
// @Author pierre.antoine - 02/04/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character

/**
 *   Class "DomainBond" :
 *   Holds a bond for the character
 **/
class DomainBond(
    /**
     * Bond's identifier
     */
    val bondId: Long?,
    /**
     * Bond's title
     */
    val bondTitle: String?,
    /**
     * Bond's value
     */
    val bondValue: String?
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainBond(bondId=$bondId, bondTitle=$bondTitle, bondValue=$bondValue)"
    }

}