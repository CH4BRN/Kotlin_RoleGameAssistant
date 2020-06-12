// occupationSkillCalculatorImpl.kt created by UldSkull - 22/05/2020

package com.uldskull.rolegameassistant.useCases.occupation

import com.uldskull.rolegameassistant.models.character.skill.DomainFilledSkill
import sun.rmi.runtime.Log

/**
Class "occupationSkillCalculatorImpl"

TODO: Describe class utility.
 */
class occupationSkillCalculatorImpl : OccupationSkillCalculator {

    companion object{
        private const val TAG = "occupationSkillCalculatorImpl"
    }
    override fun calculateAdd(current: DomainFilledSkill?): Int? {
        var add: Int? = 0

        println ("$TAG current : $current")

        if (current?.filledSkillTensValue != null && current.filledSkillUnitsValue != null) {
            var tens = current.filledSkillTensValue.toString()
            println ("$TAG, tens : $tens")
            var units = current.filledSkillUnitsValue.toString()
            println ("$TAG, units : $units")

            try {
                add = "$tens$units".toInt()
                println ("$TAG, add : $add")

            } catch (e: IllegalArgumentException) {
                println ("$TAG, add conversion failed")
                e.printStackTrace()
                throw e
            }
        }
        return add
    }
}