// GetKnowScoreUseCase.kt created by UldSkull - 23/06/2020

package com.uldskull.rolegameassistant.useCases.know

import com.uldskull.rolegameassistant.models.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.useCases.UseCase

/**
Class "GetKnowScoreUseCase"

Gets know score
 */
class GetKnowScoreUseCase : UseCase<DomainRollsCharacteristic, Int>{
    override fun execute(request: DomainRollsCharacteristic): Int {
        return if(request?.characteristicTotal != null){
            request.characteristicTotal!! *5
        }else{
            0
        }
    }

}