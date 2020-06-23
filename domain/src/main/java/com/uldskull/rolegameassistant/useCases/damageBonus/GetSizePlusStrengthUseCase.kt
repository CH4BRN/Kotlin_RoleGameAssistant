// GetDamageBonusUseCase.kt created by UldSkull - 23/06/2020

package com.uldskull.rolegameassistant.useCases.damageBonus

import com.uldskull.rolegameassistant.models.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.useCases.UseCase

/**
Class "GetDamageBonusUseCase"

Get damage bonus score
 */
class GetSizePlusStrengthUseCase : UseCase<List<DomainRollsCharacteristic>, Int> {
    override fun execute(request: List<DomainRollsCharacteristic>?): Int {
        if (request != null && request.isNotEmpty()) {
            var score = 0
            request?.forEach {
                if (it != null) {
                    score += it.characteristicTotal!!
                }
            }
            return score
        }
        return 0
    }
}