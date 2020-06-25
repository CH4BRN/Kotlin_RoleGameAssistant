// BaseHalthUseCase.kt created by UldSkull - 23/06/2020

package com.uldskull.rolegameassistant.useCases.health

import com.uldskull.rolegameassistant.models.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.useCases.UseCase

/**
Class "BaseHealthUseCase"

Gets the base health value
 */
class GetBaseHealthUseCase :
    UseCase<List<DomainRollsCharacteristic?>, Int> {
    override fun execute(request: List<DomainRollsCharacteristic?>): Int {
        var hp = 0
        request?.forEach {
            if (it?.characteristicTotal != null) {
                hp += it.characteristicTotal!!
            }
        }
        return hp / 2
    }

}