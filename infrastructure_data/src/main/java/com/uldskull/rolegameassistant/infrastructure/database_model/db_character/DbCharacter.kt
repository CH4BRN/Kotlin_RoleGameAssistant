// File DbCharacter.kt
// @Author pierre.antoine - 20/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_character

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.infrastructure.database_model.db_bond.DbBond
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.DbBreed
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRollCharacteristic
import com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal.DbIdeal
import com.uldskull.rolegameassistant.models.character.DomainBond
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.models.character.character.DomainCharacter

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
    val characterBreeds: List<DbBreed?>?,
    val characterBonds: List<DbBond?>?,
    val characterHealthPoints: Int?,
    val characterIdeaPoints: Int?,
    val characterAlignment: Int?,
    val characterEnergyPoints: Int?,
    // val characterSkills: List<DbFilledSkill>?,
    //  val characterJob: DbJob?,
    // val characterHobby: DbHobby?,
    val characterIdeals: List<DbIdeal?>?,
    val characterGender: String?,
    val characterHeight: Int?,
    val characterPictureUri: String?,
    @Embedded(prefix = "str")
    val characterStrength: DbRollCharacteristic?,
    @Embedded(prefix = "siz")
    val characterSize: DbRollCharacteristic?,
    @Embedded(prefix = "pow")
    val characterPower: DbRollCharacteristic?,
    @Embedded(prefix = "int")
    val characterIntelligence: DbRollCharacteristic?,
    @Embedded(prefix = "dex")
    val characterDexterity: DbRollCharacteristic?,
    @Embedded(prefix = "con")
    val characterConstitution: DbRollCharacteristic?,
    @Embedded(prefix = "app")
    val characterAppearance: DbRollCharacteristic?


) : DbEntity<DomainCharacter> {


    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainCharacter {
        var domainBonds: MutableList<DomainBond?>
        if (!this.characterBonds.isNullOrEmpty()) {
            domainBonds = this.characterBonds.map { dbBond -> dbBond?.toDomain() }.toMutableList()
        } else {
            domainBonds = mutableListOf()
        }

        var domainIdeals: MutableList<DomainIdeal?>
        if (!this.characterIdeals.isNullOrEmpty()) {
            domainIdeals = this.characterIdeals.map { dbIdeal -> dbIdeal?.toDomain() }.toMutableList()
        } else {
            domainIdeals = mutableListOf()
        }
        var domainBreeds: MutableList<DomainBreed?>
        if (!this.characterBreeds.isNullOrEmpty()) {
            domainBreeds = this.characterBreeds.map { dbBreed -> dbBreed?.toDomain() }.toMutableList()
        } else {
            domainBreeds = mutableListOf()
        }

        return DomainCharacter(
            characterId = this.characterId,
            characterName = this.characterName,
            characterAge = this.characterAge,
            characterGender = this.characterGender,
            characterBiography = this.characterBiography,
            characterBonds = domainBonds,
            characterIdeals = domainIdeals,
            //   characterCharacteristics = this.characterCharacteristics?.map { characteristic -> characteristic.toDomain() },
            characterBreeds = domainBreeds,
            characterHealthPoints = this.characterHealthPoints,
            characterIdeaPoints = this.characterIdeaPoints,
            //   characterHobby = this.characterHobby?.toDomain(),
            characterAlignment = this.characterAlignment,
            //   characterJob = this.characterJob?.toDomain(),
            //  characterSkills = this.characterSkills?.map { skill -> skill.toDomain()},
            characterEnergyPoints = this.characterEnergyPoints,
            characterHeight = this.characterHeight,
            characterPictureUri = this.characterPictureUri,
            characterStrength = this.characterStrength?.toDomain(),
            characterSize = this.characterSize?.toDomain(),
            characterPower = this.characterPower?.toDomain(),
            characterIntelligence = this.characterIntelligence?.toDomain(),
            characterDexterity = this.characterDexterity?.toDomain(),
            characterConstitution = this.characterConstitution?.toDomain(),
            characterAppearance = this.characterAppearance?.toDomain()
        )
    }

    companion object :
        DbCompanion<DomainCharacter, DbCharacter> {
        /**
         * Converts a domain model into a database model entity.
         */
        override fun from(domainModel: DomainCharacter?): DbCharacter {
            return DbCharacter(
                characterId = domainModel?.characterId,

                characterHeight = domainModel?.characterHeight,
                characterGender = domainModel?.characterGender,
                characterPictureUri = domainModel?.characterPictureUri,
                characterBonds = domainModel?.characterBonds?.map { domainBond ->
                    DbBond.from(
                        domainBond
                    )
                },
                characterIdeals = domainModel?.characterIdeals?.map { domainIdeal ->
                    DbIdeal.from(
                        domainIdeal
                    )
                },
                // characterCharacteristics = null,
                characterAlignment = domainModel?.characterAlignment,
                characterEnergyPoints = domainModel?.characterEnergyPoints,
                characterHealthPoints = domainModel?.characterHealthPoints,
                //   characterHobby = null,
                //characterJob = null,
                //characterSkills = null,
                characterIdeaPoints = domainModel?.characterIdeaPoints,
                characterBreeds = domainModel?.characterBreeds?.map { domainBreed ->
                    DbBreed.from(
                        domainBreed
                    )
                },
                characterBiography = domainModel?.characterBiography,
                characterAge = domainModel?.characterAge,
                characterName = domainModel?.characterName,
                characterAppearance = DbRollCharacteristic.from(domainModel?.characterAppearance),
                characterConstitution = DbRollCharacteristic.from(domainModel?.characterConstitution),
                characterDexterity = DbRollCharacteristic.from(domainModel?.characterDexterity),
                characterIntelligence = DbRollCharacteristic.from(domainModel?.characterIntelligence),
                characterPower = DbRollCharacteristic.from(domainModel?.characterPower),
                characterSize = DbRollCharacteristic.from(domainModel?.characterSize),
                characterStrength = DbRollCharacteristic.from(domainModel?.characterStrength)
            )


        }
    }
}








