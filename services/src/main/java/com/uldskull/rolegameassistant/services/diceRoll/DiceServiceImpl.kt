// File DiceServiceImpl.kt
// @Author pierre.antoine - 19/03/2020 - No copyright.

package com.uldskull.rolegameassistant.services.diceRoll

import com.uldskull.rolegameassistant.services.DiceService
import com.uldskull.rolegameassistant.useCases.diceRoll.GetOneDiceRollUseCase

/**
 *   Class "DiceServiceImpl" :
 *   Holds the dices service implementation
 **/
class DiceServiceImpl(
    /**
     * use case for "getOneDice"
     */
    private val getOneDiceRollUseCase: GetOneDiceRollUseCase
) : DiceService {

    companion object {
        private const val TAG = "DiceServiceImpl"
    }

    /**
     * Get one dice roll with a number of faces
     */
    override fun getOneDiceRollWithANumberOfFace(numberOfFace: Int): Int {
        return getOneDiceRollUseCase.execute(numberOfFace)
    }

    /**
     * Get multiple dice rolls specifying the number of faces.
     */
    override fun getMultipleDiceRollWithANumberOfFace(numberOfFaces: ArrayList<Int>): ArrayList<Int> {
        var list: ArrayList<Int>? = null

        for (i in 0 until when (numberOfFaces.size) {
            0 or 1 -> 2
            else -> numberOfFaces.size
        }){
            if (list == null) {
                list = ArrayList()
            }

            val result = getOneDiceRollUseCase.execute(numberOfFaces[i])

            list.add(result)
        }
        return list!!
    }
}