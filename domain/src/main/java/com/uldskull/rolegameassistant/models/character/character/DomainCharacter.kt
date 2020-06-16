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
    var characterName: String? = "Name",
    var characterAge: Int? = 42,
    var characterGender: String? = "Gender",
    var characterBiography: String? ="Let's type a biography.",
    var characterBonds: MutableList<DomainBond?>? = mutableListOf(),
    var characterIdeals: MutableList<DomainIdeal?>? = mutableListOf(),
    var characterBaseHealthPoints:Int? = 42,
    var characterHealthPoints: Int? = 42,
    var characterIdeaPoints: Int? = 42,
    var characterAlignment: Int? = 42,
    var characterEnergyPoints: Int? = 42,
    var characterHeight: Int? = 42,
    var characterPictureUri: String?,
    var characterConstitution: DomainRollsCharacteristic?,
    var characterStrength: DomainRollsCharacteristic?,
    var characterPower: DomainRollsCharacteristic?,
    var characterDexterity: DomainRollsCharacteristic?,
    var characterSize: DomainRollsCharacteristic?,
    var characterIntelligence: DomainRollsCharacteristic?,
    var characterAppearance: DomainRollsCharacteristic?,
    var characterEducation: DomainRollsCharacteristic?,
    var characterWeight: Int? = 42,
    var characterSanity: Int? = 42,
    var characterLuck: Int? = 42,
    var characterKnow: Int? = 42,
    var characterBreedBonus: Int? = 42,
    var characterOccupation:DomainOccupation?,
    var characterSelectedOccupationSkill: MutableList<Long?>?,
    var characterSelectedHobbiesSkill:MutableList<Long?>?,
    var characterBreeds: MutableList<Long?>?,
    var characterSpentOccupationPoints:Int? = 0
) {
    override fun toString(): String {
        return "DomainCharacter(characterId=$characterId, characterName=$characterName, characterSpentOccupationPoints=$characterSpentOccupationPoints)"
    }
}


















