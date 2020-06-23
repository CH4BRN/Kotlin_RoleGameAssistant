// occupationSkillCalculator.kt created by UldSkull - 22/05/2020

package com.uldskull.rolegameassistant.useCases.occupation

import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill

/**
Interface "occupationSkillCalculator"

 contracts for skill total calculation
 **/
interface OccupationSkillCalculator {

    /**
     * Adds the base and the bonus to gets the total.
     */
    fun calculateAdd(current: DomainSkillToFill?): Int?
}