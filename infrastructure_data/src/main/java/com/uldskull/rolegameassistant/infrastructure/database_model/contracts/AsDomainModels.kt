// AsDomainModel.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database_model.contracts

/**
Interface "AsDomainModel"

TODO : Describe interface utility.
 **/
interface AsDomainModels<T, U> {
    fun List<U>.asDomainModel():List<T>
}