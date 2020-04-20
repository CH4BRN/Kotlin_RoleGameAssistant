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
    val breedDescription: String?,
    val breedHealthBonus: Int?,
    var breedChecked: Boolean = false
) {
    override fun toString(): String =
        "\tBREED : \n" +
                "Id : ${breedId}\n" +
                "Name : ${breedName}\n" +
                "Description : ${breedDescription}\n" +
                "Health bonus : $breedHealthBonus\n" +
                "Checked : $breedChecked"
}
