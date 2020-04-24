// File DbOccupationWithDbSkills.kt
// @Author pierre.antoine - 24/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.uldskull.rolegameassistant.infrastructure.database_model.DbOccupationSkill_id
import com.uldskull.rolegameassistant.infrastructure.database_model.DbOccupation_id
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbOccupationSkill

/**
 *   Class "DbOccupationWithDbSkills" :
 *   TODO: Fill class use.
 **/
class DbOccupationWithDbSkills (
    @Embedded
    val occupation: DbOccupation,
    @Relation(
        parentColumn = DbOccupation_id,
        entityColumn = DbOccupationSkill_id,
        associateBy = Junction(DbOccupationAndDbSkillCrossRef::class)
    )
    val skills:List<DbOccupationSkill>
){
    override fun toString(): String {
        var childrenSkills:String =""
        if(!skills.isNullOrEmpty()){
            skills.forEach {dbOccupationSkill ->
                childrenSkills += "\n" + dbOccupationSkill.skillName
            }
        }
        return occupation.occupationName + childrenSkills

    }
}