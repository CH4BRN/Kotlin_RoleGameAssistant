// HobbySkillsViewModel.kt created by UldSkull - 15/06/2020

package com.uldskull.rolegameassistant.viewmodels.hobbies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill

/**
Class "HobbySkillsViewModel"

Viewmodel for hobby
 */
class HobbySkillsViewModel(
    application: Application
): AndroidViewModel(application){
    companion object{
        private const val TAG = "HobbySkillsViewModel"
    }

    /**
     * Current hobby skill
     */
    var currentHobbySkill = MutableLiveData<DomainSkillToFill?>()

    /**
     * Total points to spend for hobby skills
     */
    var hobbySkillsTotalPointsToSpend = MutableLiveData<Int>()

    /**
     * Checked hobby skills
     */
    var checkedHobbySkills = MutableLiveData<List<DomainSkillToFill>>()
}