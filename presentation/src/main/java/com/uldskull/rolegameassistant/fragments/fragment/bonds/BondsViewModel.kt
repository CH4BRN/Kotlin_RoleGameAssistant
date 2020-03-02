// File BondsViewModel.kt
// @Author pierre.antoine - 02/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.bonds

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 *   Class "BondsViewModel" :
 *   TODO: Fill class use.
 **/
class BondsViewModel(application: Application) : AndroidViewModel(application) {
    /** Skills to display   **/
    var bonds = MutableLiveData<List<String>>()

    init {
        bonds.value = listOf(
            "Brave",
            "Bonds",
            "Bonds",
            "Bonds",
            "Bonds"
        )
    }
// TODO : Fill class.
}