// File DbCharacter.kt
// @Author pierre.antoine - 20/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.db_race.DbRace
import com.uldskull.rolegameassistant.models.character.DomainCharacter

/**
 *   Class "DbCharacter" :
 *   TODO: Fill class use.
 **/
@Entity(tableName = DatabaseValues.TABLE_NAME_CHARACTER)
class DbCharacter(
    @PrimaryKey(autoGenerate = true)
    val characterId: Long?,
    val characterName: String?,
    val characterAge: Int?,
    val characterBiography: String?,
    @Embedded
    val characterRace: DbRace?,
    //val characterBonds: ArrayList<String>?,
    // val characterCharacteristics: List<DbRollCharacteristic>?,
    val characterHealthPoints: Int?,
    val characterIdeaPoints: Int?,
    val characterAlignment: Int?,
    val characterEnergyPoints: Int?,
    // val characterSkills: List<DbFilledSkill>?,
    //  val characterJob: DbJob?,
    // val characterHobby: DbHobby?,
    // val characterIdeals: ArrayList<DbIdeal>?,
    val characterGender: String?,
    val characterHeight: Int?

) : DbEntity<DomainCharacter> {


    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainCharacter {
        return DomainCharacter(
            characterId = this.characterId,
            characterName = this.characterName,
            characterRace = this.characterRace?.toDomain(),
            characterAge = this.characterAge,
            characterBiography = this.characterBiography,
            //   characterBonds = this.characterBonds,
            //   characterCharacteristics = this.characterCharacteristics?.map { characteristic -> characteristic.toDomain() },
            characterAlignment = this.characterAlignment,
            characterEnergyPoints = this.characterEnergyPoints,
            characterHealthPoints = this.characterHealthPoints,
            //   characterHobby = this.characterHobby?.toDomain(),
            //  characterIdeals = this.characterIdeals?.map { ideal -> ideal.toDomain() },
            characterIdeaPoints = this.characterIdeaPoints,
            //   characterJob = this.characterJob?.toDomain(),
            //  characterSkills = this.characterSkills?.map { skill -> skill.toDomain()},
            characterGender = this.characterGender,
            characterHeight = this.characterHeight


        )
    }

    companion object : DbCompanion<DomainCharacter, DbCharacter> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainCharacter?): DbCharacter {
            return DbCharacter(
                characterId = domainModel?.characterId,

                characterHeight = domainModel?.characterHeight,
                characterGender = domainModel?.characterGender,
                //  characterBonds = null,
                // characterCharacteristics = null,
                characterAlignment = domainModel?.characterAlignment,
                characterEnergyPoints = domainModel?.characterEnergyPoints,
                characterHealthPoints = domainModel?.characterHealthPoints,
                //   characterHobby = null,
                // characterIdeals = null,
                //characterJob = null,
                //characterSkills = null,
                characterIdeaPoints = domainModel?.characterIdeaPoints,
                characterRace = DbRace.from(domainModel?.characterRace),
                characterBiography = domainModel?.characterBiography,
                characterAge = domainModel?.characterAge,
                characterName = domainModel?.characterName
            )


        }
    }
}






