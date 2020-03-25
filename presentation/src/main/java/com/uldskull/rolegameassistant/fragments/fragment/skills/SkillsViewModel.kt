// File SkillsViewModel.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.skills

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.character.DomainFilledSkill

/**
 *   Class "SkillsViewModel" :
 *   Designed to store and manage UI-related data in a lifecycle conscious way.
 *   The ViewModel class allows data to survive configuration changes such as
 *   screen rotations.
 **/
class SkillsViewModel(application: Application) : AndroidViewModel(application) {

    /** Skills to display   **/
    var jobSkills = MutableLiveData<List<DomainFilledSkill>>()
    var hobbiesSkills = MutableLiveData<List<DomainFilledSkill>>()
    var hobbySkills = MutableLiveData<List<DomainFilledSkill>>()

    init {

        hobbiesSkills.value = listOf(
            DomainFilledSkill(
                filledSkillName = "SKILL",
                filledSkillMax = 24,
                filledSkillDescription = "Its a skill.",
                filledSkillBase = 0,
                filledSkillTensValue = 0,
                filledSkillUnitsValue = 0,
                filledSkillId = 0,
                filledSkillTotal = 0
            )
        )


    }
}