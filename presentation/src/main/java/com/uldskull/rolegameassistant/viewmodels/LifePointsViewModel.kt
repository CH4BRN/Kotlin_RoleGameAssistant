// LifePointsViewModel.kt created by UldSkull - 29/06/2020

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
Class "LifePointsViewModel"

Holds and manage life points
 */
class LifePointsViewModel(
    application: Application
) : AndroidViewModel(application){
    companion object{
        private const val TAG = "LifePointsViewModel"
    }

    /**
     * Life points to modify and display
     */
    var lifePoints = MutableLiveData<Int?>()
}