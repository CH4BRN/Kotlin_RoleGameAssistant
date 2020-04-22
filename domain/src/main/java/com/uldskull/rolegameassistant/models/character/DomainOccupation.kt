// File DomainJob.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character

/**
 *   Class "DomainJob" :
 *   TODO: Fill class use.
 **/
class DomainOccupation(
    occupationId: Long?,
    occupationName: String?,
    occupationDescription: String?) : DomainActivity(
    activityId = occupationId,
    activityName = occupationName,
    activityDescription = occupationDescription
)