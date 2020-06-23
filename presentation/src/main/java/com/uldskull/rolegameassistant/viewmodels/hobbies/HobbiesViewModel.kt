// HobbiesViewModel.kt created by UldSkull - 16/06/2020

package com.uldskull.rolegameassistant.viewmodels.hobbies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.skill.DomainSkillToCheck

/**
Class "HobbiesViewModel"

View model for hobbies
 */
class HobbiesViewModel(
    application: Application

): AndroidViewModel(application){
    companion object{
        private const val TAG = "HobbiesViewModel"
    }

    /**
     * Mutable selected character skills
     */
    val selectedCharacterSkills = MutableLiveData<List<Long?>>()

    /**
     * Observable mutable hobbies skills.
     */
    var observedHobbiesSkills: MutableLiveData<List<DomainSkillToCheck>>? =
        MutableLiveData()
}