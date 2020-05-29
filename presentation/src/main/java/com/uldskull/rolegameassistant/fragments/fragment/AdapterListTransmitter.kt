// AdapterListTransmitter.kt created by UldSkull - 27/05/2020

package com.uldskull.rolegameassistant.fragments.fragment

/**
Interface "AdapterListTransmitter"

TODO : Describe interface utility.
 **/
interface AdapterListTransmitter<T> {
    fun transmitList(domainModels: List<T>?)
}