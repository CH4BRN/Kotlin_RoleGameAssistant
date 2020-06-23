// GetTotalHealthUseCase.kt created by UldSkull - 23/06/2020

package com.uldskull.rolegameassistant.useCases.health

import com.uldskull.rolegameassistant.useCases.UseCase

/**
Class "GetTotalHealthUseCase"

Gets total health
 */
class GetTotalHealthUseCase : UseCase<List<Int?>, Int> {
    override fun execute(request: List<Int?>?): Int {
        var score = 0
        if (request != null && request.isNotEmpty()) {
            request.forEach {
                if (it != null) {
                    score += it
                }
            }
        }
        return score
    }

}