// GetDamageBonusUseCase.kt created by UldSkull - 23/06/2020

package com.uldskull.rolegameassistant.useCases.damageBonus

import com.uldskull.rolegameassistant.useCases.UseCase

/**
Class "GetDamageBonusUseCase"

Get the damage bonus value
 */
class GetDamageBonusUseCase : UseCase<Int, DamageBonus> {
    override fun execute(request: Int): DamageBonus {
        return if (request != null) {
            DamageBonus.values()[request]
        } else {
            DamageBonus.None
        }
    }
}

