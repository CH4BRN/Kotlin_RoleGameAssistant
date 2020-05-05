// File OccupationSkillsViewModel.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill
import com.uldskull.rolegameassistant.repository.skill.OccupationSkillRepository

/**
 *   Class "OccupationSkillsViewModel" :
 *   TODO: Fill class use.
 **/
class OccupationSkillsViewModel(
    application: Application
) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "OccupationSkillsViewModel"
    }

    private val lock = java.lang.Object()

    var occupationSkills = MutableLiveData<List<DomainOccupationSkill>>()

}