// DomainCharacter.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.models.character

/**
Class "DomainCharacter"

Model class for domain character.
 */
open class DomainCharacter(
    val characterId: Long?,
    val characterName: String?,
    val characterAge: Int?,
    val characterGender: String?,
    val characterBiography: String?,
    val characterRace: DomainRace?,
    // val characterBonds: List<String>?,
    //val characterCharacteristics: List<DomainRollCharacteristic>?,
    val characterHealthPoints: Int?,
    val characterIdeaPoints: Int?,
    val characterAlignment: Int?,
    val characterEnergyPoints: Int?,
    //val characterSkills: List<DomainFilledSkill>?,
    //val characterJob: DomainJob?,
    //val characterHobby: DomainHobby?,
    // val characterIdeals: List<DomainIdeal>?,
    val characterHeight: Int?
)


















