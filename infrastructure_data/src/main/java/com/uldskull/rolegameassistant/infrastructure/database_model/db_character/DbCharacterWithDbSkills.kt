package com.uldskull.rolegameassistant.infrastructure.database_model.db_character

import androidx.room.Embedded
import androidx.room.Relation
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_CHARACTER_ID
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_FILLED_OCCUPATION_SKILL_CHARACTER_ID
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbFilledSkill

/**
 * Holds a database character with skills
 */
class DbCharacterWithDbSkills(
    /**
     * The databse character
     */
    @Embedded
    val character: DbCharacter,
    /**
     * The skills
     */
    @Relation(
        parentColumn = FIELD_CHARACTER_ID,
        entityColumn = FIELD_FILLED_OCCUPATION_SKILL_CHARACTER_ID
    )
    val skills: List<DbFilledSkill>
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DbCharacterWithDbSkills(character=$character, skills=$skills)"
    }
}
