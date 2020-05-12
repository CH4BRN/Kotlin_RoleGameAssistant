// File OccupationSkillsViewModel.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels.occupations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.character.skill.DomainFilledSkill

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

    var occupationSkillsPointsToSpend = MutableLiveData<Int>()
    private val lock = java.lang.Object()

    var checkedOccupationSkills = MutableLiveData<List<DomainFilledSkill>>()
    var currentOccupationSkill = MutableLiveData<DomainFilledSkill>()

    var occupationSkillsTotalPointsToSpend = MutableLiveData<Int>()


}