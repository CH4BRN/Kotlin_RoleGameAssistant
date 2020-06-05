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
        return "DomainCharacter(characterId=$characterId, characterName=$characterName, characterAge=$characterAge, characterGender=$characterGender, characterBiography=$characterBiography, characterBonds=$characterBonds, characterIdeals=$characterIdeals, characterHealthPoints=$characterHealthPoints, characterIdeaPoints=$characterIdeaPoints, characterAlignment=$characterAlignment, characterEnergyPoints=$characterEnergyPoints, characterHeight=$characterHeight, characterPictureUri=$characterPictureUri, characterConstitution=$characterConstitution, characterStrength=$characterStrength, characterPower=$characterPower, characterDexterity=$characterDexterity, characterSize=$characterSize, characterIntelligence=$characterIntelligence, characterAppearance=$characterAppearance, characterEducation=$characterEducation, characterWeight=$characterWeight)"
    }
}


















