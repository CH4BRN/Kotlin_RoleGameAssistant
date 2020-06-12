// DomainCharacter.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.models.character.character

import com.uldskull.rolegameassistant.models.character.DomainBond
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation

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
    var characterBaseHealthPoints:Int?,
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
    var characterWeight: Int?,
    var characterSanity: Int?,
    var characterLuck: Int?,
    var characterKnow: Int?,
    var characterBreedBonus: Int?,
    var characterOccupation:DomainOccupation?,
    var characterSelectedOccupationSkill: MutableList<Long?>?
) {
    override fun toString(): String {
        return "DomainCharacter(characterName=$characterName, characterBonds=$characterBonds, characterIdeals=$characterIdeals, characterBreedBonus=$characterBreedBonus, characterOccupation=$characterOccupation, characterSelectedOccupationSkill=$characterSelectedOccupationSkill)".toUpperCase()
    }
}


















