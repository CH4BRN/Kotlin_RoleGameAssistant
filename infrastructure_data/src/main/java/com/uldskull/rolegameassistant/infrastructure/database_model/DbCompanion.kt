// DbCompanion.kt created by UldSkull - 15/06/2020

package com.uldskull.rolegameassistant.infrastructure.database_model

/**
Interface "DbCompanion"

Ensure that companion object have "from" method.
 **/
interface DbCompanion<DO, DA> {
    /**
     * Converts a domain model into a database model entity.
     */
    fun from(domainModel: DO?): DA
}