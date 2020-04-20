// File GetOneDiceRollUseCase.kt
// @Author pierre.antoine - 18/03/2020 - No copyright.

package com.uldskull.rolegameassistant.useCases.diceRoll

import com.uldskull.rolegameassistant.useCases.UseCase
import kotlin.random.Random

/**
 *   Class "GetOneDiceRollUseCase" :
 *   TODO: Fill class use.
 **/
class GetOneDiceRollUseCase : UseCase<Int, Int> {
    override fun execute(faces: Int?): Int {
        when (faces) {
            null -> return 0
            0 or 1 -> return 1
        }
        return Random.nextInt(1, faces!!)
    }
}