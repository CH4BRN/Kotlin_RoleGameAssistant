// File BondsViewModel.kt
// @Author pierre.antoine - 02/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.character.DomainBond

/**
 *   Class "BondsViewModel" :
 *   ViewModel class for bonds.
 **/
class BondsViewModel(application: Application) : AndroidViewModel(application) {
    fun addBond(): MutableList<DomainBond>? {
        Log.d("Bond", "add bond")
        val bond = DomainBond(
            bondId = null,
            bondTitle = this.currentBondTitle,
            bondValue = this.currentBondValue
        )
        Log.d("Bond", "Add bond $bond")
        try {
            val bondsValue = bonds.value
            bondsValue?.add(bond)

            bonds.value = bondsValue
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
        Log.d("Bond", "Bond $bond added")
        return bonds.value
    }

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

    fun bondValueIsInitialized(): Boolean = ::currentBondValue.isInitialized
    fun bondTitleIsInitialized(): Boolean = ::currentBondTitle.isInitialized
    /** bonds to display   **/
    var bonds = MutableLiveData<MutableList<DomainBond>>()

    init {
        bonds.value = mutableListOf()
    }
}