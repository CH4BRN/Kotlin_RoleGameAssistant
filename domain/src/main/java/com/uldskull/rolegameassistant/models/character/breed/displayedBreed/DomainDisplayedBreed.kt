// File DomainRace.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.breed.displayedBreed

/**
 *   Class "DomainDisplayedBreed" :
 *   Represents one displayed breed
 **/
class DomainDisplayedBreed(
    /**
     * Breed identifier
     */
    val breedId: Long?,
    /**
     * Breed name
     */
    val breedName: String?,
    /**
     * Breed description
     */
    val breedDescription: String?,
    /**
     * Breed health bonus
     */
    val breedHealthBonus: Int?,
    /**
     * Is breed checked
     */
    var breedIsChecked: Boolean = false
) {
    /**
     *Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DomainDisplayedBreed(breedId=$breedId, breedName=$breedName, breedDescription=$breedDescription, breedHealthBonus=$breedHealthBonus, breedChecked=$breedIsChecked)"
    }
}
