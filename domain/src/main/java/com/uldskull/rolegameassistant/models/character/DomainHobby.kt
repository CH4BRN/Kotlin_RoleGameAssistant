// File DomainHobby.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character

/**
 *   Class "DomainHobby" :
 *   Holds a character hobby
 **/
class DomainHobby(
    /**
     * Hobby's identifier
     */
    hobbyId: Long,
    /**
     * Hobby's name
     */
    hobbyName: String
) : DomainActivity(
    activityId = hobbyId,
    activityName = hobbyName
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainHobby()"
    }
}