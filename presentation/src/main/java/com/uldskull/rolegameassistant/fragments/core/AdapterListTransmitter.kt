// AdapterListTransmitter.kt created by UldSkull - 27/05/2020

package com.uldskull.rolegameassistant.fragments.core

/**
Interface "AdapterListTransmitter"

Allows an adapter to transmit a list
 **/
interface AdapterListTransmitter<T> {
    /**
     * Transmit the list
     */
    fun transmitList(domainModels: List<T>)
}