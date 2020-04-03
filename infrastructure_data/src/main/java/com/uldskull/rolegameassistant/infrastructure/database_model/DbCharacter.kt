// File DbCharacter.kt
// @Author pierre.antoine - 20/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.DbBreed
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
    val characterBreed: DbBreed?,
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
    val characterHeight: Int?,
    val characterPictureUri: String?

) : DbEntity<DomainCharacter> {


    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainCharacter {
        return DomainCharacter(
            characterId = this.characterId,
            characterName = this.characterName,
            characterAge = this.characterAge,
            characterGender = this.characterGender,
            characterBiography = this.characterBiography,
            //   characterBonds = this.characterBonds,
            //   characterCharacteristics = this.characterCharacteristics?.map { characteristic -> characteristic.toDomain() },
            characterBreed = this.characterBreed?.toDomain(),
            characterHealthPoints = this.characterHealthPoints,
            characterIdeaPoints = this.characterIdeaPoints,
            //   characterHobby = this.characterHobby?.toDomain(),
            //  characterIdeals = this.characterIdeals?.map { ideal -> ideal.toDomain() },
            characterAlignment = this.characterAlignment,
            //   characterJob = this.characterJob?.toDomain(),
            //  characterSkills = this.characterSkills?.map { skill -> skill.toDomain()},
            characterEnergyPoints = this.characterEnergyPoints,
            characterHeight = this.characterHeight,
            characterPictureUri = this.characterPictureUri


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
                characterPictureUri = domainModel?.characterPictureUri,
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
                characterBreed = DbBreed.from(domainModel?.characterBreed),
                characterBiography = domainModel?.characterBiography,
                characterAge = domainModel?.characterAge,
                characterName = domainModel?.characterName
            )


        }
    }
}






