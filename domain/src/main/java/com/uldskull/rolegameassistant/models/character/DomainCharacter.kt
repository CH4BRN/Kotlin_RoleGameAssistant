// DomainCharacter.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.models.character

import com.uldskull.rolegameassistant.models.DomainBond
import com.uldskull.rolegameassistant.models.DomainIdeal
import com.uldskull.rolegameassistant.models.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.models.occupation.DomainOccupation

/**
Class "DomainCharacter"

Model class for domain character.
 */
open class DomainCharacter(
    /**
     * Character's identifier
     */
    var characterId: Long?,
    /**
     * Character's name
     */
    var characterName: String? = "Name",
    /**
     * Character's age
     */
    var characterAge: Int? = 42,
    /**
     * Character's gender
     */
    var characterGender: String? = "Gender",
    /**
     * Character's biography
     */
    var characterBiography: String? ="Let's type a biography.",
    /**
     * Character's bonds
     */
    var characterBonds: MutableList<DomainBond?>? = mutableListOf(),
    /**
     * Character's ideals
     */
    var characterIdeals: MutableList<DomainIdeal?>? = mutableListOf(),
    /**
     * Character's base health points
     */
    var characterBaseHealthPoints:Int? = 42,
    /**
     * Character's health points
     */
    var characterHealthPoints: Int? = 42,
    /**
     * Character's ideas points
     */
    var characterIdeaPoints: Int? = 42,
    /**
     * Character's alignment score
     */
    var characterAlignment: Int? = 42,
    /**
     * Character's energy points
     */
    var characterEnergyPoints: Int? = 42,
    /**
     * Character's height
     */
    var characterHeight: Int? = 42,
    /**
     * Character's picture URI
     */
    var characterPictureUri: String?,
    /**
     * Character's constitution
     */
    var characterConstitution: DomainRollsCharacteristic?,
    /**
     * Character's strength
     */
    var characterStrength: DomainRollsCharacteristic?,
    /**
     * Character's power
     */
    var characterPower: DomainRollsCharacteristic?,
    /**
     * Character's dexterity
     */
    var characterDexterity: DomainRollsCharacteristic?,
    /**
     * Character's size
     */
    var characterSize: DomainRollsCharacteristic?,
    /**
     * Character's intelligence
     */
    var characterIntelligence: DomainRollsCharacteristic?,
    /**
     * Character's appearance
     */
    var characterAppearance: DomainRollsCharacteristic?,
    /**
     * Character's education
     */
    var characterEducation: DomainRollsCharacteristic?,
    /**
     * Character's weight
     */
    var characterWeight: Int? = 42,
    /**
     * Character's sanity
     */
    var characterSanity: Int? = 42,
    /**
     * Character's luck
     */
    var characterLuck: Int? = 42,
    /**
     * Character's know
     */
    var characterKnow: Int? = 42,
    /**
     * Character's breed bonus
     */
    var characterBreedBonus: Int? = 42,
    /**
     * Character's occupation
     */
    var characterOccupation:DomainOccupation?,
    /**
     * Character's selected occupation skills
     */
    var characterSelectedOccupationSkill: MutableList<Long?>?,
    /**
     * Character's selected hobbies skills
     */
    var characterSelectedHobbiesSkill:MutableList<Long?>?,
    /**
     * Character's breed
     */
    var characterBreeds: MutableList<Long?>?,
    /**
     * Character's spent occupation points
     */
    var characterSpentOccupationPoints:Int? = 0
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainCharacter(characterId=$characterId, characterName=$characterName, characterSpentOccupationPoints=$characterSpentOccupationPoints)"
    }
}


















