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
            Ability("Str : ", roll = 1, bonus = 5, total = 6),
            Ability("Dex : ", roll = 2, bonus = 5, total = 7),
            Ability("Con : ", roll = 3, bonus = 5, total = 8),
            Ability("Int : ", roll = 4, bonus = 5, total = 9),
            Ability("Wis : ", roll = 5, bonus = 5, total = 10),
            Ability("Cha : ", roll = 6, bonus = 5, total = 11)

        )
    }


}