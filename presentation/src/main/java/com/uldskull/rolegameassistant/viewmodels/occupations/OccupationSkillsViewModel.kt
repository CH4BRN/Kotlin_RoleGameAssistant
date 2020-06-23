// File OccupationSkillsViewModel.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels.occupations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill

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

    /**
     * Lock object for threading
     */
    private val lock = Object()

    /**
     * Checked occupation skills
     */
    var checkedOccupationSkills = MutableLiveData<List<DomainSkillToFill>>()

    /**
     * Current selected occupation skill
     */
    var currentOccupationSkill = MutableLiveData<DomainSkillToFill?>()

    /**
     * occupation skills total points to spend
     */
    var occupationSkillsTotalPointsToSpend = MutableLiveData<Int>()


}