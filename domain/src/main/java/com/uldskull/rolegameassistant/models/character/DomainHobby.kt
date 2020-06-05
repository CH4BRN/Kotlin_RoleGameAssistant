// File DomainHobby.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character

/**
 *   Class "DomainHobby" :
 *   TODO: Fill class use.
 **/
class DomainHobby(
    hobbyId: Long,
    hobbyName: String
) : DomainActivity(
    activityId = hobbyId,
    activityName = hobbyName
) {
    override fun toString(): String {
        return "DomainHobby()"
    }
}