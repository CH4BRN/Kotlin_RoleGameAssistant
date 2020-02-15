// DbEntity.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database_model

/**
Interface "DbEntity"

TODO : Describe interface utility.
 **/
interface DbEntity<T,U>{
    fun toDomain():T

    fun List<U>.asDomainModel():List<T>



}