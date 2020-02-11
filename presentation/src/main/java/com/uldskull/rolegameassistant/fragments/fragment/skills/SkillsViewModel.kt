// File SkillsViewModel.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.skills

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 *   Class "SkillsViewModel" :
 *   Designed to store and manage UI-related data in a lifecycle conscious way.
 *   The ViewModel class allows data to survive configuration changes such as
 *   screen rotations.
 **/
class SkillsViewModel(application: Application) : AndroidViewModel(application) {

    /** Skills to display   **/
    var skills = MutableLiveData<List<String>>()

    init {
        skills.value = listOf(
            "Skill 1",
            "SKill 2",
            "Skill 3",
            "Skill 4",
            "Skill 5"
        )
    }
}