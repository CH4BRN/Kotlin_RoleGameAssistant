// File DomainIdeal.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models

/**
 *   Class "DomainIdeal" :
 *   Hold's a character ideal
 **/
class DomainIdeal(
    /**
     * Ideal's identifier
     */
    val idealId: Long?,
    /**
     * Ideal's name
     */
    val idealName: String?,
    /**
     * Ideal's good points
     */
    val idealGoodPoints: Int?,
    /**
     * Ideal's evil points
     */
    val idealEvilPoints: Int?,
    /**
     * Is ideal checked ?
     */
    var isChecked: Boolean? = false
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainIdeal(idealId=$idealId, idealName=$idealName, idealGoodPoints=$idealGoodPoints, idealEvilPoints=$idealEvilPoints, isChecked=$isChecked)"
    }
}
