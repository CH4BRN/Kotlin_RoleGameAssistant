// PointsToSpendViewModel.kt created by UldSkull - 19/05/2020

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
Class "PointsToSpendViewModel"

Holds observable points to spend and points to spend logic
 */
class PointsToSpendViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Observable occupation points to spend
     */
    val observableOccupationPointsToSpend = MutableLiveData<Int>()

    /**
     * Observable occupation spent points array
     */
    var observableOccupationSpentTensPointsArray = MutableLiveData<Array<Int?>>()

    /**
     * Observable current occupation skill position
     */
    var observableCurrentOccupationSkillPosition = MutableLiveData<Int>()

    /**
     * Observable occupation spent points
     */
    var observableOccupationSpentPoints = MutableLiveData<Int>()
    /**
     * Observable hobby spent points array
     */
    var observableHobbySpentPoints = MutableLiveData<Int>()

}