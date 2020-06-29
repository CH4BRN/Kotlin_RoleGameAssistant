// EditLifePointsButtonListener.kt created by UldSkull - 29/06/2020

package com.uldskull.rolegameassistant.fragments.core.listeners

import com.uldskull.rolegameassistant.models.character.DomainCharacter

/**
Interface "EditLifePointsButtonListener"

Interface to allow fragment to edit the character's life points
 **/
interface EditLifePointsButtonListener {
    /**
     * Called when the "edit life points" button is pushed
     */
    fun editLifePoints(domainCharacter: DomainCharacter)
}