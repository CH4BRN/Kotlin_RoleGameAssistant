// File DbCharacter.kt
// @Author pierre.antoine - 20/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model

import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRollCharacteristic
import com.uldskull.rolegameassistant.models.character.DomainCharacter

/**
 *   Class "DbCharacter" :
 *   TODO: Fill class use.
 **/
class DbCharacter(
    val characterId: Long,
    val characterName: String,
    val characterAge: Int,
    val characterBiography: String,
    val characterRace: List<DbRace>,
    val characterBonds: ArrayList<String>,
    val characterCharacteristics: List<DbRollCharacteristic>,
    val characterHealthPoints: Int,
    val characterIdeaPoints: Int,
    val characterAlignment: Int,
    val characterEnergyPoints: Int,
    val characterSkills: List<DbFilledSkill>,
    val characterJob: DbJob,
    val characterHobby: DbHobby,
    val characterIdeals: ArrayList<DbIdeal>

) : DbEntity<DomainCharacter> {
    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainCharacter {
        return DomainCharacter(
            characterId = this.characterId,
            characterName = this.characterName,
            characterRace = this.characterRace.map { race -> race.toDomain() },
            characterAge = this.characterAge,
            characterBiography = this.characterBiography,
            characterBonds = this.characterBonds,
            characterCharacteristics = this.characterCharacteristics.map { characteristic -> characteristic.toDomain() },
            characterAlignment = this.characterAlignment,
            characterEnergyPoints = this.characterEnergyPoints,
            characterHealthPoints = this.characterHealthPoints,
            characterHobby = this.characterHobby.toDomain(),
            characterIdeals = this.characterIdeals.map { ideal -> ideal.toDomain() },
            characterIdeaPoints = this.characterIdeaPoints,
            characterJob = this.characterJob.toDomain(),
            characterSkills = this.characterSkills.map { skill -> skill.toDomain() }

        )
    }
}






