// occupationSkillCalculatorImpl.kt created by UldSkull - 22/05/2020

package com.uldskull.rolegameassistant.services.occupation

import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.services.OccupationSkillCalculator

/**
Class "occupationSkillCalculatorImpl"

holds the skill calculator implementation
 */
class OccupationSkillCalculatorImpl :
    OccupationSkillCalculator {

    companion object{
        private const val TAG = "occupationSkillCalculatorImpl"
    }

    /**
     * Adds the bonus and the base to get the total.
     */
    override fun calculateAdd(current: DomainSkillToFill?): Int? {
        var add: Int? = 0

        println ("$TAG current : $current")

        if (current?.filledSkillTensValue != null && current.filledSkillUnitsValue != null) {
            val tens = current.filledSkillTensValue.toString()
            println ("$TAG, tens : $tens")
            val units = current.filledSkillUnitsValue.toString()
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