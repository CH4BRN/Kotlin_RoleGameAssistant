// EditCthulhuMythButtonListener.kt created by UldSkull - 29/06/2020

package com.uldskull.rolegameassistant.fragments.core.listeners

import com.uldskull.rolegameassistant.models.character.DomainCharacter

/**
Interface "EditSanityButtonListener"

Interface to allow fragment to edit the character's sanity  score
 **/
interface EditSanityButtonListener {
    /**
     * Called when the "edit sanity" button is pushed
     */
    fun editSanityScore(domainCharacter: DomainCharacter)
}