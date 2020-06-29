// File DomainJob.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.occupation

/**
 *   Class "DomainJob" :
 *   Holds a domain occupation ( like a job)
 **/
class DomainOccupation(
    /**
     * Occupation identifier
     */
    var occupationId: Long?,
    /**
     * Occupation name
     */
    var occupationName: String?,
    /**
     * Occupation income
     */
    var occupationIncome:String?,
    /**
     * Occupation contacts
     */
    var occupationContacts:String?,
    /**
     * Occupation specials
     */
    var occupationSpecial:String?
)  {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainOccupation(occupationId=$occupationId, occupationName=$occupationName, occupationIncome=$occupationIncome, occupationContacts=$occupationContacts, occupationSpecial=$occupationSpecial)"
    }
}