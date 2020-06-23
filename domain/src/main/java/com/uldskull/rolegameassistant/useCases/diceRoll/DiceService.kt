// File DiceService.kt
// @Author pierre.antoine - 19/03/2020 - No copyright.

package com.uldskull.rolegameassistant.useCases.diceRoll

/**
 *   Interface "DiceService" :
 *   Contracts for dice service
 **/
interface DiceService {

    /**
     * Get one dice roll specifying a number of faces.
     */
    fun getOneDiceRollWithANumberOfFace(numberOfFace: Int): Int?

    /**
     * Get multiple dice rolls specifying the number of faces.
     */
    fun getMultipleDiceRollWithANumberOfFace(numberOfFaces: ArrayList<Int>): ArrayList<Int>
}