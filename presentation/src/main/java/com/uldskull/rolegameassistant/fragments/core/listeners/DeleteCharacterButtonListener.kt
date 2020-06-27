// DeleteCharacterButtonListener.kt created by UldSkull - 27/06/2020

package com.uldskull.rolegameassistant.fragments.core.listeners

import com.uldskull.rolegameassistant.models.character.DomainCharacter

/**
Interface "DeleteCharacterButtonListener"

Interface to allow fragment to delete character
 **/
interface DeleteCharacterButtonListener {
    /**
     * Called when the "delete" button is pushed
     */
    fun deleteCharacter(domainCharacter: DomainCharacter)
}