// File DomainJob.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.occupation

import com.uldskull.rolegameassistant.models.character.DomainActivity

/**
 *   Class "DomainJob" :
 *   TODO: Fill class use.
 **/
class DomainOccupation(
    var occupationId: Long?,
    var occupationName: String?,
    var occupationIncome:String?,
    var occupationContacts:String?,
    var occupationSpecial:String?
) : DomainActivity(
    activityId = occupationId,
    activityName = occupationName
) {
    override fun toString(): String {
        return "\nOCCUPATION" +
                "\nName : $occupationName\n" +
                "id : $occupationId\n"
    }
}