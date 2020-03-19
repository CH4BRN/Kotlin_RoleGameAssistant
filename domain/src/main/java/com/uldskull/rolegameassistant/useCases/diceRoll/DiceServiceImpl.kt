// File DiceServiceImpl.kt
// @Author pierre.antoine - 19/03/2020 - No copyright.

package com.uldskull.rolegameassistant.useCases.diceRoll

/**
 *   Class "DiceServiceImpl" :
 *   TODO: Implements coroutines
 **/
class DiceServiceImpl(
    private val getOneDiceRollUseCase: GetOneDiceRollUseCase
) : DiceService {
    override fun getOneDiceRollWithANumberOfFace(numberOfFace: Int): Int {
        return getOneDiceRollUseCase.execute(numberOfFace)
    }

    override fun getMultipleDiceRollWithANumberOfFace(numberOfFaces: ArrayList<Int>): ArrayList<Int> {
        var list: ArrayList<Int>? = null
        for (i in 0 until numberOfFaces.size) {
            if (list == null) {
                list = ArrayList()
            }
            list.add(getOneDiceRollWithANumberOfFace(numberOfFaces[i]))
        }
        return list!!

    }

}


