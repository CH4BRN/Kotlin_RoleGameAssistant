// File DiceService.kt
// @Author pierre.antoine - 19/03/2020 - No copyright.

package com.uldskull.rolegameassistant.useCases.diceRoll

/**
 *   Interface "DiceService" :
 *   TODO: Fill interface use.
 **/
interface DiceService {

    fun getOneDiceRollWithANumberOfFace(numberOfFace: Int): Int?

    fun getMultipleDiceRollWithANumberOfFace(numberOfFaces: ArrayList<Int>): ArrayList<Int>
}