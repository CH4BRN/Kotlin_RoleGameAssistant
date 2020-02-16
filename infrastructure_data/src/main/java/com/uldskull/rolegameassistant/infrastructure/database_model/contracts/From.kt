// From.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database_model.contracts

/**
Interface "From"

TODO : Describe interface utility.
 **/
interface From<T,U> {
    fun from(t:T):U
}