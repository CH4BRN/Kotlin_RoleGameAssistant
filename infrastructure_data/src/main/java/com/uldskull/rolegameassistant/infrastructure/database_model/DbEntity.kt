// DbEntity.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database_model

/**
Interface "DbEntity"

Interface that ensure there is a mapping between domain entity and dbEntity.
U  - Database entity
T  - Domain entity.
 **/
interface DbEntity<DO> {
    /**
     * Converts a Database model entity into a domain model.
     */
    fun toDomain(): DO
}