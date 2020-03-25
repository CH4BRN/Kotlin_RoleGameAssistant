// File AbilitiesViewModel.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.abilities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic

/**
 *   Class "AbilitiesViewModel" :
 *   Designed to store and manage UI-related data in a lifecycle conscious way.
 *   The ViewModel class allows data to survive configuration changes such as
 *   screen rotations.
 **/
class CharacteristicsViewModel(application: Application) : AndroidViewModel(application) {

    /** Abilities to display    **/
    var characteristicsToChoose = MutableLiveData<List<DomainCharacteristic>>()

    init {
        characteristicsToChoose.value = listOf(
            DomainCharacteristic(
                0,
                characteristicName = CharacteristicsName.STRENGTH.characteristicName
            ),
            DomainCharacteristic(
                0,
                CharacteristicsName.SIZE.characteristicName
            ),
            DomainCharacteristic(
                0,
                CharacteristicsName.POWER.characteristicName
            ),
            DomainCharacteristic(
                0,
                CharacteristicsName.INTELLIGENCE.characteristicName
            ),
            DomainCharacteristic(
                0,
                CharacteristicsName.DEXTERITY.characteristicName
            ),
            DomainCharacteristic(
                0,
                CharacteristicsName.CONSTITUTION.characteristicName
            ),
            DomainCharacteristic(
                0,
                CharacteristicsName.CHARISMA.characteristicName
            ),
            DomainCharacteristic(
                0,
                CharacteristicsName.APPEARANCE.characteristicName
            )
        )
    }
}