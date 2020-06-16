/** File DatabaseContract.kt
 *   @Author pierre.antoine - 13/02/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.infrastructure

import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_BREED_CHARACTERISTICS
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_SKILL_TO_CHECK
import com.uldskull.rolegameassistant.infrastructure.dao.DELETE_FROM
import com.uldskull.rolegameassistant.infrastructure.dao.SELECT_ALL_FROM

object DatabaseValues {
    const val DATABASE_NAME = "appdb"
}

object IdFieldName{
    const val FIELD_OCCUPATION_SKILL_ID = "skillId"
    const val FIELD_BREED_CHARACTERISTIC_ID = "characteristicId"
    const val FIELD_BOND_ID = "bondId"
    const val FIELD_BREED_ID = "breedId"
    const val FIELD_CHARACTER_BREED_ID = "characterBreedId"
    const val FIELD_CHARACTER_ID = "characterId"
    const val FIELD_SKILL_ID = "filledSkillId"
    const val FIELD_IDEAL_ID = "idealId"
    const val FIELD_OCCUPATION_ID = "occupationId"
    const val FIELD_CHARACTERISTIC_BREED_ID = "characteristicBreedId"
    const val FIELD_FILLED_OCCUPATION_SKILL_ID = "filledSkillId"
    const val FIELD_FILLED_OCCUPATION_SKILL_TYPE = "filledSkillType"
    const val FIELD_FILLED_OCCUPATION_SKILL_CHARACTER_ID = "filledSkillCharacterId"
}

object TableNames{
    const val TABLE_NAME_SKILL_TO_CHECK  = "skill_to_check"
    const val TABLE_NAME_FILLED_OCCUPATION_SKILL = "DbFilledSkill"
    const val TABLE_NAME_SKILL = "skill"
    const val TABLE_NAME_HEALTH = "health"
    const val TABLE_NAME_ABILITY_SCORE = "ability_score"
    const val TABLE_NAME_PICTURE = "picture"
    const val TABLE_NAME_BACKGROUND = "background"
    const val TABLE_NAME_BIOGRAPHY = "biography"
    const val TABLE_NAME_BOND = "bond"
    const val TABLE_NAME_FLAW = "flaw"
    const val TABLE_NAME_IDEAL = "ideal"
    const val TABLE_NAME_PERSONALITY = "personality"
    const val TABLE_NAME_BASIC_INFO = "basic_info"
    const val TABLE_NAME_CLASS = "class"
    const val TABLE_NAME_LEVEL = "level"
    const val TABLE_NAME_DISPLAYED_BREED = "displayed_breed"
    const val TABLE_NAME_CHARACTERS_BREED = "characters_breed"
    const val TABLE_NAME_CHARACTER = "character"
    const val TABLE_NAME_CHARACTERISTICS = "characteristics"
    const val TABLE_NAME_BREED_CHARACTERISTICS = "breed_characteristics"
    const val TABLE_NAME_ROLL_CHARACTERISTICS = "roll_characteristics"
    const val TABLE_NAME_GENDER = "gender"
    const val TABLE_NAME_OCCUPATIONS = "occupations"
}

object Queries{
    const val SELECT_ALL_BREED_CHARACTERISTICS = "$SELECT_ALL_FROM $TABLE_NAME_BREED_CHARACTERISTICS"
    const val DELETE_ALL_BREED_CHARACTERISTICS = "$DELETE_FROM $TABLE_NAME_BREED_CHARACTERISTICS"
    const val SELECT_ALL_OCCUPATION_SKILLS = "$SELECT_ALL_FROM $TABLE_NAME_SKILL_TO_CHECK"
    const val DELETE_ALL_OCCUPATION_SKILLS = "$DELETE_FROM $TABLE_NAME_SKILL_TO_CHECK"
}