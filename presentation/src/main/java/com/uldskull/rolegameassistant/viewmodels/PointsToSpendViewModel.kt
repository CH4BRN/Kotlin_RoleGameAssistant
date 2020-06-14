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
class PointsToSpendViewModel(  application: Application): AndroidViewModel(application){
    val observablePointsToSpend = MutableLiveData<Int>()
    var observableSpentOccupationTensPointsArray= MutableLiveData<Array<Int?>>()
    var observableSpentOccupationUnitsPointsArray= MutableLiveData<ArrayList<Int>>()
    var observableCurrentOccupationSkillPosition = MutableLiveData<Int>()
    var observableSpentPoints = MutableLiveData<Int>()

}