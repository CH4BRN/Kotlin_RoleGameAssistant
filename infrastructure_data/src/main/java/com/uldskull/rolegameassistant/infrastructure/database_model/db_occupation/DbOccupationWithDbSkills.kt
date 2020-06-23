// File DbOccupationWithDbSkills.kt
// @Author pierre.antoine - 24/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_OCCUPATION_ID
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_OCCUPATION_SKILL_ID
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbSkillToCheck

/**
 *   Class "DbOccupationWithDbSkills" :
 *   Holds an occupation with all its skills
 **/
class DbOccupationWithDbSkills (
    /**
     * The occupation
     */
    @Embedded
    val occupation: DbOccupation,
    /**
     * The skills
     */
    @Relation(
        parentColumn = FIELD_OCCUPATION_ID,
        entityColumn = FIELD_OCCUPATION_SKILL_ID,
        associateBy = Junction(DbOccupationAndDbSkillCrossRef::class)
    )
    val skills:List<DbSkillToCheck>
){
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DbOccupationWithDbSkills(occupation=$occupation, skills=$skills)"
    }
}