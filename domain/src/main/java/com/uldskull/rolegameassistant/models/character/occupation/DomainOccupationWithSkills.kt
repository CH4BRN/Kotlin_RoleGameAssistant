// File DomainOccupationWithSkills.kt
// @Author pierre.antoine - 24/04/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.occupation

import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToCheck

/**
 *   Class "DomainOccupationWithSkills" :
 *   TODO: Fill class use.
 **/
class DomainOccupationWithSkills (
    var occupation:DomainOccupation?,
    var skills:List<DomainSkillToCheck>){
    override fun toString(): String {
        return "DomainOccupationWithSkills(occupation=$occupation, skills=$skills)"
    }
}