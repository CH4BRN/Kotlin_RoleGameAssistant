// File BondsViewModel.kt
// @Author pierre.antoine - 02/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.DomainBond

/**
 *   Class "BondsViewModel" :
 *   ViewModel class for bonds.
 **/
class BondsViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Delete a bond from the bond list.
     */
    fun deleteBond(domainBond: DomainBond?): Boolean {
        Log.d("Bond", "delete bond  $domainBond")

        try {
            val bondsValue = bonds.value
            bondsValue?.remove(domainBond)

            bonds.value = bondsValue
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

        return true
    }


    /**
     * Current bond value
     */
    lateinit var currentBondValue: String

    /**
     * Current bond title
     */
    lateinit var currentBondTitle: String

    /**
     * Is bond value initialized ?
     */
    fun bondValueIsInitialized(): Boolean = ::currentBondValue.isInitialized

    /**
     * Is bond title initialized ?
     */
    fun bondTitleIsInitialized(): Boolean = ::currentBondTitle.isInitialized

    /** bonds to display   **/
    var bonds = MutableLiveData<MutableList<DomainBond?>>()

    init {
        bonds.value = mutableListOf()
    }
}