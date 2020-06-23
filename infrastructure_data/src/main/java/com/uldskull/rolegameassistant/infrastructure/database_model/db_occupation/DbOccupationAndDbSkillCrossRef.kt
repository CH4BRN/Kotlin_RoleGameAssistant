// File OccupationAndSkillCrossRef.kt
// @Author pierre.antoine - 24/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation

import androidx.annotation.NonNull
import androidx.room.Entity
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_OCCUPATION_ID
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_OCCUPATION_SKILL_ID

/**
 *   Class "OccupationAndSkillCrossRef" :
 *   Relation between occupation and skills
 **/
@Entity(primaryKeys = [FIELD_OCCUPATION_ID, FIELD_OCCUPATION_SKILL_ID])
data class DbOccupationAndDbSkillCrossRef(
    /**
     * Occupation identifier
     */
    @NonNull
    val occupationId: Long,
    /**
     * Skill identifier
     */
    @NonNull
    val skillId: Long
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DbOccupationAndDbSkillCrossRef(occupationId=$occupationId, skillId=$skillId)"
    }
}