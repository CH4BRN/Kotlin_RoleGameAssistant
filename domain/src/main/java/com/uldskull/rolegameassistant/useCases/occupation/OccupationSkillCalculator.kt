// occupationSkillCalculator.kt created by UldSkull - 22/05/2020

package com.uldskull.rolegameassistant.useCases.occupation

import com.uldskull.rolegameassistant.models.character.skill.DomainFilledSkill

/**
Interface "occupationSkillCalculator"

TODO : Describe interface utility.
 **/
interface OccupationSkillCalculator {

    fun calculateAdd(current: DomainFilledSkill?): Int?
}