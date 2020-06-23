// File OccupationViewModel.kt
// @Author pierre.antoine - 06/05/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels.occupations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 *   Class "OccupationViewModel" :
 *   ViewModel for occupation
 **/
class OccupationViewModel(
    application: Application
) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "OccupationViewModel"
    }
}