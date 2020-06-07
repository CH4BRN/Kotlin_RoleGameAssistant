// DomainCharacter.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.models.character.character

import com.uldskull.rolegameassistant.models.character.DomainBond
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollsCharacteristic

/**
Class "DomainCharacter"

Model class for domain character.
 */
open class DomainCharacter(
    var characterId: Long?,
    var characterName: String?,
    var characterAge: Int?,
    var characterGender: String?,
    var characterBiography: String?,
    var characterBonds: MutableList<DomainBond?>?,
    var characterIdeals: MutableList<DomainIdeal?>?,
    var characterHealthPoints: Int?,
    var characterIdeaPoints: Int?,
    var characterAlignment: Int?,
    var characterEnergyPoints: Int?,
    var characterHeight: Int?,
    var characterPictureUri: String?,
    var characterConstitution: DomainRollsCharacteristic?,
    var characterStrength: DomainRollsCharacteristic?,
    var characterPower: DomainRollsCharacteristic?,
    var characterDexterity: DomainRollsCharacteristic?,
    var characterSize: DomainRollsCharacteristic?,
    var characterIntelligence: DomainRollsCharacteristic?,
    var characterAppearance: DomainRollsCharacteristic?,
    var characterEducation: DomainRollsCharacteristic?,
    var characterWeight: Int?


) {
    override fun toString(): String {
        return "DomainCharacter(\n" +
                "characterId=$characterId,\n" +
                " characterName=$characterName,\n" +
                " characterAge=$characterAge,\n" +
                " characterGender=$characterGender,\n" +
                " characterBiography=$characterBiography,\n" +
                " characterBonds=$characterBonds,\n" +
                " characterIdeals=$characterIdeals,\n" +
                " characterHealthPoints=$characterHealthPoints,\n" +
                " characterIdeaPoints=$characterIdeaPoints,\n" +
                " characterAlignment=$characterAlignment,\n" +
                " characterEnergyPoints=$characterEnergyPoints,\n" +
                " characterHeight=$characterHeight,\n" +
                " characterPictureUri=$characterPictureUri,\n" +
                " characterConstitution=$characterConstitution,\n" +
                " characterStrength=$characterStrength,\n" +
                " characterPower=$characterPower,\n" +
                " characterDexterity=$characterDexterity,\n" +
                " characterSize=$characterSize,\n" +
                " characterIntelligence=$characterIntelligence,\n" +
                " characterAppearance=$characterAppearance,\n" +
                " characterEducation=$characterEducation,\n" +
                " characterWeight=$characterWeight)"
    }
}


















