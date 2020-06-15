// File OccupationSkillsViewModel.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels.occupations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill

/**
 *   Class "OccupationSkillsViewModel" :
 *   Occupation skills view m
 **/
class OccupationSkillsViewModel(
    application: Application
) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "OccupationSkillsViewModel"
    }

    private val lock = java.lang.Object()

    var checkedOccupationSkills = MutableLiveData<List<DomainSkillToFill>>()
    var currentOccupationSkill = MutableLiveData<DomainSkillToFill?>()

    var occupationSkillsTotalPointsToSpend = MutableLiveData<Int>()


}