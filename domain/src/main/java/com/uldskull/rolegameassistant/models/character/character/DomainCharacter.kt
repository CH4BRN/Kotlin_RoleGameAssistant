// DomainCharacter.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.models.character.character

import com.uldskull.rolegameassistant.models.character.DomainBond
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollCharacteristic

/**
Class "DomainCharacter"

Model class for domain character.
 */
open class DomainCharacter(
    var characterId: Long?,
    val characterName: String?,
    val characterAge: Int?,
    val characterGender: String?,
    val characterBiography: String?,
    val characterBreeds: List<DomainBreed?>?,
    val characterBonds: List<DomainBond?>?,
    val characterIdeals: List<DomainIdeal?>?,
    val characterHealthPoints: Int?,
    val characterIdeaPoints: Int?,
    val characterAlignment: Int?,
    val characterEnergyPoints: Int?,
    val characterHeight: Int?,
    val characterPictureUri: String?,
    var characterConstitution: DomainRollCharacteristic?,
    var characterStrength: DomainRollCharacteristic?,
    var characterPower: DomainRollCharacteristic?,
    var characterDexterity: DomainRollCharacteristic?,
    var characterSize: DomainRollCharacteristic?,
    var characterIntelligence: DomainRollCharacteristic?,
    var characterAppearance: DomainRollCharacteristic?


) {
    override fun toString(): String {
        var bondsList: String = ""
        var idealsList: String = ""

        characterBonds?.forEach { bond ->
            bondsList += "\t" + bond?.bondTitle.toString() + "\n"
        }

        characterIdeals?.forEach { ideal ->
            idealsList += "\t" + ideal?.idealName.toString() + "\n"
        }

        return "\n" +
                "nam : ${characterName}\n" +
                "id : ${characterId}\n" +
                "age : ${characterAge}\n" +
                "gen : ${characterGender}\n" +
                "bio : ${characterBiography}\n" +
                "bre : ${characterBreeds?.size}\n" +
                "pic : $characterPictureUri\n" +
                "bon : \n$bondsList\n" +
                "ide : \n$idealsList\n" +
                "con : $characterConstitution\n" +
                "str : $characterStrength\n" +
                "pow : $characterPower\n" +
                "dex : $characterDexterity\n" +
                "siz : $characterSize\n" +
                "int : $characterIntelligence\n" +
                "app : $characterAppearance"
    }
}


















