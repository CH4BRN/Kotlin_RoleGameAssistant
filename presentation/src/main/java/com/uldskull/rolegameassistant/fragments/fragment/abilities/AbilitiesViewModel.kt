// File AbilitiesViewModel.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.abilities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.domain.model.abilities.DomainAbility

/**
 *   Class "AbilitiesViewModel" :
 *   Designed to store and manage UI-related data in a lifecycle conscious way.
 *   The ViewModel class allows data to survive configuration changes such as
 *   screen rotations.
 **/
class AbilitiesViewModel(application: Application) : AndroidViewModel(application) {

    /** Abilities to display    **/
    var abilities = MutableLiveData<List<DomainAbility>>()

    init {
        abilities.value = listOf(
            DomainAbility("Str : ", roll = 1, bonus = 5, total = 6),
            DomainAbility("Dex : ", roll = 2, bonus = 5, total = 7),
            DomainAbility("Con : ", roll = 3, bonus = 5, total = 8),
            DomainAbility("Int : ", roll = 4, bonus = 5, total = 9),
            DomainAbility("Wis : ", roll = 5, bonus = 5, total = 10),
            DomainAbility("Cha : ", roll = 6, bonus = 5, total = 11)

        )
    }


}