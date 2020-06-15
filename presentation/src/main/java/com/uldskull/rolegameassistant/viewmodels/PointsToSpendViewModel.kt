// PointsToSpendViewModel.kt created by UldSkull - 19/05/2020

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
Class "PointsToSpendViewModel"

TODO: Describe class utility.
 */
class PointsToSpendViewModel(application: Application) : AndroidViewModel(application) {
    val observableOccupationPointsToSpend = MutableLiveData<Int>()
    val observableHobbyPointsToSpend = MutableLiveData<Int>()
    var observableOccupationSpentTensPointsArray = MutableLiveData<Array<Int?>>()
    var observableHobbySpentTensPointsArray = MutableLiveData<Array<Int?>>()
    var observableOccupationSpentUnitsPointsArray = MutableLiveData<ArrayList<Int>>()
    var observableHobbySpentUnitsPointsArray = MutableLiveData<Array<Int>>()
    var observableCurrentOccupationSkillPosition = MutableLiveData<Int>()
    var observableOccupationSpentPoints = MutableLiveData<Int>()
    var observableHobbySpentPoints = MutableLiveData<Int>()

}