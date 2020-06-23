// File DomainActivity.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models

/**
 *   Class "DomainActivity" :
 *   Holds an activity : occupation or hobby
 **/
abstract class DomainActivity(
    /**
     * Activity identifier
     */
    val activityId: Long?,
    /**
     * Activity name
     */
    val activityName: String?
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainActivity(activityId=$activityId, activityName=$activityName)"
    }
}