// AlignmentScoreUseCase.kt created by UldSkull - 23/06/2020

package com.uldskull.rolegameassistant.useCases.alignment

import com.uldskull.rolegameassistant.models.DomainIdeal
import com.uldskull.rolegameassistant.useCases.UseCase

/**
Class "AlignmentScoreUseCase"

Gets the alignment score.
 */
class GetAlignmentScoreUseCase:
    UseCase<List<DomainIdeal?>, Int>{
    override fun execute(request: List<DomainIdeal?>?): Int {
        var score = 0
        request?.forEach {
            if (it != null) {
                if (it.idealGoodPoints != null) {
                    val goodPoints = it.idealGoodPoints
                    if (goodPoints != null) {
                        score += goodPoints
                    }
                }
                if (it.idealEvilPoints != null) {
                    val evilPoints = it.idealEvilPoints
                    if (evilPoints != null) {
                        score -= evilPoints
                    }
                }
            }
        }
        return score
    }

}