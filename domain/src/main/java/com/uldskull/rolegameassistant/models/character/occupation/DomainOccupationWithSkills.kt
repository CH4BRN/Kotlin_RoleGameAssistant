// File DomainOccupationWithSkills.kt
// @Author pierre.antoine - 24/04/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.occupation

import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToCheck

/**
 *   Class "DomainOccupationWithSkills" :
 *   Holds an occupation with its skills
 **/
class DomainOccupationWithSkills (
    /**
     * The occupation
     */
    var occupation:DomainOccupation?,
    /**
     * The skills list
     */
    var skills:List<DomainSkillToCheck>){
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainOccupationWithSkills(occupation=$occupation, skills=$skills)"
    }
}