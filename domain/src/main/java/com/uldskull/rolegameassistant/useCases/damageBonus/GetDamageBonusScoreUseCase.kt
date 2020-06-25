// GetDamageBonusUseCase.kt created by UldSkull - 23/06/2020

package com.uldskull.rolegameassistant.useCases.damageBonus

import com.uldskull.rolegameassistant.useCases.UseCase

/**
Class "GetDamageBonusUseCase"

Get damage bonus use case
 */
class GetDamageBonusScoreUseCase : UseCase<Int, Int> {

    override fun execute(request: Int): Int {
        when (request) {
            in 2..12 -> return 0
            in 13..16 -> return 1
            in 17..24 -> return 2
            in 25..32 -> return 3
            in 33..40 -> return 4
            in 41..56 -> return 5
            in 57..72 -> return 6
            in 73..88 -> return 7
            in 89..104 -> return 8
            in 105..120 -> return 9
            in 121..136 -> return 10
            in 137..152 -> return 11
            in 153..168 -> return 12
            in 169..184 -> return 13
            else -> return 2
        }
    }

}