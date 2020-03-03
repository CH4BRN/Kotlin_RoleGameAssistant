// File BondsViewModel.kt
// @Author pierre.antoine - 02/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.bonds

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 *   Class "BondsViewModel" :
 *   ViewModel class for bonds.
 **/
class BondsViewModel(application: Application) : AndroidViewModel(application) {
    /** bonds to display   **/
    var bonds = MutableLiveData<List<String>>()

    init {
        bonds.value = listOf(
            "Bond 1",
            "Bond 2",
            "Bond 3",
            "Bond 4",
            "Bond 5"
        )
    }
}