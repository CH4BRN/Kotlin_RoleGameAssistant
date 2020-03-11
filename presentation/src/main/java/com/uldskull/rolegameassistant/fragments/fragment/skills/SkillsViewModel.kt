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
    var jobSkills = MutableLiveData<List<DomainSkill>>()
    var hobbiesSkills = MutableLiveData<List<DomainSkill>>()
    var hobbySkills = MutableLiveData<List<DomainSkill>>()

    init {

        hobbiesSkills.value = listOf(
            DomainSkill(
                skillName = "hobbiesSkills",
                skillBase = 15,
                skillTensValue = 8,
                skillUnitsValue = 0
            ),
            DomainSkill(
                skillName = "hobbiesSkills",
                skillBase = 35,
                skillTensValue = 9,
                skillUnitsValue = 0
            ),
            DomainSkill(
                skillName = "hohobbiesSkillshobbiesSkillsbby",
                skillBase = 15,
                skillTensValue = 8,
                skillUnitsValue = 5
            ),
            DomainSkill(
                skillName = "hobbiesSkills",
                skillBase = 15,
                skillTensValue = 8,
                skillUnitsValue = 9
            )
        )

        jobSkills.value = listOf(
            DomainSkill(
                skillName = "jobSkills",
                skillBase = 15,
                skillTensValue = 8,
                skillUnitsValue = 0
            ),
            DomainSkill(
                skillName = "jobSkillsjobSkills",
                skillBase = 35,
                skillTensValue = 9,
                skillUnitsValue = 0
            ),
            DomainSkill(
                skillName = "jobSkills",
                skillBase = 15,
                skillTensValue = 8,
                skillUnitsValue = 5
            ),
            DomainSkill(
                skillName = "jobSkillsjobSkills",
                skillBase = 15,
                skillTensValue = 8,
                skillUnitsValue = 9
            )
        )

        hobbySkills.value = listOf(
            DomainSkill(
                skillName = "jobSkills",
                skillBase = 15,
                skillTensValue = 8,
                skillUnitsValue = 0
            ),
            DomainSkill(
                skillName = "jobSkillsjobSkills",
                skillBase = 35,
                skillTensValue = 9,
                skillUnitsValue = 0
            ),
            DomainSkill(
                skillName = "jobSkills",
                skillBase = 15,
                skillTensValue = 8,
                skillUnitsValue = 5
            ),
            DomainSkill(
                skillName = "jobSkillsjobSkills",
                skillBase = 15,
                skillTensValue = 8,
                skillUnitsValue = 9
            )
        )
    }
}