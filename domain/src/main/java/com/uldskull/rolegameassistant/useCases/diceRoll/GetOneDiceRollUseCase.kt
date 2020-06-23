// File GetOneDiceRollUseCase.kt
// @Author pierre.antoine - 18/03/2020 - No copyright.

package com.uldskull.rolegameassistant.useCases.diceRoll

import com.uldskull.rolegameassistant.useCases.UseCase
import sun.rmi.runtime.Log
import kotlin.random.Random

/**
 *   Class "GetOneDiceRollUseCase" :
 *   Use case to get one dice roll
 **/
class GetOneDiceRollUseCase : UseCase<Int, Int> {
    companion object{
        private const val TAG ="GetOneDiceRollUseCase"
    }

    /**
     * Execute method
     */
    override fun execute(faces: Int?): Int {

        when (faces) {
            null -> return 0
            0 or 1 -> return 1
        }

        var facesPlusOne = faces!!+1
        var result = Random.nextInt(1, facesPlusOne)
        println(TAG + "result = $result")

        return result
    }
}