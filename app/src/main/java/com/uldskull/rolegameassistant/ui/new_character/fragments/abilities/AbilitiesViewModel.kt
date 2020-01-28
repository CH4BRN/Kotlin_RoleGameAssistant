// File AbilitiesViewModel.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.fragments.abilities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.domain.abilities.Ability

/**
 *   Class "AbilitiesViewModel" :
 *   Designed to store and manage UI-related data in a lifecycle conscious way.
 *   The ViewModel class allows data to survive configuration changes such as
 *   screen rotations.
 **/
class AbilitiesViewModel(application: Application) : AndroidViewModel(application) {

    var abilities = MutableLiveData<List<Ability>>()

    init {
        abilities.value = listOf(
            Ability("aa", roll = 1, bonus = 1, total = 2),
            Ability("bb", roll = 3, bonus = 4, total = 7),
            Ability("cc", roll = 8, bonus = 9, total = 17),
            Ability("dd", roll = 6, bonus = 3, total = 9)
        )
    }


}