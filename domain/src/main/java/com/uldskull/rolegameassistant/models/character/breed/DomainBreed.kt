// File DomainRace.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character.breed

/**
 *   Class "DomainBreed" :
 *   Represents one breed
 **/
class DomainBreed(
    val breedId: Long?,
    val breedName: String?,
    val breedDescription: String?
) {
    override fun toString(): String =
        "\nId : ${breedId}\nName : ${breedName}\nDescription : ${breedDescription}"
}
