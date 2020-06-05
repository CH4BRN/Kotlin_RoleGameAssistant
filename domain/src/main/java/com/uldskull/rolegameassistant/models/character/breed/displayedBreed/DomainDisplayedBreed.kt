// File DomainRace.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.breed.displayedBreed

/**
 *   Class "DomainBreed" :
 *   Represents one breed
 **/
class DomainDisplayedBreed(
    val breedId: Long?,
    val breedName: String?,
    val breedDescription: String?,
    val breedHealthBonus: Int?,
    var breedChecked: Boolean = false
) {
    override fun toString(): String {
        return "DomainDisplayedBreed(breedId=$breedId, breedName=$breedName, breedDescription=$breedDescription, breedHealthBonus=$breedHealthBonus, breedChecked=$breedChecked)"
    }
}
