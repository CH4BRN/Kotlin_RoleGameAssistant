// File AdapaterButtonListener.kt
// @Author pierre.antoine - 27/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.core.listeners


/**
 *   Interface "AdapterButtonListener" :
 *   Ensure communication between adapter and fragment.
 **/
interface CustomAdapterButtonListener<T> {
    /**
     * Called when a recyclerview cell is pressed
     */
    fun itemPressed(domainModel: T?, position:Int? = null)
}
