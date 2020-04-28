// File SkillsViewModel.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill

/**
 *   Class "SkillsViewModel" :
 *   Designed to store and manage UI-related data in a lifecycle conscious way.
 *   The ViewModel class allows data to survive configuration changes such as
 *   screen rotations.
 **/
class SkillsViewModel(application: Application) : AndroidViewModel(application) {
    companion object{
        private const val TAG = "SkillsViewModel"
    }

    var observedOccupationsSkills = MutableLiveData<List<DomainOccupationSkill?>>()
    set(value){
        Log.d(TAG, "new observedOccupationsSkills")
    }
    var hobbiesSkills = MutableLiveData<List<DomainOccupationSkill?>>()
    var hobbySkills = MutableLiveData<List<DomainOccupationSkill?>>()

}