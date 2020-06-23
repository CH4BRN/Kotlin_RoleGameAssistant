// GetBreedsHealthBonusUseCase.kt created by UldSkull - 23/06/2020

package com.uldskull.rolegameassistant.useCases.health

import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.useCases.UseCase

/**
Class "GetBreedsHealthBonusUseCase"

Gets breeds health bonus
 */
class GetBreedsHealthBonusUseCase:UseCase<List<DomainDisplayedBreed>, Int> {
    override fun execute(request: List<DomainDisplayedBreed>?): Int {
        var bonus = 0
        if(request != null && request?.isNotEmpty()){
            request.forEach{
                if(it.breedHealthBonus != null){
                    bonus += it.breedHealthBonus
                }
            }
        }
        return bonus
    }
}