// File DomainJob.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character

/**
 *   Class "DomainJob" :
 *   TODO: Fill class use.
 **/
class DomainJob(
    jobId: Long,
    jobName: String,
    jobDescription: String,
    jobTotalPoints: Int
) : DomainActivity(
    activityId = jobId,
    activityName = jobName,
    activitydescription = jobDescription,
    activityTotalPoints = jobTotalPoints
)