// HobbiesViewModel.kt created by UldSkull - 16/06/2020

package com.uldskull.rolegameassistant.viewmodels.hobbies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToCheck

/**
Class "HobbiesViewModel"

TODO: Describe class utility.
 */
class HobbiesViewModel(
    application: Application

): AndroidViewModel(application){
    companion object{
        private const val TAG = "HobbiesViewModel"
    }
    val selectedCharacterSkills = MutableLiveData<List<Long?>>()
    var observedHobbiesSkills: MutableLiveData<List<DomainSkillToCheck>>? =
        MutableLiveData()
}