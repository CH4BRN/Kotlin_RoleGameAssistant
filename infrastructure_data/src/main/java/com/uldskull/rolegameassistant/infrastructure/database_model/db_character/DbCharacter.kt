// File DbCharacter.kt
// @Author pierre.antoine - 20/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_character

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.TableNames.TABLE_NAME_CHARACTER
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCompanion
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.infrastructure.database_model.db_bond.DbBond
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRollCharacteristic
import com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal.DbIdeal
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupation
import com.uldskull.rolegameassistant.models.DomainBond
import com.uldskull.rolegameassistant.models.DomainIdeal
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import java.util.*

/**
 *   Class "DbCharacter" :
 *   Database model for character
 **/
@Entity(tableName = TABLE_NAME_CHARACTER)
class DbCharacter(
    /**
     * Character's identifier
     */
    @PrimaryKey(autoGenerate = true)
    val characterId: Long? = null,
    /**
     * Character's name
     */
    val characterName: String?,
    /**
     * Character's age
     */
    val characterAge: Int?,
    /**
     * Character's biography
     */
    val characterBiography: String?,
    /**
     * Character's bonds
     */
    val characterBonds: List<DbBond?>?,
    /**
     * Character's health points
     */
    val characterHealthPoints: Int?,
    /**
     * Character's base health
     */
    val characterBaseHealth: Int?,
    /**
     * Character's idea points
     */
    val characterIdeaPoints: Int?,
    /**
     * Character's alignment
     */
    val characterAlignment: Int?,
    /**
     * Character's energy points
     */
    val characterEnergyPoints: Int?,
    /**
     * Character's ideals
     */
    val characterIdeals: List<DbIdeal?>?,
    /**
     * Character's gender
     */
    val characterGender: String?,
    /**
     * Character's height
     */
    val characterHeight: Int?,
    /**
     * Character's picture URI
     */
    val characterPictureUri: String?,
    /**
     * Character's weight
     */
    val characterWeight: Int?,
    /**
     * Character's know
     */
    val characterKnow: Int?,
    /**
     * Character's luck
     */
    val characterLuck: Int?,
    /**
     * Character's sanity
     */
    val characterSanity: Int?,
    /**
     * Character's breed bonus
     */
    val characterBreedBonus: Int?,
    /**
     * character's selected occupation skills
     */
    val characterSelectedOccupationSkill: List<Long?>?,
    /**
     * character's selected hobbies skills
     */
    val characterSelectedHobbiesSkill: MutableList<Long?>?,
    /**
     * Character's selected breeds
     */
    val characterSelectedBreeds: List<Long?>?,
    /**
     * Character's spent occupation points
     */
    val characterSpentOccupationPoints: Int?,
    /**
     * Characters occupation
     */
    @Embedded(prefix = "occ")
    val characterOccupation: DbOccupation?,
    /**
     * character's strength
     */
    @Embedded(prefix = "str")
    val characterStrength: DbRollCharacteristic?,
    /**
     * Character's size
     */
    @Embedded(prefix = "siz")
    val characterSize: DbRollCharacteristic?,
    /**
     * Character's power
     */
    @Embedded(prefix = "pow")
    val characterPower: DbRollCharacteristic?,
    /**
     * Character's intelligence
     */
    @Embedded(prefix = "int")
    val characterIntelligence: DbRollCharacteristic?,
    /**
     * Character's dexterity
     */
    @Embedded(prefix = "dex")
    val characterDexterity: DbRollCharacteristic?,
    /**
     * Character's constitution
     */
    @Embedded(prefix = "con")
    val characterConstitution: DbRollCharacteristic?,
    /**
     * Character's appearance
     */
    @Embedded(prefix = "app")
    val characterAppearance: DbRollCharacteristic?,
    /**
     * Character's education
     */
    @Embedded(prefix = "edu")
    val characterEducation: DbRollCharacteristic?
) : DbEntity<DomainCharacter> {


    /**
     * Converts a Database model entity into a domain model.
     */
    override fun toDomain(): DomainCharacter {
        val domainBonds: MutableList<DomainBond?>
        domainBonds = if (!this.characterBonds.isNullOrEmpty()) {
            this.characterBonds.map { dbBond -> dbBond?.toDomain() }.toMutableList()
        } else {
            mutableListOf()
        }

        val domainIdeals: MutableList<DomainIdeal?>
        domainIdeals = if (!this.characterIdeals.isNullOrEmpty()) {
            this.characterIdeals.map { dbIdeal -> dbIdeal?.toDomain() }.toMutableList()
        } else {
            mutableListOf()
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
            characterAppearance = this.characterAppearance?.toDomain(),
            characterEducation = this.characterEducation?.toDomain(),
            characterWeight = this.characterWeight,
            characterKnow = this.characterKnow,
            characterLuck = this.characterLuck,
            characterSanity = this.characterSanity,
            characterBaseHealthPoints = this.characterBaseHealth,
            characterBreedBonus = this.characterBreedBonus,
            characterSelectedOccupationSkill = this.characterSelectedOccupationSkill?.toMutableList(),
            characterOccupation = this.characterOccupation?.toDomain(),
            characterBreeds = this.characterSelectedBreeds?.toMutableList(),
            characterSpentOccupationPoints = this.characterSpentOccupationPoints,
            characterSelectedHobbiesSkill = this.characterSelectedHobbiesSkill
        )
    }

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DbCharacter(characterId=$characterId, characterName=$characterName, characterAge=$characterAge, characterBiography=$characterBiography, characterBonds=$characterBonds, characterHealthPoints=$characterHealthPoints, characterBaseHealth=$characterBaseHealth, characterIdeaPoints=$characterIdeaPoints, characterAlignment=$characterAlignment, characterEnergyPoints=$characterEnergyPoints, characterIdeals=$characterIdeals, characterGender=$characterGender, characterHeight=$characterHeight, characterPictureUri=$characterPictureUri, characterWeight=$characterWeight, characterKnow=$characterKnow, characterLuck=$characterLuck, characterSanity=$characterSanity, characterBreedBonus=$characterBreedBonus, characterSelectedOccupationSkill=$characterSelectedOccupationSkill, characterStrength=$characterStrength, characterSize=$characterSize, characterPower=$characterPower, characterIntelligence=$characterIntelligence, characterDexterity=$characterDexterity, characterConstitution=$characterConstitution, characterAppearance=$characterAppearance, characterEducation=$characterEducation)".toUpperCase(
            Locale.ENGLISH
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
                characterBiography = domainModel?.characterBiography,
                characterAge = domainModel?.characterAge,
                characterName = domainModel?.characterName,
                characterAppearance = DbRollCharacteristic.from(domainModel?.characterAppearance),
                characterConstitution = DbRollCharacteristic.from(domainModel?.characterConstitution),
                characterDexterity = DbRollCharacteristic.from(domainModel?.characterDexterity),
                characterIntelligence = DbRollCharacteristic.from(domainModel?.characterIntelligence),
                characterPower = DbRollCharacteristic.from(domainModel?.characterPower),
                characterSize = DbRollCharacteristic.from(domainModel?.characterSize),
                characterStrength = DbRollCharacteristic.from(domainModel?.characterStrength),
                characterEducation = DbRollCharacteristic.from(domainModel?.characterEducation),
                characterWeight = domainModel?.characterWeight,
                characterSanity = domainModel?.characterSanity,
                characterLuck = domainModel?.characterLuck,
                characterKnow = domainModel?.characterKnow,
                characterBaseHealth = domainModel?.characterBaseHealthPoints,
                characterBreedBonus = domainModel?.characterBreedBonus,
                characterSelectedOccupationSkill = domainModel?.characterSelectedOccupationSkill,
                characterOccupation = DbOccupation.from(domainModel?.characterOccupation),
                characterSelectedBreeds = domainModel?.characterBreeds,
                characterSpentOccupationPoints = domainModel?.characterSpentOccupationPoints,
                characterSelectedHobbiesSkill = domainModel?.characterSelectedHobbiesSkill

            )
        }
    }
}








