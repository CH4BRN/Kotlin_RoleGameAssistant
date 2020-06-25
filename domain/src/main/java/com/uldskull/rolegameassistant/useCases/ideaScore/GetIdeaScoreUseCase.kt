// getIdeaScoreUseCase.kt created by UldSkull - 23/06/2020

package com.uldskull.rolegameassistant.useCases.ideaScore

import com.uldskull.rolegameassistant.models.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.useCases.UseCase

/**
Class "getIdeaScoreUseCase"

Gets idea score
 */
class GetIdeaScoreUseCase : UseCase<DomainRollsCharacteristic, Int>{
    override fun execute(request: DomainRollsCharacteristic): Int {
        return if(request?.characteristicTotal != null){
            request!!.characteristicTotal!!*5
        }else {
            0
        }
    }
}