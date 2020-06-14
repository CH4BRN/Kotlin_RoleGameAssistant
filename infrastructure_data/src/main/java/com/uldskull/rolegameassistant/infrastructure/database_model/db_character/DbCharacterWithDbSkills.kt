package com.uldskull.rolegameassistant.infrastructure.database_model.db_character

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Relation
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_CHARACTER_ID
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_FILLED_OCCUPATION_SKILL_CHARACTER_ID
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_SKILL_ID
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbFilledSkill

class DbCharacterWithDbSkills(
    @Embedded
    val character: DbCharacter,
    @Relation(
        parentColumn = FIELD_CHARACTER_ID,
        entityColumn = FIELD_FILLED_OCCUPATION_SKILL_CHARACTER_ID
    )
    val skills: List<DbFilledSkill>
) {
    override fun toString(): String {
        return "DbCharacterWithDbSkills(character=$character, skills=$skills)"
    }
}
