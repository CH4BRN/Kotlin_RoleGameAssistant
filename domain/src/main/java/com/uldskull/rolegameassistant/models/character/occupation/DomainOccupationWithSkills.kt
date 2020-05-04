// File DomainOccupationWithSkills.kt
// @Author pierre.antoine - 24/04/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.occupation

import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill

/**
 *   Class "DomainOccupationWithSkills" :
 *   TODO: Fill class use.
 **/
class DomainOccupationWithSkills (
    var occupation:DomainOccupation?,
    var skills:List<DomainOccupationSkill>){
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        var str = "SKILLS : \n"
        skills.forEach(){domainOccupationSkill ->
            str += "$domainOccupationSkill" + "\n"
        }
        return "DomainOccupationWithSkills : ${occupation}\n$str"
    }
}