// File DomainHobby.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character

/**
 *   Class "DomainHobby" :
 *   TODO: Fill class use.
 **/
class DomainHobby(
    hobbyId: Long,
    hobbyName: String,
    hobbyDescription: String,
    hobbyTotalPoints: Int
) : DomainActivity(
    activityId = hobbyId,
    activityName = hobbyName,
    activitydescription = hobbyDescription,
    activityTotalPoints = hobbyTotalPoints
)