// File AdapaterButtonListener.kt
// @Author pierre.antoine - 27/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import java.text.FieldPosition

/**
 *   Interface "AdapterButtonListener" :
 *   Ensure communication between adapter and fragment.
 **/
interface AdapterButtonListener<T> {
    /**
     * Called when a recyclerview cell is pressed
     */
    fun itemPressed(domainModel: T?, position:Int? = null)
}
