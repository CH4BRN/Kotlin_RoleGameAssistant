// GetEnergyPointsUseCase.kt created by UldSkull - 23/06/2020

package com.uldskull.rolegameassistant.useCases.energyPoints

import com.uldskull.rolegameassistant.models.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.useCases.UseCase

/**
Class "GetEnergyPointsUseCase"

Gets energy points use case
 */
class GetEnergyPointsUseCase : UseCase<DomainRollsCharacteristic, Int>{
    override fun execute(request: DomainRollsCharacteristic?): Int {
        return if(request?.characteristicTotal != null){
            request.characteristicTotal!!
        }else{
            0
        }
    }

}