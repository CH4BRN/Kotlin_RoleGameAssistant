// File GetOneDiceRollUseCase.kt
// @Author pierre.antoine - 18/03/2020 - No copyright.

package com.uldskull.rolegameassistant.useCases.diceRoll

import com.uldskull.rolegameassistant.useCases.UseCase
import kotlin.random.Random

/**
 *   Class "GetOneDiceRollUseCase" :
 *   Use case to get one dice roll
 **/
class GetOneDiceRollUseCase : UseCase<Int, Int> {
    companion object {
        private const val TAG = "GetOneDiceRollUseCase"
    }

    /**
     * Execute method
     */
    override fun execute(request: Int): Int {
        when (request) {
            null -> return 0
            0 or 1 -> return 1
        }

        val facesPlusOne = request!! + 1
        val result = Random.nextInt(1, facesPlusOne)
        println(TAG + "result = $result")

        return result
    }
}