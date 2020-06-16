// HobbySkillsViewModel.kt created by UldSkull - 15/06/2020

package com.uldskull.rolegameassistant.viewmodels.hobbies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill

/**
Class "HobbySkillsViewModel"

TODO: Describe class utility.
 */
class HobbySkillsViewModel(
    application: Application
): AndroidViewModel(application){
    companion object{
        private const val TAG = "HobbySkillsViewModel"
    }

    var currentHobbySkill = MutableLiveData<DomainSkillToFill?>()
    var hobbySkillsTotalPointsToSpend = MutableLiveData<Int>()
    var checkedHobbySkills = MutableLiveData<List<DomainSkillToFill>>()
}