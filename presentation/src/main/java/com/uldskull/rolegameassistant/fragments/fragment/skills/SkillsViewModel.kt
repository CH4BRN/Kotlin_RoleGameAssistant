// File SkillsViewModel.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.skills

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.skill.DomainSkill

/**
 *   Class "SkillsViewModel" :
 *   Designed to store and manage UI-related data in a lifecycle conscious way.
 *   The ViewModel class allows data to survive configuration changes such as
 *   screen rotations.
 **/
class SkillsViewModel(application: Application) : AndroidViewModel(application) {

    /** Skills to display   **/
    var skills = MutableLiveData<List<DomainSkill>>()

    init {
        skills.value = listOf(
            DomainSkill(
                skillName = "Sample",
                skillBase = 15,
                skillValue = 80
            ),
            DomainSkill(
                skillName = "SampleSample",
                skillBase = 35,
                skillValue = 99
            ),
            DomainSkill(
                skillName = "SampleSampleSample",
                skillBase = 15,
                skillValue = 80
            ),
            DomainSkill(
                skillName = "SampleSampleSampleSample",
                skillBase = 15,
                skillValue = 80
            )
        )
    }
}