// File OccupationViewModel.kt
// @Author pierre.antoine - 06/05/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 *   Class "OccupationViewModel" :
 *   TODO: Fill class use.
 **/
class OccupationViewModel(
    application:Application
) : AndroidViewModel(application) {

    companion object{
        private const val TAG = "OccupationViewModel"
    }

    var tensValue=MutableLiveData<Int>()
    var tensFixedValue:Int = 0
    var unitsValue=MutableLiveData<Int>()
    var unitsFixedValue:Int = 0
}