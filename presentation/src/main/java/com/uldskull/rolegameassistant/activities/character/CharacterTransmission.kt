// CharacterTransmission.kt created by UldSkull - 25/05/2020

package com.uldskull.rolegameassistant.activities.character

import com.uldskull.rolegameassistant.models.character.DomainCharacter

/**
Interface "CharacterTransmission"

Ensure that activity can transmit character
 **/
interface CharacterTransmission {

    /**
     * Transmit character
     */
    fun transmitCharacter(domainCharacter:DomainCharacter?)
}